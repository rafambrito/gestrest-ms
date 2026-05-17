package br.com.gestrest.api.adapters.in.web.exception;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.gestrest.pedido.service.infrastructure.GestRestApiApplication;

@SpringBootTest(classes = GestRestApiApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser(roles = "CLIENTE")
class GlobalExceptionIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
        @DisplayName("Validação DTO retorna ProblemDetail 400 com lista de erros")
    void validationErrorReturnsErrorResponse() throws Exception {
        String body = """
                {
                  "restauranteId": null,
                  "itens": []
                }
                """;

        mockMvc.perform(post("/api/v1/pedidos")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.title").value("Bad Request"))
                .andExpect(jsonPath("$.errors").isArray());
    }

    @Test
        @DisplayName("Pedido inexistente retorna 404 com ProblemDetail")
    void nonexistentPedidoReturns404() throws Exception {
        mockMvc.perform(get("/api/v1/pedidos/99999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.title").value("Not Found"));
    }
}