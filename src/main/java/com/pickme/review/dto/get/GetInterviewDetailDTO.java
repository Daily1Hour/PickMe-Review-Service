package com.pickme.review.dto.get;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetInterviewDetailDTO {

    private String companyName;

    private String position;

    private LocalDateTime interviewDateTime;

    private String category;

}
