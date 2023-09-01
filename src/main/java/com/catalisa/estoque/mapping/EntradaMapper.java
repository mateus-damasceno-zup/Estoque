package com.catalisa.estoque.mapping;

import com.catalisa.estoque.dto.ProdutosDTO;
import com.catalisa.estoque.model.Produtos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    @Mapping(target = "produtos.id", ignore = true)
    ProdutosDTO produtoParaprodutoDTO(Produtos produtos);

    Produtos dTOParaProduto(ProdutosDTO produtosDTO);

}

