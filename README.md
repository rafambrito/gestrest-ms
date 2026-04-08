# 🍴 **GestRest MS – Sistema de Gestão de Pedidos de Restaurantes**

**Fase 3 – Tech Challenge - FIAP Pós Tech – Arquitetura e Desenvolvimento em JAVA**

---

## 🧭 Visão geral

Plataforma de gestão de pedidos para restaurantes em arquitetura de microserviços, cobrindo usuários, catálogo, pedidos e pagamentos. A aplicação faz parte da Fase 3 do Tech Challenge.

## 🏗️ Arquitetura

- Microserviços por contexto de negócio.
- Responsabilidades separadas por domínio.
- Clean Architecture com Ports and Adapters (Hexagonal).
- Fluxo principal: Cliente -> pedido-service -> pagamento-service.

## 🧩 Serviços

- auth-service: gestão de usuários e dados de autenticação.
- restaurante-service: cadastro de restaurantes e itens de cardápio.
- pedido-service: criação e consulta de pedidos.
- pagamento-service: processamento de pagamento e publicação de status.

## ▶️ Execução

```bash
docker compose up --build
```

## 🛠️ Tecnologias

- Java 21
- Spring Boot
- Spring Data JPA
- Maven
- PostgreSQL
- Docker

## 📁 Estrutura

```text
gestrest-ms/
├── docker-compose.yml
├── gestrest-auth-service/
├── gestrest-restaurante-service/
├── gestrest-pedido-service/
└── gestrest-pagamento-service/
```

## 🚀 Próximos passos

- JWT
- Kafka
- Resiliência

## 👤 Autor

Rafael Mendonça de Brito (RM369933)
