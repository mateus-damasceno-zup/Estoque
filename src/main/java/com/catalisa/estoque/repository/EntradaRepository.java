package com.catalisa.estoque.repository;

import com.catalisa.estoque.model.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada,Long> {
}
