package com.pickme.review.repository;

import com.pickme.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewMongoQueryProcessor {

    private final ReviewRepository reviewRepository;

    // 필터링된 면접 리뷰 목록 가져오는 메서드
    public List<Review.InterviewReviews> filterFindInterviewReviews(Review review,
                                                                    String reviewId) {

        return review.getInterviewReviews().stream()
                .filter(interviewReview -> reviewId == null || interviewReview.getReviewId().equals(reviewId))
                .toList();

    }

    // 사용자의 reviewId에 해당하는 일정 가져오는 메서드
    public Review.InterviewReviews findInterviewReview(Review review, String reviewId) {
        return review.getInterviewReviews().stream()
                .filter(interviewReview -> interviewReview.getReviewId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    public ResponseEntity<?> deleteReview(Review review, String reviewId) {
        // reviewId에 해당하는 면접 리뷰를 갖고옴
        Review.InterviewReviews interviewReview = findInterviewReview(review, reviewId);
        // 해당 리뷰를 삭제
        review.getInterviewReviews().remove(interviewReview);
        // 변경된 review 저장
        reviewRepository.save(review);

        return ResponseEntity.status(HttpStatus.OK).body(String.format("%s 에 해당하는 면접 리뷰를 삭제했습니다.", reviewId));

    }


}
