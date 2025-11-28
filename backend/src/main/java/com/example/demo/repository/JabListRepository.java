package com.example.demo.repository;

import com.example.demo.entity.JobLists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JabListRepository extends JpaRepository<JobLists, Long> {
}
