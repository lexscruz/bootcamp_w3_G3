package com.bootcamp_w3_g3.service;

import com.bootcamp_w3_g3.model.dtos.response.requisito6.AtividadeRepresentanteDTO;
import com.bootcamp_w3_g3.model.entity.*;
import com.bootcamp_w3_g3.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hugo damm
 * @author matheus willock
 */
public class OrdemDeEntradaUnitTest {

    ProdutoService produtoService = Mockito.mock(ProdutoService.class);
    VendedorRepository vendedorRepository = Mockito.mock(VendedorRepository.class);
    VendedorService vendedorService = new VendedorService(vendedorRepository);


    LoteRepository loteRepository = Mockito.mock(LoteRepository.class);
    LoteService loteService = new LoteService(loteRepository, produtoService);


    RepresentanteRepository representanteRepository = Mockito.mock(RepresentanteRepository.class);
    RepresentanteService representanteServiceMock = Mockito.mock(RepresentanteService.class);
    RepresentanteService representanteService = new RepresentanteService(representanteRepository);

    ArmazemRepository armazemRepository = Mockito.mock(ArmazemRepository.class);
    ArmazemService armazemService = new ArmazemService(armazemRepository);

    SetorRepository setorRepository = Mockito.mock(SetorRepository.class);
    SetorService setorService = new SetorService(setorRepository);

    OrdemDeEntradaRepository ordemDeEntradaRepository = Mockito.mock(OrdemDeEntradaRepository.class);

    OrdemDeEntradaService ordemDeEntradaService = new OrdemDeEntradaService(ordemDeEntradaRepository, representanteService, armazemService, setorService );

    private static final Long SETOR_ID = Long.valueOf(1);

    List<Setor> setorList = new ArrayList<>();

    Representante representante1 = Representante.builder()
            .nome("Ernani")
            .sobrenome("Santos")
            .cpf("123.345.678-92")
            .telefone("11 9 7867-3456")
            .endereco("Rua B")
            .build();

    Representante representante2 = Representante.builder()
            .nome("João")
            .sobrenome("Paulo")
            .cpf("678.123.645-92")
            .telefone("19 9 7867-3456")
            .endereco("Rua ABCDEF")
            .build();

    Armazem armazem1 = Armazem.builder()
            .codArmazem("Ar-123")
            .representante(representante1)
            .nome("AR1")
            .endereco("rua 10")
            .uf("SP").build();

    Armazem armazem2 = Armazem.builder()
            .codArmazem("Ar-231231")
            .representante(representante2)
            .nome("AR2")
            .endereco("rua 10")
            .uf("SP").build();

    Setor setor1 = Setor.builder()
            .id(SETOR_ID)
            .codigo("123")
            .nome("Setor123")
            .tipoProduto(TipoProduto.CONGELADOS)
            .espacoDisponivel(100)
            .armazem(armazem1).build();

    Setor setor2 = Setor.builder()
            .id(SETOR_ID)
            .codigo("2")
            .nome("Setor2")
            .tipoProduto(TipoProduto.FRESCOS)
            .armazem(armazem2).build();

    Produto produto = Produto.builder()
            .codigoDoProduto(123)
            .nome("carne")
            .preco(60.0)
            .temperaturaIndicada(8.0)
            .tipoProduto(TipoProduto.FRESCOS)
            .build();

    Produto produto2 = Produto.builder()
            .codigoDoProduto(231231)
            .nome("carne2")
            .preco(60.0)
            .temperaturaIndicada(8.0)
            .tipoProduto(TipoProduto.FRESCOS)
            .build();

    Lote lote = Lote.builder()
            .numero(10)
            .quantidadeAtual(10)
            .quantidadeMinina(5)
            .temperaturaAtual(10.0)
            .temperaturaMinima(5.0)
            .horaFabricacao(LocalTime.now())
            .dataDeFabricacao(LocalDate.now())
            .dataDeValidade(LocalDate.now())
            .dataDeValidade(LocalDate.now())
            .produto(produto)
            .build();

    Lote lote2 = Lote.builder()
            .numero(20)
            .quantidadeAtual(10)
            .quantidadeMinina(5)
            .temperaturaAtual(10.0)
            .temperaturaMinima(5.0)
            .horaFabricacao(LocalTime.now())
            .dataDeFabricacao(LocalDate.now())
            .dataDeValidade(LocalDate.now())
            .dataDeValidade(LocalDate.now())
            .produto(produto2)
            .build();

    Vendedor vendedor = Vendedor.builder()
            .nome("Alex")
            .sobrenome("Cruz")
            .cpf("2345678910")
            .telefone("5555555")
            .endereco("Rua Joao neves 18")
            .build();

    Vendedor vendedor1 = Vendedor.builder()
            .nome("Mestre")
            .sobrenome("Dos Mestres")
            .cpf("2345678910")
            .telefone("21 9 9934-3454")
            .endereco("Av. Copacabana")
            .build();

    Vendedor vendedor2 = Vendedor.builder()
            .nome("Alex")
            .sobrenome("Dos Mestres")
            .cpf("2345678945")
            .telefone("21 9 9934-3454")
            .endereco("Av. Copacabana")
            .build();

    OrdemDeEntrada ordemDeEntrada = OrdemDeEntrada.builder()
            .numeroDaOrdem(100)
            .dataDaOrdem(LocalDate.now())
            .setor(setor1)
            .representante(representante1)
            .quantidade(3)
            .lote(lote)
            .vendedor(vendedor)
            .build();

    OrdemDeEntrada ordemDeEntrada2 = OrdemDeEntrada.builder()
            .numeroDaOrdem(200)
            .dataDaOrdem(LocalDate.now())
            .setor(setor2)
            .representante(representante2)
            .quantidade(3)
            .lote(lote2)
            .vendedor(vendedor2)
            .build();

    OrdemDeEntrada ordemDeEntrada3 = OrdemDeEntrada.builder()
            .numeroDaOrdem(300)
            .dataDaOrdem(LocalDate.now())
            .setor(setor2)
            .representante(representante2)
            .quantidade(3)
            .lote(lote2)
            .vendedor(vendedor2)
            .build();

    List<OrdemDeEntrada> ordemDeEntradaList = new ArrayList<>();

    @Test
    void registrarOrdemDeEntradaTest()
    {
        ordemDeEntrada.setSetor(setor1);

        Mockito.when(setorRepository.save(Mockito.any(Setor.class))).thenReturn(setor1);
        setorService.salvarSetor(setor1);
        Mockito.when(ordemDeEntradaRepository.save(Mockito.any(OrdemDeEntrada.class))).thenReturn(ordemDeEntrada);

        ordemDeEntradaService.registra(ordemDeEntrada);
        assertNotNull(ordemDeEntrada);
    }

    @Test
    void alterarOrdemDeEntradaTest() {


        ordemDeEntrada.setLote(lote);
        ordemDeEntrada.setRepresentante(representante1);
        ordemDeEntrada.setSetor(setor1);
        ordemDeEntrada.setVendedor(vendedor1);

        ordemDeEntradaService = Mockito.mock(OrdemDeEntradaService.class);

        Mockito.when(loteRepository.save(Mockito.any(Lote.class))).thenReturn(lote);
        Mockito.when(representanteRepository.save(Mockito.any(Representante.class))).thenReturn(representante1);
        Mockito.when(setorRepository.save(Mockito.any(Setor.class))).thenReturn(setor1);
        Mockito.when(vendedorRepository.save(Mockito.any(Vendedor.class))).thenReturn(vendedor);
        Mockito.when(ordemDeEntradaRepository.findByNumeroDaOrdem(Mockito.any(Integer.class))).thenReturn(ordemDeEntrada);
        Mockito.when(ordemDeEntradaRepository.save(Mockito.any(OrdemDeEntrada.class))).thenReturn(ordemDeEntrada);
        Mockito.when(ordemDeEntradaService.atualizaOrdem(ordemDeEntrada)).thenReturn(ordemDeEntrada);

        ordemDeEntradaService.atualizaOrdem(ordemDeEntrada);
        OrdemDeEntrada atualizado = ordemDeEntradaService.obter(ordemDeEntrada.getNumeroDaOrdem());
        if (atualizado != null) {
            Mockito.verify(ordemDeEntradaService, Mockito.times(1)).atualizaOrdem(ordemDeEntrada);
            assertNotNull(atualizado);
            assertNotEquals(atualizado.getVendedor(), ordemDeEntrada.getVendedor());

        }

    }

    @Test
    void retornaAtividadeRepresentanteTest(){
        representante1.setCodigo("R-101010");
        representante2.setCodigo("R-202020");
        ordemDeEntradaList.add(ordemDeEntrada);
        ordemDeEntradaList.add(ordemDeEntrada2);
        ordemDeEntradaList.add(ordemDeEntrada3);

        Mockito.when(representanteRepository.findByCodigo(Mockito.any(String.class))).thenReturn(representante2);

        AtividadeRepresentanteDTO atividadeRepresentanteDTO = ordemDeEntradaService.retornaAtivdadeRepresentante(representante2.getCodigo(), ordemDeEntradaList);

        ordemDeEntradaList.remove(0);

        AtividadeRepresentanteDTO atividadeRepresentanteDTOEsperada = AtividadeRepresentanteDTO.builder()
                .codigo(representante2.getCodigo())
                .nome(representante2.getNome())
                .sobreNome(representante2.getSobrenome())
                .ordemDeEntradaList(ordemDeEntradaList)
                .build();

        assertNotNull(atividadeRepresentanteDTO);
        assertEquals(atividadeRepresentanteDTOEsperada.getOrdemDeEntradaList().size(), atividadeRepresentanteDTO.getOrdemDeEntradaList().size());
    }



}