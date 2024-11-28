package com.pickme.review.dto.put;

import lombok.Data;

import java.util.List;

@Data
public class PutReviewDetailDTO {

    private PutPreparationDTO preparation; // 사전 준비, 면접 전 준비 사항 및 평가

    private PutInterviewProcessDTO interviewProcess; // 면접 진행 과정(대면, 비대면, 분위기 등)

    private List<PutQuestionsAnswersDTO> questionsAnswers; // 질문 타입, 질문, 답변, 피드백

    private PutCommunicationDTO communication; // 의사소통 (언어적, 비언어적)

    private PutInterviewAnalysisDTO interviewAnalysis; // 면접 후 분석(종합적 평가, 강점, 약점, 면접 전반에 대한 평가)

    private PutNextPreparationDTO nextPreparation; // 다음 면접을 위한 준비 사항 및 개선 사항

}
