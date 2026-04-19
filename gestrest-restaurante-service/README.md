# 🍴 **GestRest Restaurante Service – Gestão de Restaurantes e Cardápio**

## 📌 Responsabilidade
Cadastro e consulta de restaurantes e cardápios.

## 🧱 Arquitetura
Clean Architecture com Ports and Adapters.
Cliente → Restaurante → Cardápio

## 📂 Estrutura

```
src/main/java/br/com/gestrest/restaurante/service/
├── adapters/
│   ├── in/web/
│   └── out/persistence/
├── application/usecase/
├── domain/
│   ├── model/
│   └── ports/
└── infrastructure/config/
```

## 🧩 Entidades principais

- Restaurante
- ItemCardapio

## ⚙️ Use cases principais

- CriarRestauranteUseCase
- BuscarRestaurantePorIdUseCase
- ListarRestaurantesUseCase
- AtualizarRestauranteUseCase
- ExcluirRestauranteUseCase
- CriarItemCardapioUseCase
- BuscarItemCardapioPorIdUseCase
- ListarItensPorRestauranteUseCase
- AtualizarItemCardapioUseCase
- ExcluirItemCardapioUseCase

## 🌐 Endpoints

- `POST /api/v1/restaurantes`
- `GET /api/v1/restaurantes`
- `GET /api/v1/restaurantes/{id}`
- `PUT /api/v1/restaurantes/{id}`
- `DELETE /api/v1/restaurantes/{id}`
- `POST /api/v1/itens-cardapio`
- `GET /api/v1/itens-cardapio/{id}`
- `GET /api/v1/itens-cardapio?restauranteId={id}`
- `PUT /api/v1/itens-cardapio/{id}`
- `DELETE /api/v1/itens-cardapio/{id}`

## 🔄 Sequência

1. Cliente requisita restaurantes
2. Serviço retorna cardápio
3. Cliente consulta itens
4. Restaurante responde com dados

## ▶️ Execução

```bash
./mvnw spring-boot:run
```
