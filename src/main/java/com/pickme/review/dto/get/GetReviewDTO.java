package com.pickme.review.dto.get;

import lombok.Data;

import java.util.List;

@Data
public class GetReviewDTO {

    private String id;

    private String clientId;

    private List<GetInterviewReviewsDTO> interviewReviews;

}
