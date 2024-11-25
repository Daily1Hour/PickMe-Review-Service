package com.pickme.review.controller;

import com.pickme.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final MongoTemplate mongoTemplate;

    @GetMapping("/")
    public String test(){
        Review review = new Review();

        // Setting clientId
        review.setClientId("user123");

        // Creating InterviewReviews
        Review.InterviewReviews interviewReview = new Review.InterviewReviews();
        interviewReview.setReviewId("review001");

        // Creating InterviewDetail
        Review.InterviewDetail interviewDetail = new Review.InterviewDetail();
        interviewDetail.setCompanyName("Tech Corp");
        interviewDetail.setPosition("Software Engineer");
        interviewDetail.setInterviewDateTime(LocalDateTime.of(2024, 11, 25, 14, 30)); // Sample date-time
        interviewDetail.setCategory("1st Technical Interview");

        interviewReview.setInterviewDetail(interviewDetail);

        // Creating ReviewDetail
        Review.ReviewDetail reviewDetail = new Review.ReviewDetail();

        // Creating Preparation
        Review.Preparation preparation = new Review.Preparation();
        preparation.setStrengths("Good understanding of algorithms and problem-solving skills.");
        preparation.setImprovements("Need to work on system design and communication.");

        reviewDetail.setPreparation(preparation);

        // Creating InterviewProcess
        Review.InterviewProcess interviewProcess = new Review.InterviewProcess();
        interviewProcess.setFormat("In-person");
        interviewProcess.setMood("Friendly and professional");
        interviewProcess.setPanel("Technical Lead, HR Manager");
        interviewProcess.setInterviewRatio("2:1");

        reviewDetail.setInterviewProcess(interviewProcess);

        // Creating QuestionsAnswers
        Review.QuestionsAnswers qa1 = new Review.QuestionsAnswers();
        qa1.setType("Technical Question");
        qa1.setQuestion("What is a binary search?");
        qa1.setAnswer("A binary search is an algorithm to find an item from a sorted list by repeatedly dividing the search interval in half.");
        qa1.setFeedback("Good answer but needs more clarity on time complexity.");

        Review.QuestionsAnswers qa2 = new Review.QuestionsAnswers();
        qa2.setType("Behavioral Question");
        qa2.setQuestion("Describe a challenging project you've worked on.");
        qa2.setAnswer("I worked on optimizing an algorithm for a large-scale dataset, which required innovative problem-solving.");
        qa2.setFeedback("Great answer with clear examples, but could improve on explaining the impact of the project.");

        reviewDetail.setQuestionsAnswers(Arrays.asList(qa1, qa2));

        // Creating Communication
        Review.Communication communication = new Review.Communication();
        communication.setVerbal("Clear and concise, but a bit nervous.");
        communication.setNonVerbal("Confident body language.");
        communication.setInteraction("Positive interaction with interviewers, but sometimes interrupted during explanations.");

        reviewDetail.setCommunication(communication);

        // Creating PostAnalysis
        Review.InterviewAnalysis interviewAnalysis = new Review.InterviewAnalysis();
        interviewAnalysis.setStrengths("Great problem-solving skills.");
        interviewAnalysis.setImprovements("Improve communication during technical discussions.");
        interviewAnalysis.setFeedback("The technical skills were strong, but communication could be improved.");
        interviewAnalysis.setDifficulty("Moderate difficulty.");
        interviewAnalysis.setInterviewResultAnalysis("The lack of clarity in explaining solutions may have impacted the final result.");

        reviewDetail.setInterviewAnalysis(interviewAnalysis);

        // Creating NextPreparation
        Review.NextPreparation nextPreparation = new Review.NextPreparation();
        nextPreparation.setTechnical("Work on system design and improve data structure knowledge.");
        nextPreparation.setExpression("Practice explaining technical concepts clearly and concisely.");
        nextPreparation.setAdditionalPractice("Review common system design patterns and practice mock interviews.");

        reviewDetail.setNextPreparation(nextPreparation);

        interviewReview.setReviewDetail(reviewDetail);

        // Adding the interview review to the list
        review.setInterviewReviews(Arrays.asList(interviewReview));

        mongoTemplate.save(review);

        return "테스트";
    }
}
