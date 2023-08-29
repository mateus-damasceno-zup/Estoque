package com.catalisa.estoque.mapping;

import com.catalisa.estoque.dto.EstoqueDTO;
import com.catalisa.estoque.model.Estoque;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EstoqueMapper {

    EstoqueMapper INSTANCE = Mappers.getMapper(EstoqueMapper.class);

    EstoqueDTO estoqueTOEstoqueDTO (Estoque estoque);
}
