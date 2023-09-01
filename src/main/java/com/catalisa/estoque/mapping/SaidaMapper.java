package com.catalisa.estoque.mapping;

import com.catalisa.estoque.dto.EntradaDTO;
import com.catalisa.estoque.dto.ProdutosDTO;
import com.catalisa.estoque.model.Entrada;
import com.catalisa.estoque.model.Produtos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface EntradaMapper {

    EntradaMapper INSTANCE = Mappers.getMapper(EntradaMapper.class);

    @Mapping(target = "produtos.id", ignore = true)
    EntradaDTO entradaParaentradaDTO(Entrada entrada);

    Entrada dTOParaEntrada(EntradaDTO entradaDTO);

}

