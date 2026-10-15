package com.gestao.gestaoalunos.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gestao.gestaoalunos.model.Aluno;
import com.gestao.gestaoalunos.model.Mensalidade;
import com.gestao.gestaoalunos.model.StatusPagamento;
import com.gestao.gestaoalunos.repository.AlunoRepository;
import com.gestao.gestaoalunos.repository.MensalidadeRepository;

import jakarta.transaction.Transactional;

@Service
public class MensalidadeService {

    private final MensalidadeRepository mensalidadeRepository;
    private final AlunoRepository alunoRepository;

    public MensalidadeService(MensalidadeRepository mensalidadeRepository, AlunoRepository alunoRepository) {
        this.mensalidadeRepository = mensalidadeRepository;
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public List<Mensalidade> gerarPlanoAnual(Long alunoId, BigDecimal valorMensal, LocalDate primeiroVencimento) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        List<Mensalidade> mensalidades = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            Mensalidade m = new Mensalidade();
            m.setAluno(aluno);
            m.setValor(valorMensal);
            m.setStatus(StatusPagamento.PENDENTE);
            m.setDataVencimento(primeiroVencimento.plusMonths(i));

            mensalidades.add(m);
        }

        return mensalidadeRepository.saveAll(mensalidades);
    }

    // --- REGISTRAR PAGAMENTO (DAR BAIXA) ---
    @Transactional
    public Mensalidade registrarPagamento(Long mensalidadeId) {
        Mensalidade mensalidade = buscarPorId(mensalidadeId);
        mensalidade.setStatus(StatusPagamento.PAGO);
        mensalidade.setDataPagamento(LocalDate.now());

        return mensalidadeRepository.save(mensalidade);
    }

    // --- BUSCAR MENSALIDADES VENCENDO NA SEMANA (PRÓXIMOS 7 DIAS) ---
    public List<Mensalidade> buscarVencendosNaSemana() {
        LocalDate hoje = LocalDate.now();
        LocalDate daquiAProximosSeteDias = hoje.plusDays(7);

        return mensalidadeRepository.findByDataVencimentoBetweenAndStatus(
                hoje,
                daquiAProximosSeteDias,
                StatusPagamento.PENDENTE
        );
    }

    public List<Mensalidade> listarPorAluno(Long alunoId) {
        return mensalidadeRepository.findByAlunoId(alunoId);
    }

    public Mensalidade salvar(Mensalidade mensalidade) {
        return mensalidadeRepository.save(mensalidade);
    }

    public List<Mensalidade> listarTodas() {
        return mensalidadeRepository.findAll();
    }

    public Mensalidade buscarPorId(Long id) {
        return mensalidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensalidade não encontrada com o ID: " + id));
    }

    public void deletar(Long id) {
        Mensalidade mensalidade = buscarPorId(id);
        mensalidadeRepository.delete(mensalidade);
    }
}