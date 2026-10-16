package com.gestao.gestaoalunos.controller;

import com.gestao.gestaoalunos.model.Atendimento;
import com.gestao.gestaoalunos.model.Aluno;
import com.gestao.gestaoalunos.service.AtendimentoService;
import com.gestao.gestaoalunos.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atendimentos")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<Atendimento> listarTodos() {
        return atendimentoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Atendimento> criarAtendimento(@RequestBody Atendimento atendimento) {
        if (atendimento.getAluno() != null && atendimento.getAluno().getId() != null) {
            Aluno aluno = alunoRepository.findById(atendimento.getAluno().getId()).orElse(null);
            atendimento.setAluno(aluno);
        }

        Atendimento novoAtendimento = atendimentoService.salvar(atendimento);
        return ResponseEntity.ok(novoAtendimento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtendimento(@PathVariable Long id) {
        atendimentoService.deletar(id);
        return ResponseEntity.ok().build();
    }
}