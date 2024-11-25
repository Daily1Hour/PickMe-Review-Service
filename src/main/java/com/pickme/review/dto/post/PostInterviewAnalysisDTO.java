package com.pickme.review.dto.post;

import lombok.Data;

@Data
public class PostInterviewAnalysisDTO {

    private String strengths; // 면접에서 잘한 점

    private String improvements; // 개선해야 할 점

    private String feedback; // 면접에 대한 피드백

    private String difficulty; // 면접의 난이도 평가

    private String interviewResultAnalysis; // 면접 결과 분석(ex. 추가질문에 대한 대비 부족이 약점 <- 이 부분이 불합격에 영향을 줬을것이다 라고 분석)

}
