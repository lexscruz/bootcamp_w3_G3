package com.bootcamp_w3_g3.model.dtos.response;

import com.bootcamp_w3_g3.model.entity.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hugo damm
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SetorDTO {

    private String codigo;
    private String nome;
    private String tipoProduto;
    private Armazem armazem;
    private List<Lote> lote;

    public static SetorDTO converter(Setor setor){
        return SetorDTO.builder()
                .codigo(setor.getCodigo())
                .nome(setor.getNome())
                .tipoProduto(setor.getTipoProduto())
                .armazem(setor.getArmazem()).build();
    }

    public static List<SetorDTO> converterLista(List<Setor> setorList){
        List<SetorDTO> setorDTOList = new ArrayList<>();
        for (Setor setor : setorList){
            setorDTOList.add(SetorDTO.builder()
                    .codigo(setor.getCodigo())
                    .nome(setor.getNome())
                    .tipoProduto(setor.getTipoProduto())
                    .armazem(setor.getArmazem())
                    .build());
        }
        return setorDTOList;
    }

}
