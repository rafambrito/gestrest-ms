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
1. Pedido envia solicitação de pagamento
2. Pagamento processa cobrança
3. Pagamento retorna status
4. Pedido atualiza estado

## ▶️ Execução
```bash
mvn spring-boot:run
```
