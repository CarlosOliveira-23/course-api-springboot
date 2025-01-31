package com.example.cursos.service;

import com.example.cursos.dto.CursoDTO;
import com.example.cursos.model.Curso;
import com.example.cursos.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso criarCurso(CursoDTO dto) {
        Curso curso = new Curso();
        curso.setName(dto.name());
        curso.setCategory(dto.category());
        curso.setProfessor(dto.professor());
        return cursoRepository.save(curso);
    }

    public List<Curso> listarCursos(String name, String category) {
        if (name != null) {
            return cursoRepository.findByNameContainingIgnoreCase(name);
        } else if (category != null) {
            return cursoRepository.findByCategoryContainingIgnoreCase(category);
        }
        return cursoRepository.findAll();
    }

    public Curso atualizarCurso(Long id, CursoDTO dto) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        if (dto.name() != null) {
            curso.setName(dto.name());
        }
        if (dto.category() != null) {
            curso.setCategory(dto.category());
        }
        if (dto.professor() != null) {
            curso.setProfessor(dto.professor());
        }
        return cursoRepository.save(curso);
    }

    public void removerCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }
}
