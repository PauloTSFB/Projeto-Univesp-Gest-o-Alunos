package com.gestao.gestaoalunos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;

    @OneToOne(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("aluno")
    private Anamnese anamnese;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("aluno")
    private List<Mensalidade> mensalidades = new ArrayList<>();

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("aluno")
    private List<Atendimento> atendimentos = new ArrayList<>();

    // Construtor Padrão (Obrigatório para o JPA)
    public Aluno() {}

    // Construtor Completo
    public Aluno( String nome, String telefone, String email, LocalDate dataNascimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    // --- GETTERS E SETTERS ---

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getNome() { 
        return nome; 
    }

    public void setNome(String nome) { 
        this.nome = nome; 
    }

    public String getTelefone() { 
        return telefone; 
    }

    public void setTelefone(String telefone) { 
        this.telefone = telefone; 
    }

    public String getEmail() { 
        return email; 
    }

    public void setEmail(String email) { 
        this.email = email; 
    }

    public LocalDate getDataNascimento() { 
        return dataNascimento; 
    }

    public void setDataNascimento(LocalDate dataNascimento) { 
        this.dataNascimento = dataNascimento; 
    }

    public Anamnese getAnamnese() { 
        return anamnese; 
    }

    public void setAnamnese(Anamnese anamnese) { 
        this.anamnese = anamnese; 
    }

    public List<Mensalidade> getMensalidades() { 
        return mensalidades; 
    }

    public void setMensalidades(List<Mensalidade> mensalidades) { 
        this.mensalidades = mensalidades; 
    }

    public List<Atendimento> getAtendimentos() { 
        return atendimentos; 
    }

    public void setAtendimentos(List<Atendimento> atendimentos) { 
        this.atendimentos = atendimentos; 
    }
}