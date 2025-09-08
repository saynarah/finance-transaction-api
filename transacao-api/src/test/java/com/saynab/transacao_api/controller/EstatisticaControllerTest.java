package com.saynab.transacao_api.controller;


import com.saynab.transacao_api.business.services.EstatisticasService;
import com.saynab.transacao_api.controller.dtos.EstatisticasResponseDTO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class EstatisticaControllerTest {

    @InjectMocks
    private EstatisticasController estatisticaController;

    @Mock
    EstatisticasService estatisticaService;

    EstatisticasResponseDTO estatisticas;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(estatisticaController).build();
        estatisticas = new EstatisticasResponseDTO(1L,20.0, 20.0, 20.0, 20.0);

    }

    @Test
    void deveBuscarEstatisticasComSucesso() throws Exception {

        when(estatisticaService.calcularEstatisticasTransacoes(60)).
                thenReturn(estatisticas);

        mockMvc.perform(get("/estatistica")
                .param("intervaloBusca","60")
                .accept(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(jsonPath("$.count").value(estatisticas.count()));


    }

}
