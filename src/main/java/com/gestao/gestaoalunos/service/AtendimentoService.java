package com.gestao.gestaoalunos.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.gestao.gestaoalunos.model.Atendimento;
import com.gestao.gestaoalunos.repository.AtendimentoRepository;

@Service
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;

    public AtendimentoService(AtendimentoRepository atendimentoRepository) {
        this.atendimentoRepository = atendimentoRepository;
    }

    public Atendimento salvar(Atendimento atendimento) {
        return atendimentoRepository.save(atendimento);
    }

    public List<Atendimento> listarTodos() {
        return atendimentoRepository.findAll();
    }

    public Atendimento buscarPorId(Long id) {
        return atendimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atendimento não encontrado com o ID: " + id));
    }

    public void deletar(Long id) {
        Atendimento atendimento = buscarPorId(id);
        atendimentoRepository.delete(atendimento);
    }
}
