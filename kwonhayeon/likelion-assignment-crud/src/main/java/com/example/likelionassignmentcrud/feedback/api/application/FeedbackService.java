package com.example.likelionassignmentcrud.feedback.api.application;

import com.example.likelionassignmentcrud.feedback.api.dto.request.FeedbackSaveRequestDto;
import com.example.likelionassignmentcrud.feedback.api.dto.response.FeedbackInfoResponseDto;
import com.example.likelionassignmentcrud.feedback.api.dto.response.FeedbackListResponseDto;
import com.example.likelionassignmentcrud.feedback.api.domain.model.Feedback;
import com.example.likelionassignmentcrud.feedback.api.domain.repository.FeedbackRepository;
import com.example.likelionassignmentcrud.session.domain.model.Session;
import com.example.likelionassignmentcrud.session.domain.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeedbackService {

    private final SessionRepository sessionRepository;
    private final FeedbackRepository feedbackRepository;

    // 피드백 저장
    @Transactional
    public void feedbackSave(FeedbackSaveRequestDto dto) {
        Session session = sessionRepository.findById(dto.sessionId())
                .orElseThrow(IllegalArgumentException::new);

        Feedback feedback = Feedback.builder()
                .message(dto.message())
                .createdAt(LocalDateTime.now())
                .session(session)
                .build();

        feedbackRepository.save(feedback);
    }

    // 전체 피드백 조회
    public FeedbackListResponseDto feedbackFindAll() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        List<FeedbackInfoResponseDto> dtos = feedbacks.stream()
                .map(FeedbackInfoResponseDto::from)
                .toList();
        return FeedbackListResponseDto.from(dtos);
    }

    // 특정 세션의 피드백 목록 조회
    public FeedbackListResponseDto feedbackFindBySession(Long sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(IllegalArgumentException::new);

        List<Feedback> feedbacks = feedbackRepository.findBySession(session);
        List<FeedbackInfoResponseDto> dtos = feedbacks.stream()
                .map(FeedbackInfoResponseDto::from)
                .toList();

        return FeedbackListResponseDto.from(dtos);
    }
}