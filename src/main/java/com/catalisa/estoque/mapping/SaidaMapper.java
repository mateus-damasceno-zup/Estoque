package com.catalisa.estoque.mapping;

import com.catalisa.estoque.dto.EntradaDTO;
import com.catalisa.estoque.dto.SaidaDTO;
import com.catalisa.estoque.model.Entrada;
import com.catalisa.estoque.model.Saida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface SaidaMapper {

    SaidaMapper INSTANCE = Mappers.getMapper(SaidaMapper.class);

    @Mapping(target = "saida.id", ignore = true)
    @Mapping(target = "produtoId", source = "produtos.id")
    SaidaDTO saidaParaSaidaDTO(Saida saida);

    Saida dTOParaSaida(SaidaDTO saidaDTO);

}

