package com.gestao.gestaoalunos.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.gestao.gestaoalunos.model.Anamnese;
import com.gestao.gestaoalunos.repository.AnamneseRepository;

@Service
public class AnamneseService {

    private final AnamneseRepository anamneseRepository;

    public AnamneseService(AnamneseRepository anamneseRepository) {
        this.anamneseRepository = anamneseRepository;
    }

    public Anamnese salvar(Anamnese anamnese) {
        return anamneseRepository.save(anamnese);
    }

    public List<Anamnese> listarTodas() {
        return anamneseRepository.findAll();
    }

    public Anamnese buscarPorId(Long id) {
        return anamneseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anamnese não encontrada com o ID: " + id));
    }

    public void deletar(Long id) {
        Anamnese anamnese = buscarPorId(id);
        anamneseRepository.delete(anamnese);
    }
}
