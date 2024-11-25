package com.pickme.review.service;

import com.pickme.review.dto.post.PostInterviewReviewsDTO;
import com.pickme.review.entity.Review;
import com.pickme.review.repository.ReviewRepository;
import com.pickme.review.service.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    // 사용자의 면접 리뷰 생성&추가
    public ResponseEntity<?> createInterviewReview(String clientId, PostInterviewReviewsDTO postInterviewReviewsDTO) {
        Review review;

        // 사용자의 면접 리뷰 정보가 있는 지 확인
        if(reviewRepository.existsByClientId(clientId)) {
            // 사용자의 면접 리뷰 정보가 있다면 해당 객체를 가져옴
            review = reviewRepository.findByClientId(clientId);
        } else {
            // 사용자의 면접 리뷰 정보가 없다면 새로운 Review 객체 생성
            review = new Review();
            review.setClientId(clientId); // 사용자 정보 설정
            review.setInterviewReviews(new ArrayList<>()); // 빈 면접 리뷰 리스트 초기화
        }

        // 새로운 InterviewReviews 객체 생성
        Review.InterviewReviews interviewReviews = new Review.InterviewReviews();

        // 면접 리뷰 고유 id 설정
        interviewReviews.setReviewId(UUID.randomUUID().toString());

        // 전달받은 DTO(PostInterviewReviews)를 interviewReviews 객체로 변환
        reviewMapper.PostInterviewReviewsToInterviewReviews(postInterviewReviewsDTO, interviewReviews);

        // 변환된 interviewReviews를 review의 interviewReviews 리스트에 추가
        review.getInterviewReviews().add(interviewReviews);

        // review 객체 데이터베이스에 저장
        reviewRepository.save(review);

        return ResponseEntity.status(HttpStatus.CREATED).body("면접 리뷰 추가 성공");

    }

}
