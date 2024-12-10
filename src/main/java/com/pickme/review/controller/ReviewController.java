package com.pickme.review.controller;

import com.pickme.review.dto.post.PostInterviewReviewsDTO;
import com.pickme.review.dto.put.PutInterviewReviewsDTO;
import com.pickme.review.service.ExternalApiService;
import com.pickme.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@Slf4j
@Tag(name = "Review", description = "면접 리뷰(회고) API")
@ApiResponse(responseCode = "400", description = "잘못된 요청")
@ApiResponse(responseCode = "401", description = "권한 없음")
@ApiResponse(responseCode = "404", description = "면접 리뷰 없음")
public class ReviewController {

    private final ReviewService reviewService;

    private final ExternalApiService externalApiService;

    // 면접 리뷰 생성&추가
    @Operation(summary = "면접 리뷰 생성&추가", description = "면접 리뷰 생성&추가")
    @ApiResponse(responseCode = "201", description = "면접 리뷰 생성&추가 성공")
    @PostMapping("/interview")
    public ResponseEntity<?> createReview(HttpServletRequest request,
                                          @RequestBody PostInterviewReviewsDTO postInterviewReviewsDTO) {

        String clientId = (String) request.getAttribute("clientId");

        return reviewService.createInterviewReview(clientId, postInterviewReviewsDTO);

    }

    // 면접 리뷰 조회
    @Operation(summary = "면접 리뷰 조회", description = "면접 리뷰 전체 조회 & 특정 조건에 해당하는 면접 리뷰 조회")
    @ApiResponse(responseCode = "200", description = "면접 리뷰 조회 요청 성공")
    @GetMapping("/interview")
    public ResponseEntity<?> findReview(HttpServletRequest request,
                                        @RequestParam(required = false) String reviewId) {

        String clientId = (String) request.getAttribute("clientId");

        return reviewService.findInterviewReview(clientId, reviewId);

    }

    // 면접 리뷰 삭제
    @Operation(summary = "면접 리뷰 삭제", description = "reviewId에 해당하는 면접 리뷰 삭제")
    @ApiResponse(responseCode = "200", description = "면접 리뷰 삭제 성공")
    @DeleteMapping("/interview")
    public ResponseEntity<?> deleteReview(HttpServletRequest request,
                                          @RequestParam String reviewId) {

        String clientId = (String) request.getAttribute("clientId");

        return reviewService.deleteReview(clientId, reviewId);
    }

    // 면접 리뷰 수정
    @Operation(summary = "면접 리뷰 수정", description = "reviewId에 해당하는 면접 리뷰 수정")
    @ApiResponse(responseCode = "200", description = "면접 리뷰 수정 성공")
    @PutMapping("/interview")
    public ResponseEntity<?> updateReview(HttpServletRequest request,
                                          @RequestParam String reviewId,
                                          @RequestBody PutInterviewReviewsDTO putInterviewReviewsDTO) {

        String clientId = (String) request.getAttribute("clientId");

        return reviewService.updateReview(clientId, reviewId, putInterviewReviewsDTO);

    }

    @GetMapping("/apiTest")
    public List<Map<String, String>> apiTest(HttpServletRequest request) {
        log.info(request.getHeader("Authorization"));
        return externalApiService.getExternalData();
    }

}
