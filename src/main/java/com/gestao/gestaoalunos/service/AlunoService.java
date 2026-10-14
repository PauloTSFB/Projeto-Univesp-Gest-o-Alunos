package com.gestao.gestaoalunos.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestao.gestaoalunos.model.Aluno;
import com.gestao.gestaoalunos.model.Mensalidade;
import com.gestao.gestaoalunos.repository.AlunoRepository;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final MensalidadeService mensalidadeService;

    public AlunoService(AlunoRepository alunoRepository, MensalidadeService mensalidadeService) {
        this.alunoRepository = alunoRepository;
        this.mensalidadeService = mensalidadeService;
    }

    @Transactional
    public Aluno salvar(Aluno aluno) {
        
        if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do aluno é obrigatório.");
        }
        
        if (aluno.getAnamnese() != null) {
            aluno.getAnamnese().setAluno(aluno);
        }
        
        // 1. Capturamos os dados da primeira mensalidade enviada pelo front-end
        BigDecimal valorInicial = new BigDecimal("100.00");
        LocalDate dataInicial = LocalDate.now().plusMonths(1);

        if (aluno.getMensalidades() != null && !aluno.getMensalidades().isEmpty()) {
            Mensalidade primeira = aluno.getMensalidades().get(0);
            if (primeira.getValor() != null) {
                valorInicial = primeira.getValor();
            }
            if (primeira.getDataVencimento() != null) {
                dataInicial = primeira.getDataVencimento();
            }
        }

        // 2. Anulamos completamente a lista no objeto para o Hibernate ignorar qualquer parcela avulsa
        aluno.setMensalidades(new ArrayList<>());
        
        // 3. Salva o aluno (e a anamnese em cascata) gerando o ID
        Aluno alunoSalvo = alunoRepository.save(aluno);

        // 4. Chama o método que gera e salva as 12 mensalidades corretamente vinculadas
        mensalidadeService.gerarPlanoAnual(alunoSalvo.getId(), valorInicial, dataInicial);

        return alunoSalvo;
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com o ID: " + id));
    }

    public void deletar(Long id) {
        Aluno aluno = buscarPorId(id);
        alunoRepository.delete(aluno);
    }
}