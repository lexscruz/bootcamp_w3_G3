package com.bootcamp_w3_g3.model.dtos.response.requisito6;


import com.bootcamp_w3_g3.model.dtos.response.requisito5.DTOLote;
import com.bootcamp_w3_g3.model.entity.Lote;
import com.bootcamp_w3_g3.model.entity.OrdemDeEntrada;
import com.bootcamp_w3_g3.model.entity.Representante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtividadeRepresentanteDTO {

    private String codigo;
    private String nome;
    private String sobreNome;

    private List<OrdemDeEntrada> ordemDeEntradaList;

    public static AtividadeRepresentanteDTO converte(Representante representante, List<OrdemDeEntrada> ordemDeEntradaList) {
        return AtividadeRepresentanteDTO.builder()
                .codigo(representante.getCodigo())
                .nome(representante.getNome())
                .sobreNome(representante.getSobrenome())
                .ordemDeEntradaList(ordemDeEntradaList)
                .build();

    }
}
