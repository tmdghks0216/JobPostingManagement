package com.example.demo.entity;

import com.example.demo.dto.JobListsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "JOBLISTS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JobLists {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "COMPANY", nullable = false)
    private String company;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "END_DATE")
    private String endDate;

    @Column(name = "DETAIL_LINK")
    private String detailLink;

    @Column(name = "JOB_CODE")
    private String jobCode;

    public static JobLists from(JobListsDto dto) {
        return JobLists.builder()
                .title(dto.getTitle())
                .company(dto.getCompany())
                .location(dto.getLocation())
                .endDate(dto.getEndDate())
                .detailLink(dto.getDetailLink())
                .jobCode(dto.getJobCode())
                .build();
    }
}