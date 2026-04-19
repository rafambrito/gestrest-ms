package br.com.gestrest.api.adapters.in.web.exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.gestrest.pedido.service.infrastructure.GestRestApiApplication;

@SpringBootTest(classes = GestRestApiApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GlobalExceptionIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
        @DisplayName("Validação DTO retorna ProblemDetail 400 com lista de erros")
    void validationErrorReturnsErrorResponse() throws Exception {
        String body = """
                {
                  \"nome\": \"\",
                  \"endereco\": \"Rua A\",
                  \"tipoCozinha\": \"Italiana\",
                  \"horarioFuncionamento\": \"11:00-22:00\",
                  \"donoId\": 1
                }
                """;

        mockMvc.perform(post("/api/v1/restaurantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.title").value("Bad Request"))
                .andExpect(jsonPath("$.errors").isArray());
    }

    @Test
        @DisplayName("Criar item para restaurante inexistente retorna 404 com ProblemDetail")
    void createItemForNonexistentRestaurantReturns404() throws Exception {
        String body = """
                {
                  \"nome\": \"Fantasma\",
                  \"descricao\": \"Nao existe\",
                  \"preco\": 10.00,
                  \"disponivelSomenteNoLocal\": true,
                  \"fotoPath\": \"/itens/fantasma.jpg\",
                  \"restauranteId\": 99999
                }
                """;

        mockMvc.perform(post("/api/v1/itens-cardapio")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.title").value("Not Found"));
    }
}