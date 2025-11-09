package com.sistema.examenes.Repository;

import com.sistema.examenes.Entity.Exam;
import com.sistema.examenes.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    // busqueda por examen
    Set<Question> findByExamen(Exam exam);

}
