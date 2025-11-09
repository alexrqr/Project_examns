package com.sistema.examenes.Services.ServiceImpl;

import com.sistema.examenes.Entity.Category;
import com.sistema.examenes.Repository.CategoryRepository;
import com.sistema.examenes.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Set<Category> listAllCategories() {
        return new LinkedHashSet<>(categoryRepository.findAll());
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = new Category();
        category.setCategoriaId(categoryId);
        categoryRepository.delete(category);
    }
}
