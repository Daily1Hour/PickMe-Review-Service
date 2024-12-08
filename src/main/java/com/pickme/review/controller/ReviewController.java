package com.pickme.review.controller;

import com.pickme.review.dto.post.PostInterviewReviewsDTO;
import com.pickme.review.dto.put.PutInterviewReviewsDTO;
import com.pickme.review.service.ExternalApiService;
import com.pickme.review.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    private final ExternalApiService externalApiService;

    // 면접 리뷰 생성&추가
    @PostMapping("/interview")
    public ResponseEntity<?> createReview(HttpServletRequest request,
                                          @RequestBody PostInterviewReviewsDTO postInterviewReviewsDTO) {

        String clientId = (String) request.getAttribute("clientId");

        return reviewService.createInterviewReview(clientId, postInterviewReviewsDTO);

    }

    // 면접 리뷰 조회
    @GetMapping("/interview")
    public ResponseEntity<?> findReview(HttpServletRequest request,
                                        @RequestParam(required = false) String reviewId) {

        String clientId = (String) request.getAttribute("clientId");

        return reviewService.findInterviewReview(clientId, reviewId);

    }

    // 면접 리뷰 삭제
    @DeleteMapping("/interview")
    public ResponseEntity<?> deleteReview(HttpServletRequest request,
                                          @RequestParam String reviewId) {

        String clientId = (String) request.getAttribute("clientId");

        return reviewService.deleteReview(clientId, reviewId);
    }

    // 면접 리뷰 수정
    @PutMapping("/interview")
    public ResponseEntity<?> updateReview(HttpServletRequest request,
                                          @RequestParam String reviewId,
                                          @RequestBody PutInterviewReviewsDTO putInterviewReviewsDTO) {

        String clientId = (String) request.getAttribute("clientId");

        return reviewService.updateReview(clientId, reviewId, putInterviewReviewsDTO);

    }

    @GetMapping("/apiTest")
    public List<Map<String, String>> apiTest() {
        return externalApiService.getExternalData();
    }

}
