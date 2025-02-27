package com.pickme.review.dto.get;

import lombok.Data;

import java.util.Date;

@Data
public class GetInterviewReviewsDTO {

    private String reviewId;

    private Date createdAt;

    private Date updatedAt;

    private GetInterviewDetailDTO interviewDetail;

    private GetReviewDetailDTO reviewDetail;

}
