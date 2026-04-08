# 💳 **GestRest Pagamento Service – Processamento de Pagamentos**

## 🎯 Responsabilidade

Serviço de pagamento de pedidos. Processa cobrança, define status e publica eventos de aprovado ou pendente.

## 🏗️ Arquitetura

- Clean Architecture
- Ports and Adapters (Hexagonal)

## 📁 Estrutura

- `domain` (model, model.event, ports.in, ports.out)
- `application` (usecase.command, usecase.impl)
- `adapters` (in.web, out.integration, out.persistence, out.event)
- `infrastructure.config`

## 🧱 Entidades principais

- Pagamento
- PagamentoStatusEnum

## ⚙️ Use cases principais

- ProcessarPagamentoUseCase

## 🌐 Endpoints

- `POST /api/v1/pagamentos`

## ▶️ Execução

```bash
mvn spring-boot:run
```
