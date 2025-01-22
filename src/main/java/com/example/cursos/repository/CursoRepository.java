package com.example.cursos.repository;

import com.example.cursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByNameContainingIgnoreCase(String name);

    List<Curso> findByCategoryContainingIgnoreCase(String category);
}
