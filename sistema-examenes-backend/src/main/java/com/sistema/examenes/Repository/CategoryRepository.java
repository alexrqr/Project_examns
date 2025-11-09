package com.sistema.examenes.Repository;

import com.sistema.examenes.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
