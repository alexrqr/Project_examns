package com.sistema.examenes.Services.ServiceImpl;

import com.sistema.examenes.Entity.Exam;
import com.sistema.examenes.Entity.Question;
import com.sistema.examenes.Repository.QuestionRepository;
import com.sistema.examenes.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Set<Question> listAllQuestions() {
        return (Set<Question>)  questionRepository.findAll();
    }

    // ObtenerPregunta
    @Override
    public Question findByQuestionId(Long questionId) {
        return questionRepository.findById(questionId).get();
    }

    @Override
    public Set<Question> obtenerPreguntasDelExamen(Exam exam) {
        return questionRepository.findByExamen(exam);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        Question question = new Question();
        question.setPreguntaId(questionId);
        questionRepository.delete(question);
    }

    @Override
    public Question listQuestion(Long questionId) {
        return this.questionRepository.getReferenceById(questionId);
    }

}
