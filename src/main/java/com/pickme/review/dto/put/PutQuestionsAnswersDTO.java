package com.pickme.review.dto.put;

import lombok.Data;

@Data
public class PutQuestionsAnswersDTO {

    private String type; // 질문 유형 (기술 질문, 인성 질문 등)

    private String question; // 면접 질문

    private String answer; // 질문에 대한 답변

    private String feedback; // 답변에 대한 피드백

}
