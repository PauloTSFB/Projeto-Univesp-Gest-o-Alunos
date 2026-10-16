package com.gestao.gestaoalunos.controller;

import java.math.BigDecimal;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gestao.gestaoalunos.model.Mensalidade;
import com.gestao.gestaoalunos.service.MensalidadeService;

@RestController
@RequestMapping("/mensalidades")
@CrossOrigin(origins = "*") // Para permitir requisições do front-end
public class MensalidadeController {

    private final MensalidadeService mensalidadeService;

    public MensalidadeController(MensalidadeService mensalidadeService) {
        this.mensalidadeService = mensalidadeService;
    }

    @GetMapping
    public ResponseEntity<List<Mensalidade>> listarTodas() {
        return ResponseEntity.ok(mensalidadeService.listarTodas());
    }

    // Endpoint para buscar por aluno: GET /mensalidades/aluno/1
    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<Mensalidade>> listarPorAluno(@PathVariable Long alunoId) {
        return ResponseEntity.ok(mensalidadeService.listarPorAluno(alunoId));
    }

    // --- ENDPOINT PARA O DASHBOARD ---
    // GET /mensalidades/vencendo-semana
    @GetMapping("/vencendo-semana")
    public ResponseEntity<List<Mensalidade>> buscarVencendosNaSemana() {
        return ResponseEntity.ok(mensalidadeService.buscarVencendosNaSemana());
    }

    // Registrar pagamento (dar baixa)
    @PutMapping("/{id}/pagar")
    public ResponseEntity<Mensalidade> registrarPagamento(@PathVariable Long id) {
        return ResponseEntity.ok(mensalidadeService.registrarPagamento(id));
    }
    
    @PostMapping("/gerar-plano")
    public ResponseEntity<List<Mensalidade>> gerarPlanoAnual(
            @RequestParam Long alunoId,
            @RequestParam BigDecimal valorMensal,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate primeiroVencimento) {

        List<Mensalidade> plano = mensalidadeService.gerarPlanoAnual(
            alunoId, 
            valorMensal, 
            primeiroVencimento
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(plano);
    }
    
}