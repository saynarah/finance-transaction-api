package com.saynab.transacao_api.business.services;

import com.saynab.transacao_api.controller.dtos.EstatisticasResponseDTO;
import com.saynab.transacao_api.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {

    public final TransacaoService transacaoService;


    public EstatisticasResponseDTO calcularEstatisticasTransacoes(Integer intervaloBusca){
        log.info("Iniciada a busca de estatísticas de transações pelo período de tempo " + intervaloBusca);

        long start  = System.currentTimeMillis();
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        if (transacoes.isEmpty()) {
            return new EstatisticasResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }


        DoubleSummaryStatistics estatisticasTransacoes =  transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor)
                .summaryStatistics();

        long finish  = System.currentTimeMillis();
        long tempoRequisicao = finish - start;

        log.info("Estatísticas retornadas com sucesso");
        log.info("Tempo de requisição: " + tempoRequisicao + "ms");

        return new EstatisticasResponseDTO(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }


}
