package com.pickme.review.dto.put;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PutInterviewDetailDTO {

    private String companyName; // 면접을 본 회사의 이름

    private String position; // 지원한 직무명

    private LocalDateTime interviewDateTime; // 면접 날짜/시간

    private String category; // 면접 유형(1차 기술면접, 2차 인성면접 등)

}
