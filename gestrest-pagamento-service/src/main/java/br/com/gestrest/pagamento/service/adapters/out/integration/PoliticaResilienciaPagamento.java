package br.com.gestrest.pagamento.service.adapters.out.integration;

import org.springframework.stereotype.Component;

@Component
public class PoliticaResilienciaPagamento {

    private final int maxTentativas = 3;
    private final long timeoutMillis = 1500;

    public int getMaxTentativas() { return maxTentativas; }
    public long getTimeoutMillis() { return timeoutMillis; }
}
