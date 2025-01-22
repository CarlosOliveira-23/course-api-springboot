package com.example.cursos.controller;

import com.example.cursos.dto.CursoDTO;
import com.example.cursos.model.Curso;
import com.example.cursos.service.CursoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoService.listarCursos(null, null));
        return "list";
    }

    @GetMapping("/{id}")
    public String visualizarCurso(@PathVariable Long id, Model model) {
        Curso curso = cursoService.buscarPorId(id);
        model.addAttribute("curso", curso);
        return "view";
    }

    @GetMapping("/novo")
    public String novoCurso(Model model) {
        model.addAttribute("curso", new Curso());
        return "form";
    }

    @PostMapping
    public String salvarCurso(@ModelAttribute Curso curso) {
        cursoService.criarCurso(new CursoDTO(curso.getName(), curso.getCategory(), curso.getProfessor()));
        return "redirect:/cursos";
    }

    @GetMapping("/{id}/edit")
    public String editarCurso(@PathVariable Long id, Model model) {
        Curso curso = cursoService.buscarPorId(id);
        model.addAttribute("curso", curso);
        return "form";
    }

    @PostMapping("/{id}/delete")
    public String excluirCurso(@PathVariable Long id) {
        cursoService.removerCurso(id);
        return "redirect:/cursos";
    }
}
