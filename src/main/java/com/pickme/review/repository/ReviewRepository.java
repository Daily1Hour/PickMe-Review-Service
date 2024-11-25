package com.pickme.review.repository;

import com.pickme.review.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

    // 해당 사용자의 저장된 면접 리뷰가 있는지 확인
    boolean existsByClientId(String clientId);

    // 해당 사용자 면접 리뷰 정보 추출
    Review findByClientId(String clientId);

}
