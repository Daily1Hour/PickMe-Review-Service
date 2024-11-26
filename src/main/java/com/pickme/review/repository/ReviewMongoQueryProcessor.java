package com.pickme.review.repository;

import com.pickme.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewMongoQueryProcessor {

    // 면접 리뷰 목록 가져오는 메서드
    public List<Review.InterviewReviews> filterFindInterviewReviews(Review review,
                                                                    String reviewId) {

        return review.getInterviewReviews().stream()
                .filter(interviewReview -> reviewId == null || interviewReview.getReviewId().equals(reviewId))
                .toList();

    }

}
