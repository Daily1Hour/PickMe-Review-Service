package com.pickme.review.service;

import com.pickme.review.dto.get.GetInterviewReviewsDTO;
import com.pickme.review.dto.get.GetReviewDTO;
import com.pickme.review.dto.get.GetSidebarDTO;
import com.pickme.review.dto.post.PostApiResponseDTO;
import com.pickme.review.dto.post.PostInterviewReviewsDTO;
import com.pickme.review.dto.put.PutApiResponseDTO;
import com.pickme.review.dto.put.PutInterviewReviewsDTO;
import com.pickme.review.entity.Review;
import com.pickme.review.repository.ReviewMongoQueryProcessor;
import com.pickme.review.repository.ReviewRepository;
import com.pickme.review.service.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewMongoQueryProcessor reviewMongoQueryProcessor;

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

        interviewReviews.setCreatedAt(new Date());

        interviewReviews.setUpdatedAt(new Date());

        // 전달받은 DTO(PostInterviewReviews)를 interviewReviews 객체로 변환
        reviewMapper.PostInterviewReviewsToInterviewReviews(postInterviewReviewsDTO, interviewReviews);

        // 변환된 interviewReviews를 review의 interviewReviews 리스트에 추가
        review.getInterviewReviews().add(interviewReviews);

        // review 객체 데이터베이스에 저장
        reviewRepository.save(review);

        PostApiResponseDTO postApiResponseDTO = new PostApiResponseDTO("true", "성공", interviewReviews.getReviewId());

        return ResponseEntity.status(HttpStatus.CREATED).body(postApiResponseDTO);

    }

    // clientId에 해당하는 면접 리뷰 조희
    public ResponseEntity<?> findInterviewReview (String clientId, String reviewId) {

        // 사용자의 면접 리뷰가 없다면
        if(!reviewRepository.existsByClientId(clientId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자 정보가 없습니다.");
        }

        // reviewId가 null이 아닌데 reviewId에 해당하는 리뷰가 없다면
        if (reviewId != null && !reviewRepository.existsByInterviewReviewsReviewId(reviewId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("reviewId에 해당하는 리뷰가 없습니다.");
        }

        // 사용자 면접 리뷰 객체 가져옴
        Review review = reviewRepository.findByClientId(clientId);

        // review 객체를 GetReviewDTO로 변환
        GetReviewDTO getReviewDTO = reviewMapper.reviewToGetReviewDTO(review);

        // 특정 reviewId에 해당하는 면접 리뷰 필터링 (조건이 없으면 전체 리뷰 반환)
        List<Review.InterviewReviews> interviewReviews = reviewMongoQueryProcessor.filterFindInterviewReviews(review, reviewId);

        // 필터링 된 interviewReviews 객체를 GetInterviewReviewsDTO로 변환
        List<GetInterviewReviewsDTO> getInterviewReviewsDTO = reviewMapper.interviewReviewsToGetInterviewReviewsDTO(interviewReviews);

        // 변환된 getInterviewReviewsDTO를 getReviewDTO의 interviewReviews 리스트에 추가
        getReviewDTO.setInterviewReviews(getInterviewReviewsDTO);

        // getReviewDTO를 반환
        return ResponseEntity.status(HttpStatus.OK).body(getReviewDTO);

    }

    // 면접 사이드 바 조회
    public ResponseEntity<?> findSidebar(String clientId) {
        // 사용자의 면접 리뷰가 없다면
        if(!reviewRepository.existsByClientId(clientId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자 정보가 없습니다.");
        }

        // 사용자 면접 리뷰 객체 가져옴
        Review review = reviewRepository.findByClientId(clientId);

        List<GetSidebarDTO> getSidebarDTO = reviewMapper.toGetSidebarDTO(review.getInterviewReviews());

        // getReviewDTO를 반환
        return ResponseEntity.status(HttpStatus.OK).body(getSidebarDTO);

    }

    // reviewId에 해당하는 리뷰 삭제
    public ResponseEntity<?> deleteReview(String clientId, String reviewId){
        // 사용자의 면접 리뷰가 없다면
        if(!reviewRepository.existsByClientId(clientId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자 정보가 없습니다.");
        }

        // reviewId에 해당하는 리뷰가 없다면
        if (!reviewRepository.existsByInterviewReviewsReviewId(reviewId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("reviewId에 해당하는 리뷰가 없습니다.");
        }

        // 사용자 면접 리뷰 객체 가져옴
        Review review = reviewRepository.findByClientId(clientId);

        return reviewMongoQueryProcessor.deleteReview(review, reviewId);
    }

    // 면접 리뷰 수정
    public ResponseEntity<?> updateReview(String clientId, String reviewId, PutInterviewReviewsDTO putInterviewReviewsDTO) {

        // 사용자의 면접 리뷰가 없다면
        if(!reviewRepository.existsByClientId(clientId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자 정보가 없습니다.");
        }

        // reviewId에 해당하는 리뷰가 없다면
        if (!reviewRepository.existsByInterviewReviewsReviewId(reviewId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("reviewId에 해당하는 리뷰가 없습니다.");
        }

        // 사용자 면접 리뷰 객체 가져옴
        Review review = reviewRepository.findByClientId(clientId);

        // reviewId에 해당하는 면접 리뷰를 갖고옴
        Review.InterviewReviews interviewReviews = reviewMongoQueryProcessor.findInterviewReview(review, reviewId);

        // 전달받은 DTO(PutInterviewReviewsDTO)를 interviewReviews 객체로 변환
        reviewMapper.putInterviewReviewsDTOToInterviewReviews(putInterviewReviewsDTO, interviewReviews);

        // 수정된 review 객체 저장
        reviewRepository.save(review);

        PutApiResponseDTO putApiResponseDTO = new PutApiResponseDTO("true","면접 리뷰 수정 성공", reviewId);

        return ResponseEntity.status(HttpStatus.OK).body(putApiResponseDTO);

    }

}
