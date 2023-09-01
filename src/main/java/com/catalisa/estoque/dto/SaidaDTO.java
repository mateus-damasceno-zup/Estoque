package com.catalisa.estoque.dto;

import com.catalisa.estoque.model.Produtos;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntradaDTO {

    private static final long serialVersionUID = 1L;
    private Produtos produtos;
    private Integer quantidade;
    private LocalDateTime data;


}
