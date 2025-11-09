package com.sistema.examenes.Services;

import com.sistema.examenes.Entity.Category;

import java.util.Set;

public interface CategoryService {

    // Metodos CRUD y filter Categorías..
    Category addCategory(Category category);

    Category updateCategory(Category category);
    // obtenerCategorys
    Set<Category> listAllCategories();
    // obtenerCategory
    Category findById(Long categoryId);

    void deleteCategory(Long categoryId);


}
