package com.ssafy.domain.users.service;

import com.ssafy.domain.company.dto.CompanyDtoMapper;
import com.ssafy.domain.company.dto.response.CompanyResponse;
import com.ssafy.domain.company.entity.Company;
import com.ssafy.domain.company.entity.CompanyScrap;
import com.ssafy.domain.company.repository.CompanyScrapRepository;
import com.ssafy.domain.users.dto.TendencyDtoMapper;
import com.ssafy.domain.users.dto.request.RegisterRequest;
import com.ssafy.domain.users.dto.request.TendencyRequest;
import com.ssafy.domain.users.dto.response.TendencyResponse;
import com.ssafy.domain.users.entity.Tendency;
import com.ssafy.domain.users.entity.Users;
import com.ssafy.domain.users.exception.UserNotFoundException;
import com.ssafy.domain.users.repository.TendencyReposiotry;
import com.ssafy.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final TendencyReposiotry tendencyReposiotry;
    private final CompanyScrapRepository companyScrapRepository;

    public void register(RegisterRequest request) {
        log.info("--- register service 진입 ---");

        String email = request.getEmail();
        String password = passwordEncoder.encode(request.getPassword());
        request.setPassword(password);

        if(userRepository.existsByEmail(email)) {
            throw new RuntimeException("이미 해당 이메일로 가입"); // 이메일중복
        }

        Users user = Users.createUser(request);
        userRepository.save(user);
        log.info("가입할 유저이메일 = {}", user.getEmail());
    }

    public Users socialRegister(Users user) { return userRepository.save(user);
    }

//    private void validateDuplicateMember(UserDto user) {
//        Users findUser = findUser(user.getId());
//        if (findUser != null) {
//            throw new IllegalStateException("이미 가입된 회원입니다.");
//        }
//    }

    public Users findUser(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 유저가 없다"));
    }


    /**
     * 회원 성향 입력
     * @param id
     * @param tendencyRequest
     */
    public void applyUsersTendency(Integer id, TendencyRequest tendencyRequest){
        Users user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        tendencyReposiotry.save(Tendency.createUserTendency(user, tendencyRequest));
    }


    /**
     * 회원 성향 조회
     * @param user
     * @return
     */
    public List<TendencyResponse> findTendency(Users user) {
        List<Tendency> tendencies = tendencyReposiotry.findAllByUsers(user);
        return TendencyDtoMapper.tendencyToDtoList(tendencies);
    }


    /**
     * 회원 관심기업 조회
     * @param userId
     * @return
     */

    public List<CompanyResponse> findUserScrap(Integer userId){
        Users user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        List<CompanyScrap> companyScrap = companyScrapRepository.findAllByUser(user);

        List<Company> companyList = companyScrap.stream()
                .map(CompanyScrap::getCompany)
                .toList();

        return CompanyDtoMapper.companyEntityToDtoList(companyList);


    }
}
