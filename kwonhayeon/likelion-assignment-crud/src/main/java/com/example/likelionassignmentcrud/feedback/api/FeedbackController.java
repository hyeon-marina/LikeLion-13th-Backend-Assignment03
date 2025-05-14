package com.example.likelionassignmentcrud.feedback.api;

import com.example.likelionassignmentcrud.feedback.api.dto.request.FeedbackSaveRequestDto;
import com.example.likelionassignmentcrud.feedback.api.dto.response.FeedbackListResponseDto;
import com.example.likelionassignmentcrud.feedback.api.application.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    // 피드백 저장
    @PostMapping
    public ResponseEntity<String> feedbackSave(@RequestBody FeedbackSaveRequestDto dto) {
        feedbackService.feedbackSave(dto);
        return new ResponseEntity<>("피드백 저장 완료!", HttpStatus.CREATED);
    }

    // 전체 피드백 조회
    @GetMapping
    public ResponseEntity<FeedbackListResponseDto> feedbackFindAll() {
        FeedbackListResponseDto responseDto = feedbackService.feedbackFindAll();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 특정 세션의 피드백 목록 조회
    @GetMapping("/session/{sessionId}")
    public ResponseEntity<FeedbackListResponseDto> feedbackFindBySession(@PathVariable Long sessionId) {
        FeedbackListResponseDto responseDto = feedbackService.feedbackFindBySession(sessionId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}