package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobListsDto {

    private String title;
    private String company;
    private String location;
    private String date;
    private String endDate;
    private String detailLink;
    private String jobCode;

}