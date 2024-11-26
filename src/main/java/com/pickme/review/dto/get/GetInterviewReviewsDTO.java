package com.pickme.review.dto.get;

import lombok.Data;

@Data
public class GetInterviewReviewsDTO {

    private String reviewId;

    private GetInterviewDetailDTO interviewDetail;

    private GetReviewDetailDTO reviewDetail;

}
