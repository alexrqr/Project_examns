package com.sistema.examenes.Repository;

import com.sistema.examenes.Entity.Category;
import com.sistema.examenes.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    // Metodos de filtrado
    List<Exam> findByCategoria(Category category);
    List<Exam> findByStatus(Boolean estado); // Busqueda por active...
    List<Exam> findByCategoriaAndStatus(Category category, Boolean estado); // busqueda por categoria y estado

}
