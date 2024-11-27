package com.pickme.review.dto.get;

import lombok.Data;

import java.util.List;

@Data
public class GetReviewDetailDTO {

    private GetPreparationDTO preparation;

    private GetInterviewProcessDTO interviewProcess;

    private List<GetQuestionsAnswersDTO> questionsAnswers;

    private GetCommunicationDTO communication;

    private GetInterviewAnalysisDTO interviewAnalysis;

    private GetNextPreparationDTO nextPreparation;

}
