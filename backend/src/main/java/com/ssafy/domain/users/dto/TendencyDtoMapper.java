package com.ssafy.domain.users.dto;

import com.ssafy.domain.users.dto.response.TendencyResponse;
import com.ssafy.domain.users.entity.Tendency;
import java.util.List;
import java.util.stream.Collectors;

public class TendencyDtoMapper {

    public static TendencyResponse tendencyEntityToDto(final Tendency tendency){
        return TendencyResponse.builder()
                .stability(tendency.getFactor().getStability())
                .profitability(tendency.getFactor().getProfitability())
                .activity(tendency.getFactor().getActivity())
                .growth(tendency.getFactor().getGrowth())
                .efficiency(tendency.getFactor().getEfficiency())
                .build();
    }
    public static List<TendencyResponse> tendencyToDtoList(final List<Tendency> tendencyList) {
        return tendencyList.stream()
                .map(TendencyDtoMapper::tendencyEntityToDto)
                .collect(Collectors.toList());
    }

}
