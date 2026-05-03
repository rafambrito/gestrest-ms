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

## � Documentação da API (Swagger)

Após iniciar os serviços, acesse a documentação Swagger de cada microserviço:

- **Auth Service** (porta 8081): [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
- **Restaurante Service** (porta 8082): [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)
- **Pedido Service** (porta 8083): [http://localhost:8083/swagger-ui.html](http://localhost:8083/swagger-ui.html)
- **Pagamento Service** (porta 8084): [http://localhost:8084/swagger-ui.html](http://localhost:8084/swagger-ui.html)

## �📁 Estrutura

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
