package com.sistema.examenes.Services;

import com.sistema.examenes.Entity.Category;
import com.sistema.examenes.Entity.Exam;

import java.util.List;
import java.util.Set;

public interface ExamService {

    Exam agregarExam(Exam exam);

    Exam actualizarExam(Exam exam);

    Set<Exam> obtenerExamenes();

    Exam obtenerExamen(Long examenId);

    void eliminarExamen(Long examenId);

    List<Exam> listExamByCategory(Category category);
    List<Exam> listAllExamenesActives();
    List<Exam> listExamActivesByCategory(Category category);


}
