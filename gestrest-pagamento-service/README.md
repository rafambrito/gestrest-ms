# 💳 **GestRest Pagamento Service – Processamento de Pagamentos**

## 📌 Responsabilidade
Serviço de pagamento de pedidos. Processa cobrança, define status e publica eventos.

## 🧱 Arquitetura
Clean Architecture com Ports and Adapters.

## 📂 Estrutura

```
src/main/java/br/com/gestrest/pagamento/service/
├── adapters/
│   ├── in/web/
│   ├── out/integration/
│   ├── out/persistence/
│   └── out/event/
├── application/usecase/
├── domain/
│   ├── model/
│   └── ports/
└── infrastructure/config/
```

## 🧩 Entidades principais

- Pagamento
- PagamentoStatusEnum

## ⚙️ Use cases principais

- ProcessarPagamentoUseCase
- BuscarPagamentoPorIdUseCase

## 📖 Documentação da API (Swagger)

Após iniciar o serviço, acesse a documentação Swagger em: [http://localhost:8084/swagger-ui.html](http://localhost:8084/swagger-ui.html)

## 🌐 Endpoints

- `POST /api/v1/pagamentos`
- `GET /api/v1/pagamentos/{id}`

## 🔄 Sequência

1. Pedido cria registro de pagamento
2. Pagamento envia requisição a procpag
3. Pagamento consulta status externo
4. Pagamento atualiza status em PostgreSQL
5. Evento de aprovação ou pendência é publicado

## 🌐 Integração com procpag

- `POST /requisicao` com `valor`, `pagamento_id`, `cliente_id`
- `GET /requisicao/{pagamento_id}` para consulta de status
- sucesso → APROVADO
- falha/timeout → PENDENTE

## 📦 Banco de dados

- PostgreSQL
- tabela `pagamento`
- campos: `id`, `pedido_id`, `usuario_id`, `valor`, `status`, `pagamento_id_externo`

## ▶️ Execução local

```bash
./mvnw spring-boot:run
```

## ▶️ Execução em Docker

```bash
docker compose up --build
```

## 🧪 Testes

```bash
./mvnw test
```

## 📊 Cobertura Jacoco

Após executar os testes, abra o relatório local em `target/site/jacoco/index.html`.

```bash
./mvnw test
```
