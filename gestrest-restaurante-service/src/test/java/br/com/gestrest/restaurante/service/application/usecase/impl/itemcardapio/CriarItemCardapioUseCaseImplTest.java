package br.com.gestrest.restaurante.service.application.usecase.impl.itemcardapio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gestrest.restaurante.service.application.usecase.command.itemcardapio.CriarItemCardapioCommand;
import br.com.gestrest.restaurante.service.application.usecase.impl.itemcardapio.CriarItemCardapioUseCaseImpl;
import br.com.gestrest.restaurante.service.domain.exception.RestauranteNaoEncontradoException;
import br.com.gestrest.restaurante.service.domain.model.ItemCardapio;
import br.com.gestrest.restaurante.service.domain.model.Restaurante;
import br.com.gestrest.restaurante.service.domain.model.ports.out.ItemCardapioRepositoryPortWrite;
import br.com.gestrest.restaurante.service.domain.model.ports.out.RestauranteRepositoryPortRead;

@ExtendWith(MockitoExtension.class)
@DisplayName("CriarItemCardapioUseCaseImpl Testes")
class CriarItemCardapioUseCaseImplTest {

    @Mock
    private ItemCardapioRepositoryPortWrite itemCardapioRepositoryWrite;

    @Mock
    private RestauranteRepositoryPortRead restauranteRepositoryRead;

    @InjectMocks
    private CriarItemCardapioUseCaseImpl useCase;

    @Test
    @DisplayName("Deve falhar quando restaurante não existir")
    void deveFalharQuandoRestauranteNaoExistir() {
        var command = new CriarItemCardapioCommand("Lanche Natural", "Pao brioche, queijo prato", new BigDecimal("39.90"), 999L, true, "c:/foto.jpg"  );

        when(restauranteRepositoryRead.buscarPorId(999L)).thenReturn(Optional.empty());

        assertThrows(RestauranteNaoEncontradoException.class, () -> useCase.criar(command));
    }

    @Test
    @DisplayName("Deve criar item com sucesso")
    void deveCriarItemComSucesso() {
        var restaurante = Restaurante.existente(1L, "Rafael Brito", "Rua das Flores, 123", "Brasileira", "10:00 - 22:00", 1L, true);
        var command = new CriarItemCardapioCommand("Lanche Natural", "Pao brioche, queijo prato", new BigDecimal("39.90"), 1L, true, "c:/foto.jpg"  );

        when(restauranteRepositoryRead.buscarPorId(1L)).thenReturn(Optional.of(restaurante));
        when(itemCardapioRepositoryWrite.salvar(any(ItemCardapio.class))).thenAnswer(inv -> inv.getArgument(0));

        var criado = useCase.criar(command);

        assertEquals("Lanche Natural", criado.getNome());
        assertEquals(0, criado.getPreco().compareTo(new BigDecimal("39.90")));
    }
}