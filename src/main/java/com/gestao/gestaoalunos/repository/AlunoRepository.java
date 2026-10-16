package com.gestao.gestaoalunos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestao.gestaoalunos.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}