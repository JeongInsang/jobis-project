package com.ssafy.domain.worldcup.service;

import com.ssafy.domain.worldcup.dto.WorldcupDtoMapper;
import com.ssafy.domain.worldcup.dto.response.WorldcupResponse;
import com.ssafy.domain.worldcup.entity.Worldcup;
import com.ssafy.domain.worldcup.repository.WorldcupRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorldcupService {

    private final WorldcupRepository worldcupRepository;

    public List<WorldcupResponse> findRandomWorldcups() {
        List<Worldcup> randomWorldcups = worldcupRepository.findRandomWorldcups();

        return WorldcupDtoMapper.worldcupEntityListToDtoList(randomWorldcups);
    }

}
