package com.gestao.gestaoalunos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDateTime;

@Entity
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    // Construtor Padrão (Obrigatório para o JPA)
    public Atendimento() {}

    // Construtor Completo
    public Atendimento(LocalDateTime dataHora, String observacoes, Aluno aluno) {
        this.dataHora = dataHora;
        this.observacoes = observacoes;
        this.aluno = aluno;
    }

    // --- GETTERS E SETTERS ---

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public LocalDateTime getDataHora() { 
        return dataHora; 
    }

    public void setDataHora(LocalDateTime dataHora) { 
        this.dataHora = dataHora; 
    }

    public String getObservacoes() { 
        return observacoes; 
    }

    public void setObservacoes(String observacoes) { 
        this.observacoes = observacoes; 
    }

    public Aluno getAluno() { 
        return aluno; 
    }

    public void setAluno(Aluno aluno) { 
        this.aluno = aluno; 
    }
}