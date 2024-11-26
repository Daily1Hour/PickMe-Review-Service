package com.pickme.review.controller;

import com.pickme.review.dto.post.PostInterviewReviewsDTO;
import com.pickme.review.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    // 면접 리뷰 생성&추가
    @PostMapping("/interview")
    public ResponseEntity<?> createReview (HttpServletRequest request,
                                           @RequestBody PostInterviewReviewsDTO postInterviewReviewsDTO){

        String clientId = (String) request.getAttribute("clientId");

        return reviewService.createInterviewReview(clientId, postInterviewReviewsDTO);

    }

}
