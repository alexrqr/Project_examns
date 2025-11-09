package com.sistema.examenes.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "categorias")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;

    private String title;
    private String description;

    // Relacion de categoría a examen
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Exam> examns = new LinkedHashSet<>();


    // Get y set
    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Exam> getExamns() {
        return examns;
    }

    public void setExamns(Set<Exam> examns) {
        this.examns = examns;
    }

    // Constructor:
    public Category() {}

    public Category(Long categoriaId, String title, String description, Set<Exam> examns) {
        this.categoriaId = categoriaId;
        this.title = title;
        this.description = description;
        this.examns = examns;
    }
}
