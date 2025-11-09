package com.sistema.examenes.Services;

import com.sistema.examenes.Entity.Exam;
import com.sistema.examenes.Entity.Question;

import java.util.Set;

public interface QuestionService {

    Question addQuestion(Question question);
    Question updateQuestion(Question question);
    Set<Question> listAllQuestions();
    Question findByQuestionId(Long questionId); // Obtener pregunta
    Set<Question> obtenerPreguntasDelExamen(Exam exam);
    void deleteQuestion(Long questionId);
    Question listQuestion(Long questionId);
}
