package com.bootcamp_w3_g3.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Joaquim Borges
 * @autor Alex Cruz
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;
    private Integer quantidadeAtual;
    private  Integer quantidadeMinina;

    private Double temperaturaAtual;
    private Double temperaturaMinima;

    private LocalTime horaFabricacao;
    private LocalDate dataDeFabricacao;
    private LocalDate dataDeValidade;

    @ManyToOne
    private Produto produto;

    @JsonBackReference
    @ManyToOne
    private Setor setor;
}
