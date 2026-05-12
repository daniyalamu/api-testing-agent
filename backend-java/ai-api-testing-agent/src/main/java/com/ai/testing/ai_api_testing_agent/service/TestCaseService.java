package com.ai.testing.ai_api_testing_agent.service;

import com.ai.testing.ai_api_testing_agent.dto.GenerateTestCaseRequest;
import com.ai.testing.ai_api_testing_agent.entity.ApiTestCase;
import com.ai.testing.ai_api_testing_agent.repository.ApiTestCaseRepository;
import org.springframework.stereotype.Service;

@Service
public class TestCaseService {

    private final AiServiceClient aiServiceClient;
    private final ApiTestCaseRepository repository;

    public TestCaseService(
            AiServiceClient aiServiceClient,
            ApiTestCaseRepository repository
    ) {
        this.aiServiceClient = aiServiceClient;
        this.repository = repository;
    }

    public ApiTestCase generate(GenerateTestCaseRequest request) {

        String aiResponse =
                aiServiceClient.generateTestCases(request);

        ApiTestCase entity = new ApiTestCase();

        entity.setEndpoint(request.getEndpoint());
        entity.setMethod(request.getMethod());
        entity.setTestCases(aiResponse);

        return repository.save(entity);
    }
}