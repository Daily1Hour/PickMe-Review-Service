package com.pickme.review.dto.get;

import lombok.Data;

@Data
public class GetSidebarDTO {
    private String reviewId;

    private InterviewDetailDTO interviewDetail;

    @Data
    public static class InterviewDetailDTO {
        private String companyName;

        private String category;
    }
}


