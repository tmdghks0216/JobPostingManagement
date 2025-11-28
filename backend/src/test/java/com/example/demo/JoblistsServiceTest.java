package com.example.demo;

import com.example.demo.dto.JobListsDto;
import com.example.demo.entity.JobLists;
import com.example.demo.repository.JabListRepository;
import com.example.demo.service.CrawlingService;
import com.example.demo.service.JobListsService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class JobListsServiceTest {

    @Autowired
    private CrawlingService crawlingService;

    @Autowired
    private JabListRepository jabListRepository;

    @Test
    void jobListsService() throws IOException, JSONException {
        List<JobListsDto> jobs = crawlingService.getJobs();

        if (jobs == null || jobs.isEmpty()) {
            log.info("데이터 없음");
            return;
        }

        List<JobLists> entities = jobs.stream()
                .map(JobLists::from)
                .collect(Collectors.toList());

        jabListRepository.saveAll(entities);
    }
}
