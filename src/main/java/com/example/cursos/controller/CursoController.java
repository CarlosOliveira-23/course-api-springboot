package com.example.cursos.controller;

import com.example.cursos.dto.CursoDTO;
import com.example.cursos.model.Curso;
import com.example.cursos.service.CursoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<Curso> criarCurso(@RequestBody CursoDTO dto) {
        Curso curso = cursoService.criarCurso(dto);
        return ResponseEntity.ok(curso);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {
        return ResponseEntity.ok(cursoService.listarCursos(name, category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(
            @PathVariable Long id,
            @RequestBody CursoDTO dto) {
        return ResponseEntity.ok(cursoService.atualizarCurso(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerCurso(@PathVariable Long id) {
        cursoService.removerCurso(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Curso> toggleAtivo(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.toggleAtivo(id));
    }
}
