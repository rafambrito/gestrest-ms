package br.com.gestrest.pagamento.service.infrastructure.config;

public final class KafkaTopics {

    private KafkaTopics() {
    }

    public static final String PEDIDO_CRIADO = "pedido.criado";
    public static final String PAGAMENTO_APROVADO = "pagamento.aprovado";
    public static final String PAGAMENTO_PENDENTE = "pagamento.pendente";
    public static final String PEDIDO_CANCELADO = "pedido.cancelado";
    public static final String PAGAMENTO_REPROCESSADO = "pagamento.reprocessado";
}
