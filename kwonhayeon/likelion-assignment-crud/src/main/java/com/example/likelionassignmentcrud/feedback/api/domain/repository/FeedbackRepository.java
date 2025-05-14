package com.example.likelionassignmentcrud.feedback.api.domain.repository;

import com.example.likelionassignmentcrud.feedback.api.domain.model.Feedback;
import com.example.likelionassignmentcrud.session.domain.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findBySession(Session session);
}
