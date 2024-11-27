package com.pickme.review.dto.put;

import lombok.Data;

@Data
public class PutInterviewProcessDTO {

    private String format; // 면접 방식(대면/비대면 등)

    private String mood; // 면접 분위기

    private String panel; // 면접관 정보 (실무자, HR 담당자 등)

    private String interviewRatio; // 면접자 : 면접관 비율

}
