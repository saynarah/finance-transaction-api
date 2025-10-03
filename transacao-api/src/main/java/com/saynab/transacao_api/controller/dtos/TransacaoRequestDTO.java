package com.saynab.transacao_api.controller.dtos;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public record TransacaoRequestDTO(
        Double valor,
        OffsetDateTime dataHora
) {

    public TransacaoRequestDTO(Double valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora
                .withOffsetSameInstant(ZoneOffset.of("-03:00"));

    }

}
