package com.gestao.gestaoalunos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestao.gestaoalunos.model.Anamnese;

@Repository
public interface AnamneseRepository extends JpaRepository<Anamnese, Long> {
}