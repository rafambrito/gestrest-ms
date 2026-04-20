package br.com.gestrest.pedido.service.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.gestrest.pedido.service.adapters.out.persistence.mapper.PedidoPersistenceMapper;
import br.com.gestrest.pedido.service.adapters.out.persistence.repository.PedidoJpaRepository;
import br.com.gestrest.pedido.service.domain.model.Pedido;
import br.com.gestrest.pedido.service.domain.model.ports.out.PedidoRepositoryPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    private final PedidoJpaRepository pedidoJpaRepository;
    private final PedidoPersistenceMapper pedidoPersistenceMapper;

    @Override
    public Pedido salvar(Pedido pedido) {
        return pedidoPersistenceMapper.toDomain(
                pedidoJpaRepository.save(pedidoPersistenceMapper.toEntity(pedido)));
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoJpaRepository.findById(id).map(pedidoPersistenceMapper::toDomain);
    }

    @Override
    public List<Pedido> listarPorUsuario(Long usuarioId) {
        return pedidoJpaRepository.findByUsuarioIdOrderByIdDesc(usuarioId).stream()
                .map(pedidoPersistenceMapper::toDomain)
                .toList();
    }
}
