package com.catalisa.estoque.repository;

import com.catalisa.estoque.model.Saida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaidaRepository extends JpaRepository<Saida,Long> {
}
