package com.catalisa.estoque.repository;

import com.catalisa.estoque.model.Produtos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EstoqueRepository  extends CrudRepository<Produtos,Long>{

}
