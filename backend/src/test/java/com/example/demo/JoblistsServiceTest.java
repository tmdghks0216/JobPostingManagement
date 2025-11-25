package com.example.demo;

import com.example.demo.dto.JoblistsDto;
import com.example.demo.service.JobListsService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
class JoblistsServiceTest {

    @Autowired
    private JobListsService jobListsService;

    @Test
    void joblistsService() throws IOException, JSONException {
        List<JoblistsDto> jobs = jobListsService.getJobs();

        // 3) 로그 출력
        jobs.forEach(job -> log.info("{}", job));
    }
}
