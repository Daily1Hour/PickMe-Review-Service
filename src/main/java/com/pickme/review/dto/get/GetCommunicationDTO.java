package com.pickme.review.dto.get;

import lombok.Data;

@Data
public class GetCommunicationDTO {

    private String verbal; // 언어적 의사소통 (말투, 발음 등)

    private String nonVerbal; // 비언어적 의사소통 (제스처, 표정 등)

    private String interaction; // 면접관과의 상호작용 (대화의 흐름 등)

}
