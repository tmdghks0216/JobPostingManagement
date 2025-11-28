package com.example.demo.service;


import com.example.demo.code.JobCodeEnum;
import com.example.demo.dto.JobListsDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class CrawlingService {
    public List<JobListsDto> getJobs() throws IOException, JSONException {
        List<JobListsDto> jobs = new ArrayList<>();

        log.info(">>>>>> 크롤링 시작");

        for (JobCodeEnum jabCode : JobCodeEnum.values()) {
            log.info("job code: {}", jabCode);

            int page = 1;
            boolean stopForThisJobCode = false;

            while (!stopForThisJobCode) {
                String url = "https://www.saramin.co.kr/zf_user/jobs/list/job-category"
                        + "?cat_kewd=" + jabCode.getCode()
                        + "&loc_mcd=101000"
                        + "&page=" + page
                        + "&sort=RD"
                        + "&count=100";

                Connection.Response response = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) ")
                        .header("Accept", "application/json, text/javascript, */*; q=0.01")
                        .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8")
                        .header("X-Requested-With", "XMLHttpRequest")
                        .referrer("https://www.saramin.co.kr")
                        .timeout(10000)
                        .method(Connection.Method.GET)
                        .execute();

                String body = response.body();
                log.info("url: {}", url);

                // JSON 파싱
                JSONObject json = new JSONObject(body);

                String contents = json.optString("contents", "");
                if (contents == null || contents.isEmpty()) {
                    log.info(">> contents 비어 있음. 종료");
                    break;
                }

                // HTML 파싱
                Document document = Jsoup.parse(contents);
                Elements jobElements = document.select("div.list_item");

                for (Element jobElement : jobElements) {
                    // 공고 제목
                    String title = jobElement.select(".col.notification_info .job_tit a.str_tit").text();

                    if (title == null || title.isEmpty()) {
                        // 추천/스와이프/테마 영역 같은 비정상 list_item
                        continue;
                    }
                    // 회사명
                    String company = jobElement.select(".col.company_nm .str_tit").text();

                    // 근무지
                    String location = jobElement.select(".col.recruit_info .work_place").text();

                    // 마감일
                    String endDateText = jobElement.select(".col.support_info .support_detail .date").text();

                    // 등록일자
                    String deadlineText = jobElement.select(".col.support_info .support_detail .deadlines").text();

                    if (!isRegisteredToday(deadlineText)) {
                        log.info(">>>마지막 공고 {} 등록일자 {}",company,deadlineText);
                        log.info(">> 24시간 이전 공고 없음");
                        stopForThisJobCode = true;
                        break;
                    }

                    String detailLink = jobElement.select(".job_tit a").attr("href");
                    if (!detailLink.startsWith("http")) {
                        detailLink = "https://www.saramin.co.kr" + url;
                    }

                    JobListsDto job = JobListsDto.builder()
                            .title(title)
                            .company(company)
                            .location(location)
                            .endDate(endDateText)
                            .detailLink(detailLink)
                            .date(deadlineText)
                            .jobCode(jabCode.getCode())
                            .build();

                    jobs.add(job);
                }
                page++;
            }
        }
        return jobs;
    }

    private boolean isRegisteredToday(String text) {

        if (text == null || text.isEmpty()){
            return false;
        }

        Pattern minutePattern = Pattern.compile("(\\d+)분 전");
        Matcher minuteMatcher = minutePattern.matcher(text);
        if (minuteMatcher.find()){
            return true;
        }

        Pattern hourPattern = Pattern.compile("(\\d+)시간 전");
        Matcher hourMatcher = hourPattern.matcher(text);
        if (hourMatcher.find()){
            return true;
        }

        return false;
    }
}
