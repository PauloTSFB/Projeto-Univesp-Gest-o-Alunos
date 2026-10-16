package com.gestao.gestaoalunos.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Mensalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;
    private LocalDate dataVencimento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    private LocalDate dataPagamento;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    @JsonIgnoreProperties("mensalidades") // Evita recursão infinita no JSON
    private Aluno aluno;

    // Construtor Padrão (Obrigatório para o JPA)
    public Mensalidade() {}

    // Construtor Completo
    public Mensalidade(BigDecimal valor, LocalDate dataVencimento, StatusPagamento status, LocalDate dataPagamento, Aluno aluno) {
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.status = status;
        this.dataPagamento = dataPagamento;
        this.aluno = aluno;
    }

    // --- GETTERS E SETTERS ---

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public BigDecimal getValor() { 
        return valor; 
    }

    public void setValor(BigDecimal valor) { 
        this.valor = valor; 
    }

    public LocalDate getDataVencimento() { 
        return dataVencimento; 
    }

    public void setDataVencimento(LocalDate dataVencimento) { 
        this.dataVencimento = dataVencimento; 
    }

    public StatusPagamento getStatus() { 
        return status; 
    }

    public void setStatus(StatusPagamento status) { 
        this.status = status; 
    }

    public LocalDate getDataPagamento() { 
        return dataPagamento; 
    }

    public void setDataPagamento(LocalDate dataPagamento) { 
        this.dataPagamento = dataPagamento; 
    }

    public Aluno getAluno() { 
        return aluno; 
    }

    public void setAluno(Aluno aluno) { 
        this.aluno = aluno; 
    }
}