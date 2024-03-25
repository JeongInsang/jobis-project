package com.ssafy.domain.patent.service;

import com.ssafy.domain.patent.dto.response.PatentResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional(readOnly = true)
class PatentServiceTest {

    @Autowired PatentService patentService;

    @Test
    @DisplayName("기업특허 목록 조회")
    public void 기업특허_목록_조회() throws Exception {
        // given
        Long companyId = (long) 1;
        int answer = 3;

        // when
        List<PatentResponse> patents = patentService.findPatents(companyId);
        int size = patents.size();

        StringBuilder sb = new StringBuilder();
        System.out.println("===== START =====");
        for (PatentResponse patent : patents) {
            sb.append("getPatentId ID: ").append(patent.getPatentId()).append("\n");
            sb.append("getCompanyId ID: ").append(patent.getCompanyId()).append("\n");
            sb.append("getPatentName ID: ").append(patent.getPatentName()).append("\n");
            sb.append("getPatentDate ID: ").append(patent.getPatentDate()).append("\n");
            sb.append("getPatentSummary ID: ").append(patent.getPatentSummary()).append("\n");
        }
        System.out.println(sb.toString());
        System.out.println("===== END =====");

        assertEquals(answer, size, "조회된 특허 목록의 갯수가 기대와 다릅니다.");
    }

}