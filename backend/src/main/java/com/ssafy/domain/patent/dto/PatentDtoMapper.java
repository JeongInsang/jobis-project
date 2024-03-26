package com.ssafy.domain.patent.dto;

import com.ssafy.domain.patent.dto.response.PatentResponse;
import com.ssafy.domain.patent.entity.Patent;

import java.util.List;
import java.util.stream.Collectors;

public class PatentDtoMapper {

    public static PatentResponse patentEntityToDto(final Patent patent) {
        return PatentResponse.builder()
                .patentId(patent.getId())
                .patentName(patent.getPatentName())
                .patentDate(patent.getPatentDate())
                .patentSummary(patent.getPatentSummary())
                .build();
    }

    public static List<PatentResponse> patentEntityToDtoList(final List<Patent> patentList) {
        return patentList.stream()
                .map(PatentDtoMapper::patentEntityToDto)
                .collect(Collectors.toList());
    }

}
