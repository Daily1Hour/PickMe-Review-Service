package com.pickme.review.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/*
// 클래스에 대한 접근 제어자와 static 키워드에 대한 설명

// public: 이 클래스는 외부에서 접근할 수 있습니다. 즉, 객체를 생성하여 다른 클래스에서 사용할 수 있습니다.
// private: 이 클래스는 해당 클래스가 정의된 범위 내에서만 접근 가능합니다. 외부에서는 사용할 수 없습니다.

// static: static 키워드는 클래스의 인스턴스를 생성하지 않고 클래스 자체에 속한 멤버로 정의하는 데 사용됩니다.
// static으로 정의된 클래스나 메서드는 해당 클래스의 인스턴스를 생성하지 않고도 사용할 수 있습니다.
// 예를 들어, `Review.InterviewReviews`와 같이 외부에서 `Review` 클래스의 인스턴스를 생성하지 않고도 `InterviewReviews`를 사용할 수 있습니다.
// 반면, static이 아닌 경우에는 반드시 `Review` 클래스를 먼저 생성하고 해당 객체를 통해 `InterviewReviews`에 접근해야 합니다.
*/

/*
 * 클래스 설명:
 * 이 클래스는 면접 리뷰(회고) 정보를 MongoDB에 저장하기 위한 도메인 모델입니다.
 * 리뷰는 사용자가 작성한 면접 후 회고 및 피드백을 포함하며, 각 면접은
 * 회사명, 지원 직무, 면접 시간, 면접 유형 등의 세부 정보를 포함합니다.
 */

@Document(collection = "review")
@Data
@NoArgsConstructor
public class Review {

    @Id
    private String id; // MongoDB에서 자동 생성되는 고유 id

    private String clientId; // 리뷰를 작성한 사용자의 고유 ID

    private List<InterviewReviews> interviewReviews; // 사용자가 작성한 면접 리뷰(회고) 리스트

    // 면접 리뷰 클래스 - 면접 하나에 대한 리뷰와 세부사항을 포함
    @Data
    @NoArgsConstructor
    public static class InterviewReviews {

        private String reviewId; // 면접 리뷰 고유 id

        private InterviewDetail interviewDetail; // 면접 기본 정보(회사명, 지원 직무, 면접 시간, 면접 유형)

        private ReviewDetail reviewDetail; // 면접 회고에 포함된 세부 항목 리스트

    }

    // 면접의 기본 정보 클래스 - 회사명, 직무, 면접 시간, 면접 유형 등을 포함
    @Data
    @NoArgsConstructor
    public static class InterviewDetail { // 면접 세부 내용

        private String companyName; // 면접을 본 회사의 이름

        private String position; // 지원한 직무명

        private LocalDateTime interviewDateTime; // 면접 날짜/시간

        private String category; // 면접 유형(1차 기술면접, 2차 인성면접 등)

    }

    // 면접 리뷰의 세부 항목 클래스 - 사전 준비, 면접 진행 과정, 질문/답변, 의사소통, 후속 분석, 개선 사항 등을 포함
    @Data
    @NoArgsConstructor
    public static class ReviewDetail { // 면접 리뷰 세부 내용

        private Preparation preparation; // 사전 준비, 면접 전 준비 사항 및 평가

        private InterviewProcess interviewProcess; // 면접 진행 과정(대면, 비대면, 분위기 등)

        private List<QuestionsAnswers> questionsAnswers; // 질문 타입, 질문, 답변, 피드백

        private Communication communication; // 의사소통 (언어적, 비언어적)

        private PostAnalysis postAnalysis; // 면접 후 분석(종합적 평가, 강점, 약점, 면접 전반에 대한 평가)

        private NextPreparation nextPreparation; // 다음 면접을 위한 준비 사항 및 개선 사항
    }

    // 면접 전 준비 사항 - 잘한 점, 개선할 점 등
    @Data
    @NoArgsConstructor
    public static class Preparation { // 사전 준비

        private String strengths; // 면접 준비에서 잘한 점

        private String improvements; // 개선해야 할 점

    }

    // 면접 진행 과정에 대한 정보 - 면접의 형식, 분위기, 면접관, 면접자/면접관 비율 등
    @Data
    @NoArgsConstructor
    public static class InterviewProcess { // 면접 진행 과정

        private String format; // 면접 방식(대면/비대면 등)

        private String mood; // 면접 분위기

        private String panel; // 면접관 정보 (실무자, HR 담당자 등)

        private String interviewRatio; // 면접자 : 면접관 비율

    }

    // 면접에서 주어진 질문과 그에 대한 답변, 피드백에 대한 정보
    @Data
    @NoArgsConstructor
    public static class QuestionsAnswers { // 질문 타입, 질문, 답변, 피드백

        private String type; // 질문 유형 (기술 질문, 인성 질문 등)

        private String question; // 면접 질문

        private String answer; // 질문에 대한 답변

        private String feedback; // 답변에 대한 피드백

    }

    // 면접 중 의사소통 방식에 대한 평가 - 언어적, 비언어적, 면접관과의 상호작용 등
    @Data
    @NoArgsConstructor
    public static class Communication { // 의사소통

        private String verbal; // 언어적 의사소통 (말투, 발음 등)

        private String nonVerbal; // 비언어적 의사소통 (제스처, 표정 등)

        private String interaction; // 면접관과의 상호작용 (대화의 흐름 등)

    }

    // 면접 후 분석 및 종합적 평가 - 면접에서 잘한 점, 개선할 점, 면접 결과 분석 등
    @Data
    @NoArgsConstructor
    public static class PostAnalysis { // 면접 후 분석(종합적 평가, 면접 전반에 대한 평가)

        private String strengths; // 면접에서 잘한 점

        private String improvements; // 개선해야 할 점

        private String feedback; // 면접에 대한 피드백

        private String difficulty; // 면접의 난이도 평가

        private String resultAnalysis; // 면접 결과 분석(ex. 추가질문에 대한 대비 부족이 약점 <- 이 부분이 불합격에 영향을 줬을것이다 라고 분석)

    }

    // 다음 면접을 위한 준비 사항 - 기술적 부분, 표현적 부분, 추가 연습 사항 등
    @Data
    @NoArgsConstructor
    public static class NextPreparation { // 다음 면접을 위한 준비 사항 및 개선 사항

        private String technical; // 기술적 준비 사항 (예: 특정 기술 스킬 향상 등)

        private String expression; // 표현적 준비 사항 (답변의 표현, 제스처, 표정 등)

        private String additionalPractice; // 추가적으로 연습이 필요한 부분

    }

}
