package com.pickme.review.service.mapper;

import com.pickme.review.dto.post.PostInterviewReviewsDTO;
import com.pickme.review.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring") // spring bean으로 등록
public interface ReviewMapper {

    // 전달받은 DTO(PostInterviewReviews)를 interviewReviews 객체로 변환
    @Mapping(target = "clientId", ignore = true) // clientId 필드는 매핑에서 제외 (ignore 설정)
    void PostInterviewReviewsToInterviewReviews(PostInterviewReviewsDTO postInterviewReviewsDTO, @MappingTarget Review.InterviewReviews interviewReviews);

}
