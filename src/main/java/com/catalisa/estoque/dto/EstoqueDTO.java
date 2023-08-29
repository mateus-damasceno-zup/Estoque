package com.catalisa.estoque.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class EstoqueDTO {

    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidade;
}
