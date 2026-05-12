package com.ai.testing.ai_api_testing_agent.service;

import com.ai.testing.ai_api_testing_agent.dto.GenerateTestCaseRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AiServiceClient {

    private final RestTemplate restTemplate;

    public AiServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateTestCases(GenerateTestCaseRequest request) {

        String aiServiceUrl = "http://localhost:8000/generate-tests";

        return restTemplate.postForObject(
                aiServiceUrl,
                request,
                String.class
        );
    }
}