package com.example.demo.controller;

import com.example.demo.entity.JobLists;
import com.example.demo.service.JobListsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JobListsController {

    private final JobListsService service;

    @GetMapping("/jobList")
    public List<JobLists> getAllJobLists() {
        return service.getAllJobs();
    }
}