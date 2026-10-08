package com.gestao.gestaoalunos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Anamnese {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String historicoMedico;
    private String objetivos;
    private String restricoes;

    @OneToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    // Construtor Padrão (Obrigatório para o JPA)
    public Anamnese() {}

    // Construtor Completo
    public Anamnese(String historicoMedico, String objetivos, String restricoes, Aluno aluno) {
        this.historicoMedico = historicoMedico;
        this.objetivos = objetivos;
        this.restricoes = restricoes;
        this.aluno = aluno;
    }

    // --- GETTERS E SETTERS ---

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getHistoricoMedico() { 
        return historicoMedico; 
    }

    public void setHistoricoMedico(String historicoMedico) { 
        this.historicoMedico = historicoMedico; 
    }

    public String getObjetivos() { 
        return objetivos; 
    }

    public void setObjetivos(String objetivos) { 
        this.objetivos = objetivos; 
    }

    public String getRestricoes() { 
        return restricoes; 
    }

    public void setRestricoes(String restricoes) { 
        this.restricoes = restricoes; 
    }

    public Aluno getAluno() { 
        return aluno; 
    }

    public void setAluno(Aluno aluno) { 
        this.aluno = aluno; 
    }
}