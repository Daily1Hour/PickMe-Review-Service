package com.pickme.review.dto.put;

import lombok.Data;

@Data
public class PutInterviewAnalysisDTO {

    private String strengths; // 면접에서 잘한 점

    private String improvements; // 개선해야 할 점

    private String feedback; // 면접에 대한 피드백

    private String difficulty; // 면접의 난이도 평가

    private String interviewResultAnalysis; // 면접 결과 분석

}
