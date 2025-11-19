package com.sistema.examenes.Repository;

import com.sistema.examenes.Entity.Category;
import com.sistema.examenes.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    // Metodos de filtrado
    List<Exam> findByCategory(Category category);
    List<Exam> findByActive(Boolean active);
    List<Exam> findByCategoryAndActive(Category category, Boolean active);
    // busqueda por categoria y estado

}
