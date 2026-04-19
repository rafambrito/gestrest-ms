# 🍴 **GestRest MS – Sistema de Gestão de Pedidos de Restaurantes**

**Fase 3 – Tech Challenge - FIAP Pós Tech – Arquitetura e Desenvolvimento em JAVA**

---

## 🧭 Visão geral

Plataforma de gestão de pedidos para restaurantes em arquitetura de microserviços, cobrindo usuários, catálogo, pedidos e pagamentos. A aplicação faz parte da Fase 3 do Tech Challenge.

## 🏗️ Arquitetura

Microserviços Spring Boot com Clean Architecture (Ports & Adapters) e comunicação síncrona via REST.
Cliente → Auth → Restaurante → Pedido → Pagamento

### Serviços

- **auth-service**: autenticação e gestão de usuários
- **restaurante-service**: gestão de restaurantes e cardápios
- **pedido-service**: processamento de pedidos e orquestração
- **pagamento-service**: processamento de pagamentos e status de cobrança

## 🛠️ Tecnologias

- Java 21
- Spring Boot 3
- PostgreSQL
- Docker Compose
- Maven

## ▶️ Execução

```bash
docker compose up --build
```

## 📁 Estrutura

```
gestrest-ms/
├── gestrest-auth-service/
├── gestrest-restaurante-service/
├── gestrest-pedido-service/
├── gestrest-pagamento-service/
└── docker-compose.yml
```

## 🚀 Próximos Passos

- Implementação JWT
- Mensageria com Kafka
- Circuit Breaker com Resilience4j
- Observabilidade com Micrometer

## 👤 Autor

Rafael Mendonça de Brito (RM369933)
