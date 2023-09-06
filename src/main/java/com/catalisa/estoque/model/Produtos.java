package com.catalisa.estoque.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private Double preco;
    @Column(nullable = false)
    private int quantidade;
    @OneToMany(mappedBy = "produtos", cascade = CascadeType.ALL)
    private List<Saida> saidas= new ArrayList<>();

    @OneToMany(mappedBy = "produtos", cascade = CascadeType.ALL)
    private List<Entrada> entradas= new ArrayList<>();

}
