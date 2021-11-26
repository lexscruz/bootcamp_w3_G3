package com.bootcamp_w3_g3.controller;

import com.bootcamp_w3_g3.model.dtos.request.CompradorForm;
import com.bootcamp_w3_g3.model.dtos.response.CompradorDTO;
import com.bootcamp_w3_g3.model.dtos.response.requisito6.AtividadeRepresentanteDTO;
import com.bootcamp_w3_g3.model.dtos.response.requisito6.CompradorMediaGastosPorCompraDTO;
import com.bootcamp_w3_g3.model.entity.Carrinho;
import com.bootcamp_w3_g3.model.entity.Comprador;
import com.bootcamp_w3_g3.model.entity.OrdemDeEntrada;
import com.bootcamp_w3_g3.repository.CarrinhoRepository;
import com.bootcamp_w3_g3.service.CarrinhoService;
import com.bootcamp_w3_g3.service.CompradorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Alex Ccruz
 */

@RestController
@RequestMapping("comprador/")
@Api(description = "Conjunto de endpoints para criação, recuperação, atualização e exclusão de Compradores.")
public class CompradorController {

    @Autowired
    private CompradorService compradorService;
    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/salvar")
    @ApiOperation("Criar um novo comprador.")
    public ResponseEntity<CompradorDTO> cadastrar(
            @ApiParam("Informações do comprador para um novo comprador a ser criado.")
            @RequestBody CompradorForm compradorForm) {
        Comprador comprador = compradorService.salvar(compradorForm.converte());
        return new ResponseEntity<>(CompradorDTO.converter(comprador), HttpStatus.CREATED);
    }

    @PutMapping("/alterar")
    @ApiOperation("Atualiza as informações do comprador cadastrado.")
    public ResponseEntity<CompradorDTO> alterar(@RequestBody CompradorForm compradorForm) {
        Comprador comprador = compradorService.atualizar(compradorForm.converte());
        return new ResponseEntity<>(CompradorDTO.converter(comprador), HttpStatus.OK);
    }

    /**
     * Retorno de um valor médio que o comprador gasta por compra.
     * @autor alex cruz
     */
    @GetMapping("/mediaCompras/{codigo}")
    public ResponseEntity<CompradorMediaGastosPorCompraDTO> mediaCompras(@PathVariable String codigo) {
        List<Carrinho> listaDeCarrinhos = carrinhoRepository.findAll();
        CompradorMediaGastosPorCompraDTO compradorMediaGastosPorCompraDTO = carrinhoService.mediaDeGastoDasCompras(codigo, listaDeCarrinhos);
        return new ResponseEntity<>(compradorMediaGastosPorCompraDTO,HttpStatus.OK);
    }
}



