package com.pickme.review.dto.put;

import lombok.Data;

@Data
public class PutNextPreparationDTO {

    private String technical; // 기술적 준비 사항 (예: 특정 기술 스킬 향상 등)

    private String expression; // 표현적 준비 사항 (답변의 표현, 제스처, 표정 등)

    private String additionalPractice; // 추가적으로 연습이 필요한 부분

}
