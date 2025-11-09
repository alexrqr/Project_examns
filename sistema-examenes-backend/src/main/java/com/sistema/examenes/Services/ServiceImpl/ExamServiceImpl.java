package com.sistema.examenes.Services.ServiceImpl;

import com.sistema.examenes.Entity.Category;
import com.sistema.examenes.Entity.Exam;
import com.sistema.examenes.Repository.ExamRepository;
import com.sistema.examenes.Services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Exam agregarExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public Exam actualizarExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public Set<Exam> obtenerExamenes() {
        return new LinkedHashSet<>(examRepository.findAll());
    }

    @Override
    public Exam obtenerExamen(Long examenId) {
        return examRepository.findById(examenId).get();
    }

    @Override
    public void eliminarExamen(Long examenId) {
        Exam examen = new Exam();
        examen.setExamenId(examenId);
        examRepository.delete(examen);
    }

    @Override
    public List<Exam> listExamByCategory(Category category) {
        return this.examRepository.findByCategoria(category);
    }

    @Override
    public List<Exam> listAllExamenesActives() {
        return examRepository.findByStatus(true);
    }

    @Override
    public List<Exam> listExamActivesByCategory(Category category) {
        return examRepository.findByCategoriaAndStatus(category, true);
    }


}
