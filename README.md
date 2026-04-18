# gestrest-ms

Sistema de gestão de pedidos de restaurantes em arquitetura de microserviços.

## Arquitetura

Microserviços Spring Boot com Clean Architecture (Ports & Adapters) e comunicação síncrona via REST.

### Serviços

- **auth-service**: Autenticação e gestão de usuários
- **restaurante-service**: Gestão de restaurantes e cardápios
- **pedido-service**: Processamento de pedidos
- **pagamento-service**: Processamento de pagamentos

## Tecnologias

- Java 21
- Spring Boot 3
- PostgreSQL
- Docker Compose
- Maven

## Execução

```bash
docker compose up --build
```

## Estrutura

```
gestrest-ms/
├── gestrest-auth-service/
├── gestrest-restaurante-service/
├── gestrest-pedido-service/
├── gestrest-pagamento-service/
└── docker-compose.yml
```

## Próximos Passos

- Implementação JWT
- Mensageria com Kafka
- Circuit Breaker com Resilience4j
- Observabilidade com Micrometer

## 🚀 Próximos passos

- JWT
- Kafka
- Resiliência

## 👤 Autor

Rafael Mendonça de Brito (RM369933)
