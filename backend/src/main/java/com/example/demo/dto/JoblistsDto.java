package com.example.demo.dto;

import lombok.Data;

@Data
public class JoblistsDto {

    private String title;
    private String company;
    private String location;
    private String date;
    private String detailLink;
    private String jobCode;

    public JoblistsDto(String title, String company, String location, String date, String detailLink, String jobCode) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.date = date;
        this.detailLink = detailLink;
        this.jobCode = jobCode;
    }
}