package com.ai.testing.ai_api_testing_agent.controller;

import com.ai.testing.ai_api_testing_agent.dto.GenerateTestCaseRequest;
import com.ai.testing.ai_api_testing_agent.entity.ApiTestCase;
import com.ai.testing.ai_api_testing_agent.service.TestCaseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test-cases")
public class TestCaseController {

    private final TestCaseService service;

    public TestCaseController(TestCaseService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public ApiTestCase generate(
            @RequestBody GenerateTestCaseRequest request
    ) {
        return service.generate(request);
    }
}
