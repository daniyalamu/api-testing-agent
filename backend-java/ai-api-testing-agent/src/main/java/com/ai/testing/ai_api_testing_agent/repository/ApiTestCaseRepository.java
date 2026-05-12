package com.ai.testing.ai_api_testing_agent.repository;

import com.ai.testing.ai_api_testing_agent.entity.ApiTestCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiTestCaseRepository
        extends JpaRepository<ApiTestCase, Long> {
}