package com.pickme.review.dto.put;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PutApiResponseDTO {
    @Schema(description = "success", example = "true")
    private String success;
    @Schema(description = "message", example = "면접 리뷰 수정 성공")
    private String message;
    @Schema(description = "interviewDetailId", example = "fd3c55f5-07fe-4d0c-94d3-69cbaeb2646c")
    private String interviewDetailId;
}
