package com.saynab.transacao_api.business.service;


import com.saynab.transacao_api.business.services.EstatisticasService;
import com.saynab.transacao_api.business.services.TransacaoService;
import com.saynab.transacao_api.controller.dtos.EstatisticasResponseDTO;
import com.saynab.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.saynab.transacao_api.infrastructure.exceptions.UnprocessableEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    TransacaoService transacaoService;

    TransacaoRequestDTO transacao;

    EstatisticasResponseDTO estatisticas;

    @BeforeEach
    void setUp(){
        transacao = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
        estatisticas = new EstatisticasResponseDTO(1L,20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void deveAdicionarTransacaoComSucesso(){

        transacaoService.adicionarTransacoes(transacao);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(5000);

        assertTrue(transacoes.contains(transacao));


    }

    @Test
    void deveLançarExcecaoCasoValorSejaNegativo(){

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                ()-> transacaoService.adicionarTransacoes(new TransacaoRequestDTO(-10.0,OffsetDateTime.now())));

        assertEquals(exception.getMessage(), "Valor não pode ser menor que zero.");
    }

    @Test
    void deveLançarExcecaoCasoDataOuHoraMaiorQueAtual(){

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                ()-> transacaoService.adicionarTransacoes(new TransacaoRequestDTO(-10.0,OffsetDateTime.now().plusDays(1))));

        assertEquals(exception.getMessage(), "Data e hora maiores que a data e hora atuais.");
    }

    @Test
    void deveLimparTransacaoComSucesso(){

        transacaoService.limparTransacoes();

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(5000);

        assertTrue(transacoes.isEmpty());


    }

    @Test
    void deveBuscarTransacoesDentroDoIntervalo(){

        TransacaoRequestDTO dto = new TransacaoRequestDTO(20.0, OffsetDateTime.now().minusHours(1));

        transacaoService.adicionarTransacoes(transacao);
        transacaoService.adicionarTransacoes(dto);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(60);

        assertTrue(transacoes.contains(transacao));
        assertFalse(transacoes.contains(dto));

    }

}
