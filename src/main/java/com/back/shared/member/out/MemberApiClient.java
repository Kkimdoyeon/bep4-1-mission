package com.back.shared.member.out;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class MemberApiClient {
    private final RestClient restClient;

    public MemberApiClient(@Value("${custom.global.internalBackUrl}") String internalBackUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(internalBackUrl + "/api/v1/member")
                .build();
    }

    public String getRandomSecureTip() {
        try {
            return restClient.get()
                    .uri("/members/randomSecureTip")
                    .retrieve()
                    .body(String.class);
        } catch (Exception e) {
            log.warn("Failed to get random security tip from Member API: {}", e.getMessage());
            return "정기적으로 비밀번호를 변경하세요."; // 기본 보안 팁 반환
        }
    }
}