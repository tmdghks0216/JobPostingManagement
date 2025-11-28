package com.example.demo.batch;


import com.example.demo.dto.JobListsDto;
import com.example.demo.entity.JobLists;
import com.example.demo.repository.JabListRepository;
import com.example.demo.service.JobListsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class JobListsBatchService {

    private final JobListsService jobListsService;
    private final JabListRepository jabListRepository;

    @Scheduled(cron = "0 5 0 * * *")
    @Transactional
    public void JobCrawlerBatch() throws IOException {
        log.info("===== 채용공고 배치 시작 =====");

        List<JobListsDto> dtos = jobListsService.getJobs();

        if (dtos.isEmpty()) {
            log.info("오늘 신규 공고 없음 → 저장 스킵");
            return;
        }

        List<JobLists> entities = dtos.stream()
                .map(JobLists::from)
                .collect(Collectors.toList());

        jabListRepository.saveAll(entities);

        log.info(">>> {}건 저장 완료", entities.size());
        log.info("===== 채용공고 배치 종료 =====");
    }

}
