package com.ssafy.domain.worldcup.service;

import com.ssafy.domain.worldcup.dto.response.WorldcupResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional(readOnly = true)
public class WorldcupServiceTest {

    @Autowired WorldcupService worldcupService;

    @Test
    @DisplayName("가상기업 리스트 랜덤 조회")
    public void 가상기업_목록_랜덤_조회() throws Exception {
        // given
        int answer = 16;

        // when
        List<WorldcupResponse> randomWorldcups = worldcupService.findRandomWorldcups();

        // then
        int size = randomWorldcups.size();

        System.out.println("===== START =====");
        StringBuilder sb = new StringBuilder();
        for (WorldcupResponse randomWorldcup : randomWorldcups) {
            sb.append("Worldcup ID: ").append(randomWorldcup.getWorldcupId()).append("\n");
            sb.append("Name: ").append(randomWorldcup.getName()).append("\n");
            sb.append("Description: ").append(randomWorldcup.getDescription()).append("\n");
            sb.append("Image URL: ").append(randomWorldcup.getImg()).append("\n");
            sb.append("Stability: ").append(randomWorldcup.getStability()).append("\n");
            sb.append("Profitability: ").append(randomWorldcup.getProfitability()).append("\n");
            sb.append("Activity: ").append(randomWorldcup.getActivity()).append("\n");
            sb.append("Growth: ").append(randomWorldcup.getGrowth()).append("\n");
            sb.append("Efficiency: ").append(randomWorldcup.getEfficiency()).append("\n");
            sb.append("--------------------------------\n");
        }
        System.out.println(sb.toString());
        System.out.println("===== END =====");

        assertEquals(answer, size, "조회된 가상기업 목록의 갯수가 기대와 다릅니다.");
    }

}
