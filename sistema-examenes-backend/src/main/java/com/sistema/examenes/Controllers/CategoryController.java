package com.sistema.examenes.Controllers;

import com.sistema.examenes.Entity.Category;
import com.sistema.examenes.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/add")
    public ResponseEntity<Category> guardarCategoria(@RequestBody Category category) {
        Category newCategory = categoryRepository.save(category);

        return ResponseEntity.ok().body(newCategory);
    }


}
