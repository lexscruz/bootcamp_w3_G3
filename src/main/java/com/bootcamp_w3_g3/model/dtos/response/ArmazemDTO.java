package com.bootcamp_w3_g3.model.dtos.response;

import com.bootcamp_w3_g3.model.entity.Armazem;
import com.bootcamp_w3_g3.model.entity.Representante;
import com.bootcamp_w3_g3.model.entity.Setor;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;


import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@AllArgsConstructor

@NoArgsConstructor
public class ArmazemDTO {

    private long codArmazem;
    private String nome;
    private String endereco;
    private String uf;

    private Representante representante;
    private List<Setor> SetoresDoArmazem;


    public static ArmazemDTO converter(Armazem armazem) {

        return new ArmazemDTO(
                armazem.getCodArmazem(),
                armazem.getNome(),
                armazem.getEndereco(),
                armazem.getUf(),
                armazem.getRepresentante(),
                armazem.getSetoresDoArmazem()
        );


    }



}
