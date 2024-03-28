package com.ssafy.domain.worldcup.dto;

import com.ssafy.domain.worldcup.dto.response.WorldcupResponse;
import com.ssafy.domain.worldcup.entity.Worldcup;

import java.util.List;
import java.util.stream.Collectors;

public class WorldcupDtoMapper {

    public static WorldcupResponse worldcupEntityToDto(final Worldcup worldcup) {
        return WorldcupResponse.builder()
                .worldcupId(worldcup.getId())
                .name(worldcup.getName())
                .description(worldcup.getDescription())
                .img(worldcup.getImg())
                .stability(worldcup.getFactor().getStability())
                .profitability(worldcup.getFactor().getProfitability())
                .activity(worldcup.getFactor().getActivity())
                .growth(worldcup.getFactor().getGrowth())
                .efficiency(worldcup.getFactor().getEfficiency())
                .build();
    }

    public static List<WorldcupResponse> worldcupEntityListToDtoList(final List<Worldcup> worldcupList) {
        return worldcupList.stream()
                .map(WorldcupDtoMapper::worldcupEntityToDto)
                .collect(Collectors.toList());
    }

}
