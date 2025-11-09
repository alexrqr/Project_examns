package com.sistema.examenes.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "examenes")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examenId;

    private String title;
    private String description;
    private String puntosMaximos;
    private boolean active = false;

    // Relacion a categoría:
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    // Relacion de examen a preguntas
    @OneToMany(mappedBy = "examen", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();

    // Get y set:
    public Long getExamenId() {
        return examenId;
    }

    public void setExamenId(Long examenId) {
        this.examenId = examenId;
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

    public String getPuntosMaximos() {
        return puntosMaximos;
    }

    public void setPuntosMaximos(String puntosMaximos) {
        this.puntosMaximos = puntosMaximos;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    // Constructors
    public Exam() {}

    public Exam(Long examenId, String title, String description, String puntosMaximos, boolean active, Category category, Set<Question> questions) {
        this.examenId = examenId;
        this.title = title;
        this.description = description;
        this.puntosMaximos = puntosMaximos;
        this.active = active;
        this.category = category;
        this.questions = questions;
    }
}
