package com.gestao.gestaoalunos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestao.gestaoalunos.model.Mensalidade;

@Repository
public interface MensalidadeRepository extends JpaRepository<Mensalidade, Long> {
	List<Mensalidade> findByAlunoId(Long alunoId);
}