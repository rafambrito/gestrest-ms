package br.com.gestrest.pedido.service.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {

    private Long id;
    private Long usuarioId;
    private Long restauranteId;
    private List<ItemPedido> itens;
    private BigDecimal valorTotal;
    private PedidoStatusEnum status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaAlteracao;

    private Pedido(Long id, Long usuarioId, Long restauranteId, List<ItemPedido> itens, PedidoStatusEnum status,
            LocalDateTime dataCriacao, LocalDateTime dataUltimaAlteracao) {
        if (usuarioId == null) {
            throw new IllegalArgumentException("Usuario e obrigatorio");
        }
        if (restauranteId == null) {
            throw new IllegalArgumentException("Restaurante e obrigatorio");
        }
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Pedido deve ter ao menos um item");
        }
        this.id = id;
        this.usuarioId = usuarioId;
        this.restauranteId = restauranteId;
        this.itens = new ArrayList<>(itens);
        this.valorTotal = calcularValorTotal();
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public static Pedido criar(Long usuarioId, Long restauranteId, List<ItemPedido> itens) {
        return new Pedido(null, usuarioId, restauranteId, itens, PedidoStatusEnum.CRIADO, LocalDateTime.now(), null);
    }

    public static Pedido existente(Long id, Long usuarioId, Long restauranteId, List<ItemPedido> itens, BigDecimal valorTotal,
            PedidoStatusEnum status, LocalDateTime dataCriacao, LocalDateTime dataUltimaAlteracao) {
        var pedido = new Pedido(id, usuarioId, restauranteId, itens, status, dataCriacao, dataUltimaAlteracao);
        pedido.valorTotal = valorTotal;
        return pedido;
    }

    private BigDecimal calcularValorTotal() {
        return itens.stream().map(ItemPedido::subtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void marcarPendentePagamento() {
        this.status = PedidoStatusEnum.PENDENTE_PAGAMENTO;
        this.dataUltimaAlteracao = LocalDateTime.now();
    }

    public void marcarPago() {
        this.status = PedidoStatusEnum.PAGO;
        this.dataUltimaAlteracao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public PedidoStatusEnum getStatus() {
        return status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }
}
