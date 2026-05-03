# 💳 **GestRest Pagamento Service – Processamento de Pagamentos**

## 📌 Responsabilidade
Serviço de pagamento de pedidos. Processa cobrança, define status e publica eventos.

## 🧱 Arquitetura
- Clean Architecture
- Ports and Adapters (Hexagonal)
Pedido → Pagamento → Status

## 📂 Estrutura
- `domain` (model, model.event, ports.in, ports.out)
- `application` (usecase.command, usecase.impl)
- `adapters` (in.web, out.integration, out.persistence, out.event)
- `infrastructure.config`

## � Entidades principais
- Pagamento
- PagamentoStatusEnum

## ⚙️ Use cases principais
- ProcessarPagamentoUseCase

## 🌐 Endpoints
- `POST /api/v1/pagamentos`

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
mvn spring-boot:run
```

## ▶️ Execução em Docker
```bash
docker compose up --build
```

## 📖 Documentação da API (Swagger)

Após iniciar o serviço, acesse a documentação Swagger em: [http://localhost:8084/swagger-ui.html](http://localhost:8084/swagger-ui.html)
