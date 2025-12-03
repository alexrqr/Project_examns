package com.sistema.examenes.Controllers;

import com.sistema.examenes.Entity.Exam;
import com.sistema.examenes.Payload.ApiResponse;
import com.sistema.examenes.Services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/examn")
@CrossOrigin("*")
public class ExamController {
    @Autowired
    private ExamService examService;

    /**
     * Registro de examen
     * @param exam
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Exam>> addExam(@RequestBody Exam exam) {
        try {

            Exam newExam = examService.agregarExam(exam);

            return ResponseEntity.ok(
                    new ApiResponse<>("success", "Examen registrado correctamente", newExam, null)
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("error", "No se pudo registrar el examen", null, e.getMessage())
            );
        }
    }

    /**
     * Actualizar examen
     * @param exam
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Exam>> updatedExamn(@RequestBody Exam exam) {
        try {

            Exam examnUpdated = examService.actualizarExam(exam);

            return ResponseEntity.ok(
                    new ApiResponse<>("success", "Examen actualizadao", examnUpdated, null)
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("error", "No se pudo actualizar el examen", null, e.getMessage())
            );
        }
    }

    /**
     * Lista de examenes
     * @return
     */
    @GetMapping("/listExam")
    public ResponseEntity<ApiResponse<Set<Exam>>> listAllExamns() {
        try {

            Set<Exam> listExamns = examService.obtenerExamenes();

            return ResponseEntity.ok(
                    new ApiResponse<>("success", "Lista de examenes", listExamns, null)
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("error", "No se pudo listar examen", null, e.getMessage())
            );
        }
    }

    /**
     * Busqueda de examen por id
     * @param examId
     * @return
     */
    @GetMapping("/findById/{examId}")
    public ResponseEntity<ApiResponse<Exam>> examFindById (@PathVariable("examId") Long examId){
        try {

            Exam examFinded = examService.obtenerExamen(examId);

            return ResponseEntity.ok(
                    new ApiResponse<>("success", "Examen encontrado", examFinded, null)
            );

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ApiResponse<>("error", "No se pudo encontrar el examen", null, e.getMessage())
            );
        }
    }


}
