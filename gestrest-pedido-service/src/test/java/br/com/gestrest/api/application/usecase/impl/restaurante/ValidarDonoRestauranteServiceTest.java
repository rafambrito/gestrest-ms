package br.com.gestrest.api.application.usecase.impl.restaurante;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gestrest.pedido.service.application.usecase.impl.restaurante.ValidarDonoRestauranteService;
import br.com.gestrest.pedido.service.domain.exception.BusinessException;
import br.com.gestrest.pedido.service.domain.model.ports.out.DonoRestauranteValidatorPort;

@ExtendWith(MockitoExtension.class)
@DisplayName("Validação de dono de restaurante")
class ValidarDonoRestauranteServiceTest {

    @Mock
    private DonoRestauranteValidatorPort donoRestauranteValidatorPort;

    @InjectMocks
    private ValidarDonoRestauranteService service;

    @Test
    @DisplayName("Deve validar com sucesso quando o validador externo aceitar o dono")
    void deveValidarComSucesso() {
        assertDoesNotThrow(() -> service.validar(1L));
    }

    @Test
    @DisplayName("Deve falhar quando o validador externo rejeitar o dono")
    void deveFalharQuandoValidadorExternoRejeitar() {
        doThrow(new BusinessException("Dono inválido"))
                .when(donoRestauranteValidatorPort)
                .validar(99L);

        assertThrows(BusinessException.class, () -> service.validar(99L));
    }
}