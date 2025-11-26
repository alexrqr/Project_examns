package com.sistema.examenes.Controllers;

import com.sistema.examenes.Entity.Category;
import com.sistema.examenes.Payload.ApiResponse;
import com.sistema.examenes.Repository.CategoryRepository;
import com.sistema.examenes.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    /**
     * Guardado de registro
     * @param category
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Category>> guardarCategoria(@RequestBody Category category) {
        try{
            Category newCategory = categoryService.addCategory(category);

            return ResponseEntity.ok(
                    new ApiResponse<>("success", "Categorúa registrada correctamente", newCategory, null)
            );

        } catch (Exception e){
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("error", "No se pudo registrar la categoría", null, e.getMessage())
            );
        }
    }

    /**
     * Búsqueda de por ID
     * @param categoryId
     * @return
     */
    @GetMapping("/findById/{categoryId}")
    public ResponseEntity<ApiResponse<Category>> listCategoryById(@PathVariable("categoryId") Long categoryId){
        try {
            Category catFinded = categoryService.findById(categoryId);

            return ResponseEntity.ok(
                    new ApiResponse<>("success", "Categoría encontrada", catFinded, null)
            );

        } catch (Exception e){
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("error", "No se pudo encontrar la categoría", null, e.getMessage())
            );
        }
    }

    /**
     * List de categorías
     * @return
     */
    @GetMapping("/listExamns")
    public ResponseEntity<ApiResponse<Set<Category>>> listCategorys() {
        try {
            Set<Category> listCategorys = categoryService.listAllCategories();

            return ResponseEntity.ok(
                    new ApiResponse<>("success", "Categorías listadas", listCategorys, null)
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("error", "No se pudo listar categorías", null, e.getMessage())
            );
        }
    }


    /**
     * Actualización de categoría
     * @param category
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Category>> updatedCategory(@RequestBody Category category) {
        try {
            Category updated = categoryService.updateCategory(category);

            return ResponseEntity.ok(
                    new ApiResponse<>("success", "Categoría actualizada", updated, null)
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("error", "No se pudo actualizar la categoría", null, e.getMessage())
            );
        }
    }


    /**
     * Delete categoría
     * @param categoryId
     * @return
     */
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long categoryId) {
        try {
            categoryService.deleteCategory(categoryId);

            return ResponseEntity.ok(
                    new ApiResponse<>("success", "Categoría eliminada", null, null)
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("error", "No se pudo eliminar la categoría", null, e.getMessage())
            );
        }
    }



}
