package com.example.demo.service;


import com.example.demo.entity.JobLists;
import com.example.demo.repository.JabListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class JobListsService {

    private final JabListRepository repository;

    public List<JobLists> getAllJobs() {
        return repository.findAll();
    }
}