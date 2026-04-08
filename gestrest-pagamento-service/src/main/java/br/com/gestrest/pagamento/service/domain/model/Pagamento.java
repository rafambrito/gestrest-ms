package br.com.gestrest.pagamento.service.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pagamento {

    private Long id;
    private Long pedidoId;
    private Long usuarioId;
    private BigDecimal valor;
    private PagamentoStatusEnum status;
    private LocalDateTime dataCriacao;

    private Pagamento(Long id, Long pedidoId, Long usuarioId, BigDecimal valor, PagamentoStatusEnum status, LocalDateTime dataCriacao) {
        if (pedidoId == null) throw new IllegalArgumentException("Pedido e obrigatorio");
        if (usuarioId == null) throw new IllegalArgumentException("Usuario e obrigatorio");
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Valor invalido");
        this.id = id;
        this.pedidoId = pedidoId;
        this.usuarioId = usuarioId;
        this.valor = valor;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    public static Pagamento criar(Long pedidoId, Long usuarioId, BigDecimal valor) {
        return new Pagamento(null, pedidoId, usuarioId, valor, PagamentoStatusEnum.PENDENTE, LocalDateTime.now());
    }

    public static Pagamento existente(Long id, Long pedidoId, Long usuarioId, BigDecimal valor, PagamentoStatusEnum status, LocalDateTime dataCriacao) {
        return new Pagamento(id, pedidoId, usuarioId, valor, status, dataCriacao);
    }

    public void aprovar() { this.status = PagamentoStatusEnum.APROVADO; }
    public void marcarPendente() { this.status = PagamentoStatusEnum.PENDENTE; }

    public Long getId() { return id; }
    public Long getPedidoId() { return pedidoId; }
    public Long getUsuarioId() { return usuarioId; }
    public BigDecimal getValor() { return valor; }
    public PagamentoStatusEnum getStatus() { return status; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
}
