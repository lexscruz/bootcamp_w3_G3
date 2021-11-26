package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.model.dtos.response.requisito6.CompradorMediaGastosPorCompraDTO;
import com.bootcamp_w3_g3.model.entity.*;
import com.bootcamp_w3_g3.repository.CarrinhoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.bootcamp_w3_g3.model.entity.StatusCompra.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Criado teste unitário referente a classe carrinhoService.
 * Desenvolvido testes para o CRUD.
 * @author Alex Cruz
 */
public class CarrinhoUnitTest{

    CarrinhoService carrinhoService;
    CarrinhoRepository carrinhoRepository = Mockito.mock(CarrinhoRepository.class);

    LoteService loteServiceMock = Mockito.mock(LoteService.class);

    List<Carrinho> carrinhoList = new ArrayList<>();

    List<Itens> itensList = new ArrayList<>();

    Comprador comprador  = Comprador.builder()
            .codigo("123")
            .nome("Alex")
            .sobrenome("Cruz")
            .cpf("12312345567")
            .telefone("5555555")
            .endereco("Rua Joao neves 18")
            .build();

    Comprador comprador2  = Comprador.builder()
            .codigo("456")
            .nome("Hugo")
            .sobrenome("Damm")
            .cpf("12312345590")
            .telefone("21 3333-1122")
            .endereco("Rua Copacabana")
            .build();

    Produto produto = Produto.builder()
            .codigoDoProduto(25)
            .nome("Batata Doce")
            .preco(10.00)
            .temperaturaIndicada(18.5)
            .tipoProduto(TipoProduto.FRESCOS)
            .build();


    Produto produto2 = Produto.builder()
            .codigoDoProduto(25)
            .nome("Alface")
            .preco(2.0)
            .temperaturaIndicada(18.5)
            .tipoProduto(TipoProduto.FRESCOS)
            .build();

    Lote lote = Lote.builder()
            .numero(10)
            .dataDeFabricacao(LocalDate.parse("2021-10-30", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
            .dataDeValidade(LocalDate.parse("2022-11-15", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
            .produto(produto)
            .quantidadeAtual(5)
            .build();

    Itens item = Itens.builder()
            .produto(produto)
            .quantidade(2)
            .build();

    Itens item2 = Itens.builder()
            .produto(produto2)
            .quantidade(4)
            .build();

    Carrinho carrinho  = Carrinho.builder()
            .codigo("12345")
            .dataDeOrdem(LocalDate.now())
            .statusCompra(StatusCompra.PENDENTE)
            .itensList(null)
            .codigoComprador(comprador.getCodigo())
            .build();

    Carrinho carrinho2  = Carrinho.builder()
            .codigo("23423")
            .dataDeOrdem(LocalDate.now())
            .statusCompra(StatusCompra.CONCLUIDO)
            .itensList(null)
            .codigoComprador(comprador2.getCodigo())
            .build();

    Carrinho carrinho3  = Carrinho.builder()
            .codigo("1113423")
            .dataDeOrdem(LocalDate.now())
            .statusCompra(StatusCompra.CONCLUIDO)
            .itensList(null)
            .codigoComprador(comprador2.getCodigo())
            .build();

    Carrinho carrinho4  = Carrinho.builder()
            .codigo("5553423")
            .dataDeOrdem(LocalDate.now())
            .statusCompra(StatusCompra.CONCLUIDO)
            .itensList(null)
            .codigoComprador(comprador2.getCodigo())
            .build();

    /**
     * Criado teste unitário de método que atende ao requisito 3
     * Teste Para cubrir o estatus PENDENTE do método registrarCarrinho
     * @author Alex Cruz
     */
    @Test
    void registrarPedidoComStatusPendenteTest(){
        produto.setCodLote(lote.getNumero());
        itensList.add(item);
        carrinho.setItensList(itensList);
        carrinho.setStatusCompra(PENDENTE);


        Mockito.when(carrinhoRepository.save(Mockito.any(Carrinho.class))).thenReturn(carrinho);
        Mockito.when(loteServiceMock.obter(Mockito.any(Integer.class))).thenReturn(lote);

        carrinhoService = new CarrinhoService(carrinhoRepository, loteServiceMock);

        BigDecimal retornoDoPrecoDosItens = carrinhoService.retornaPrecoDosItens(carrinho);

        BigDecimal retornoDoRegistrarPedido = carrinhoService.registrarPedido(carrinho);

        assertEquals(retornoDoPrecoDosItens,retornoDoRegistrarPedido);
    }

    /**
     * Teste Para cubrir o estatus CANCELADO do método registrarCarrinho
     * @author Matheus Willock
     */
    @Test
    void registrarPedidoComStatusCanceladoTest(){
        produto.setCodLote(lote.getNumero());
        itensList.add(item);
        carrinho.setItensList(itensList);
        carrinho.setStatusCompra(CANCELADO);

        Mockito.when(carrinhoRepository.save(Mockito.any(Carrinho.class))).thenReturn(carrinho);
        Mockito.when(loteServiceMock.obter(Mockito.any(Integer.class))).thenReturn(lote);

        carrinhoService = new CarrinhoService(carrinhoRepository, loteServiceMock);

        BigDecimal retornoDoPrecoDosItens = carrinhoService.retornaPrecoDosItens(carrinho);

        BigDecimal retornoDoRegistrarPedido = carrinhoService.registrarPedido(carrinho);

        assertNotEquals(retornoDoPrecoDosItens,retornoDoRegistrarPedido);
    }

    /**
     * Teste Para cubrir o estatus CONCLUIDO do método registrarCarrinho
     * @author Matheus Willock
     */
    @Test
    void registrarPedidoComStatusConcluidoTest(){
        produto.setCodLote(lote.getNumero());
        itensList.add(item);
        carrinho.setItensList(itensList);
        carrinho.setStatusCompra(CONCLUIDO);

        Mockito.when(carrinhoRepository.save(Mockito.any(Carrinho.class))).thenReturn(carrinho);
        Mockito.when(loteServiceMock.obter(Mockito.any(Integer.class))).thenReturn(lote);

        carrinhoService = new CarrinhoService(carrinhoRepository, loteServiceMock);

        BigDecimal retornoDoPrecoDosItens = carrinhoService.retornaPrecoDosItens(carrinho);

        BigDecimal retornoDoRegistrarPedido = carrinhoService.registrarPedido(carrinho);

        assertEquals(retornoDoPrecoDosItens,retornoDoRegistrarPedido);
    }

    /**
     * Criado teste unitário de método que atende ao requisito 2
     * @author Alex Cruz
     */
    @Test
    void salvarTest(){
        Mockito.when(carrinhoRepository.save(Mockito.any(Carrinho.class))).thenReturn(carrinho);

        carrinhoService = new CarrinhoService(carrinhoRepository, loteServiceMock);
        Carrinho salvo = carrinhoService.salvar(carrinho);

        Mockito.verify(carrinhoRepository, Mockito.times(1)).save(carrinho);

        assertNotNull(salvo);
    }

    /**
     * Criado teste unitário de método que atende ao requisito 2
     * @author Alex Cruz
     */
    @Test
    void listarTest(){
        carrinhoList.add(carrinho);
        carrinhoList.add(carrinho2);
        Mockito.when(carrinhoRepository.findAll()).thenReturn(carrinhoList);

        carrinhoService = new CarrinhoService(carrinhoRepository, loteServiceMock);
        List<Carrinho> lista = carrinhoService.listar();

        Mockito.verify(carrinhoRepository, Mockito.times(1)).findAll();

        assertEquals(lista.size(),carrinhoList.size());

    }

    /**
     * Criado teste unitário de método que atende ao requisito 2
     * @author Alex Cruz
     */
    @Test
    void atualizarTest(){
        itensList.add(item);
        itensList.add(item2);
        carrinho.setDataDeOrdem(LocalDate.now());
        carrinho.setItensList(itensList);
        carrinho.setStatusCompra(CANCELADO);
        Mockito.when(carrinhoRepository.getByCodigo(Mockito.any(String.class))).thenReturn(carrinho);
        Mockito.when(carrinhoRepository.save(Mockito.any(Carrinho.class))).thenReturn(carrinho);

        carrinhoService = new CarrinhoService(carrinhoRepository, loteServiceMock);
        Carrinho carrinhoAtualizado = carrinhoService.atualizar(carrinho);

        Mockito.verify(carrinhoRepository, Mockito.times(1)).getByCodigo(carrinho.getCodigo());
        Mockito.verify(carrinhoRepository, Mockito.times(1)).save(carrinho);

        assertEquals(carrinhoAtualizado.getStatusCompra(), carrinho.getStatusCompra());

    }

    /**
     * Criado teste unitário de método que atende ao requisito 2
     * @author Alex Cruz
     */
    @Test
    void retornaPrecoDosItensTest(){
        itensList.add(item2);
        carrinho.setItensList(itensList);
        double valorEsperado = 0.00;
        for(Itens item : carrinho.getItensList()){
            valorEsperado += item.getProduto().getPreco() * item.getQuantidade();
        }

        carrinhoService = new CarrinhoService(carrinhoRepository, loteServiceMock);
        BigDecimal valotTotal = carrinhoService.retornaPrecoDosItens(carrinho);

        assertNotNull(valotTotal);
        assertEquals(new BigDecimal(valorEsperado), valotTotal);
    }

    /**
     * Criado teste unitário de método que atende ao requisito 2
     * @author Alex Cruz
     */
    @Test
    void mostrarProdutosDoPedidoTest(){
        List<Produto> produtoDoPedido = new ArrayList<>();
        itensList.add(item);
        itensList.add(item2);
        carrinho.setItensList(itensList);
        carrinho.setId(34234L);
        for (Itens itens : itensList) {
            produtoDoPedido.add(itens.getProduto());
        }

        Mockito.when(carrinhoRepository.getById(Mockito.any(Long.class))).thenReturn(carrinho);

        carrinhoService = new CarrinhoService(carrinhoRepository, loteServiceMock);
        List<Produto> listaDeProdutosRetornada = carrinhoService.mostrarProdutosDoPedido(carrinho.getId());

        Mockito.verify(carrinhoRepository,Mockito.times(1)).getById(carrinho.getId());

        assertNotNull(listaDeProdutosRetornada);
        assertEquals(produtoDoPedido.size(),listaDeProdutosRetornada.size());
    }

    /**
     * Criado teste unitário de método que atende ao requisito 2
     * @author Alex Cruz
     */
    @Test
    void alterarPedidoTest(){
        itensList.add(item);
        itensList.add(item2);
        carrinho.setId(34234L);
        carrinho.setDataDeOrdem(LocalDate.now());
        carrinho.setItensList(itensList);
        carrinho.setStatusCompra(CONCLUIDO);
        Mockito.when(carrinhoRepository.getByCodigo(Mockito.any(String.class))).thenReturn(carrinho);
        Mockito.when(carrinhoRepository.save(Mockito.any(Carrinho.class))).thenReturn(carrinho);

        carrinhoService = new CarrinhoService(carrinhoRepository, loteServiceMock);

        Carrinho carrinhoAlterado = carrinhoService.alterarPedido(carrinho, carrinho.getCodigo());

        Mockito.verify(carrinhoRepository, Mockito.times(1)).save(carrinho);
        Mockito.verify(carrinhoRepository, Mockito.times(1)).getByCodigo(carrinho.getCodigo());

        assertEquals(carrinhoAlterado.getStatusCompra(), carrinho.getStatusCompra());

    }

    /**
     * Testa Retorno de um valor médio que o comprador gasta por compra.
     * @autor alex cruz
     */
    @Test
    void mediaDeGastoDasComprasTest(){
        itensList.add(item);
        itensList.add(item2);

        carrinho.setItensList(itensList);
        carrinho2.setItensList(itensList);
        carrinho3.setItensList(itensList);
        carrinho4.setItensList(itensList);

        carrinhoList.add(carrinho);
        carrinhoList.add(carrinho2);
        carrinhoList.add(carrinho3);
        carrinhoList.add(carrinho4);

        List<Carrinho> carrinhosDoComprador = new ArrayList<>();

        Mockito.when(carrinhoRepository.findAll()).thenReturn(carrinhoList);

        carrinhoService = new CarrinhoService(carrinhoRepository);
        CompradorMediaGastosPorCompraDTO compradorMediaGastosPorCompraDTO = carrinhoService.mediaDeGastoDasCompras("456", carrinhoList);

        Double soma=0.0;
        Double mediaEsperada;
        Integer qtd = 0;
        for (Carrinho c : carrinhoList) {
            if(c.getCodigoComprador().equals(comprador2.getCodigo()) && c.getStatusCompra().equals(StatusCompra.CONCLUIDO)){
                for (int i=0; i < c.getItensList().size(); i++){
                    soma += c.getItensList().get(i).getProduto().getPreco();
                    qtd += c.getItensList().get(i).getQuantidade();
                }
                carrinhosDoComprador.add(c);
            }
        }
        mediaEsperada = soma / qtd;
        CompradorMediaGastosPorCompraDTO compradorMediaGastosPorCompraDTOEsperado = CompradorMediaGastosPorCompraDTO.builder()
                .mediaDeGastoPorCompra(mediaEsperada).build();

        assertNotNull(compradorMediaGastosPorCompraDTO);
        assertEquals(compradorMediaGastosPorCompraDTOEsperado.getMediaDeGastoPorCompra(),compradorMediaGastosPorCompraDTO.getMediaDeGastoPorCompra());
    }
}

