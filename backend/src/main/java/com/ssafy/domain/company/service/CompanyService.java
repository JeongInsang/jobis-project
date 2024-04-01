package com.ssafy.domain.company.service;

import static com.ssafy.domain.company.entity.QCompany.company;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.domain.company.dto.*;
import com.ssafy.domain.company.dto.response.*;
import com.ssafy.domain.company.entity.*;
import com.ssafy.domain.company.exception.CompanyNotFoundException;
import com.ssafy.domain.company.repository.*;
import com.ssafy.domain.users.entity.Users;
import com.ssafy.domain.users.exception.UserNotFoundException;
import com.ssafy.domain.users.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyYouthRepository companyYouthRepository;
    private final CompanyRateRepository companyRateRepository;
    private final CompanyQuarterInfoRepository companyQuarterInfoRepository;
    private final CompanyStatementRepository companyStatementRepository;

    private final CompanyScrapRepository companyScrapRepository;
    private final UserRepository userRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    /**
     * 기업 검색
     *
     * @param pageable
     * @param keyword
     * @param location
     * @param type
     * @return
     */

    public Page<CompanySearchResponse> getCompanies(Pageable pageable, String keyword, List<String> location, List<String> type) {
        log.info("pageable = {} keyword = {} location = {} type = {}", pageable, keyword, location, type);


        List<CompanySearchResponse> companyList = jpaQueryFactory
                .selectFrom(company)
                .where(
                        containsKeyword(keyword),
                        inLocation(location),
                        inType(type))
                .offset(pageable.getOffset())  // 조회 시작 위치
                .limit(pageable.getPageSize())  // 현재 시작 위치
                .fetch()
                .stream()
                .map(CompanySearchDtoMapper::companySearchResponse)
                .collect(Collectors.toList());

        log.info("name = {}, address = {}, type = {}", company.name.contains(keyword), company.address.in(location), company.type.in(type));
        long totalSize = companyList.size();

        log.info("totalsize = {}", totalSize);
        return new PageImpl<>(companyList, pageable, totalSize);
    }

    private BooleanExpression containsKeyword(String keyword) {
        return keyword != null ? company.name.contains(keyword) : null;
    }

    private BooleanExpression inLocation(List<String> locations) {
        return !locations.isEmpty() ? company.address.in(locations) : null;
    }

    private BooleanExpression inType(List<String> types) {
        return !types.isEmpty() ? company.type.in(types) : null;
    }


    /**
     * 기업 기본정보 조회
     * @param id
     * @return
     */
    public CompanyResponse findCompany(Integer id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new);
        // 조회수 카운트
        company.companyViewsCnt();
        companyRepository.save(company);
        return CompanyDtoMapper.companyEntityToDto(company);
    }


    /**
     * 기업 재무재표 조회
     * @param id
     * @return
     */
    public List<CompanyStatementResponse> findCompanyStatement(Integer id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new);
        List<CompanyStatement> companyStatements = companyStatementRepository.findAllByCompany(company);
        log.info("== statement : {}", companyStatements.size());
        return CompanyStatementDtoMapper.companyStatementEntityToDtoList(companyStatements);
    }


    /**
     * 기업 재무비율 조회
     * @param id
     * @return
     */
    public List<CompanyRateResponse> findCompanyRate(Integer id){
        Company company = companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new);
        List<CompanyRate> companyRates = companyRateRepository.findAllByCompany(company);
        return CompanyRateDtoMapper.companyRateEntityToDtoList(companyRates);
    }


    /**
     * 기업 청년친화 조회
     * @param id
     * @return
     */
    public List<CompanyYouthResponse> findCompanyYouth(Integer id){
        Company company = companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new);
        List<CompanyYouth> companyYouth = companyYouthRepository.findAllByCompany(company);
        return CompanyYouthDtoMapper.companyYouthEntityToDtoList(companyYouth);
    }


    /**
     * 기업 분기정보 조회
     * @param id
     * @return
     */
    public List<CompanyQuarterInfoResponse> findCompanyQuarterInfo(Integer id){
        Company company = companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new);
        List<CompanyQuarterInfo> companyQuarterInfo = companyQuarterInfoRepository.findAllByCompany(company);
        return CompanyQuarterInfoDtoMapper.companyQuarterInfoToDtoList(companyQuarterInfo);
    }


    /**
     * 관심기업 스크랩
     * @param companyId
     * @param userId
     */
    public void companyScrap(Integer companyId, Integer userId){
        Company company = companyRepository.findById(companyId)
                .orElseThrow(CompanyNotFoundException::new);
        Users user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        companyScrapRepository.save(CompanyScrap.createCompanyScrap(company, user));
    }


    /**
     * 관심기업 스크랩 취소
     * @param companyId
     * @param userId
     */
    @Transactional
    public void companyScrapCancel(Integer companyId, Integer userId){
        Company company = companyRepository.findById(companyId)
                .orElseThrow(CompanyNotFoundException::new);
        Users user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        companyScrapRepository.deleteCompanyScrapByUserAndCompany(user, company);
    }

}
