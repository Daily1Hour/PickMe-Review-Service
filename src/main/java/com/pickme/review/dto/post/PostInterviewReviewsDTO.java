package com.pickme.review.dto.post;

import lombok.Data;

@Data
public class PostInterviewReviewsDTO {

    private PostInterviewDetailDTO interviewDetail; // 면접 기본 정보(회사명, 지원 직무, 면접 시간, 면접 유형)

    private PostReviewDetailDTO reviewDetail; // 면접 회고에 포함된 세부 항목

}
