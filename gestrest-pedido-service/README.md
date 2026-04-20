# 📦 **GestRest Pedido Service – Gestão de Pedidos**

## 📌 Responsabilidade
Processar pedidos e orquestrar o fluxo de restaurante e pagamento.

## 🧱 Arquitetura
Clean Architecture com Ports and Adapters.
Cliente → Pedido → Pagamento

## 📂 Estrutura

```
src/main/java/br/com/gestrest/pedido/service/
├── adapters/
│   ├── in/web/
│   └── out/
│       ├── persistence/
│       ├── integration/
│       └── event/
├── application/usecase/
├── domain/
│   ├── model/
│   └── ports/
└── infrastructure/config/
```

## 🧩 Entidades principais

- Pedido
- ItemPedido
- Restaurante
- ItemCardapio

## ⚙️ Use cases principais

- CriarPedidoUseCase
- BuscarPedidoPorIdUseCase
- ListarPedidosPorUsuarioUseCase
- AtualizarStatusPedidoUseCase
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

- `POST /api/v1/pedidos`
- `GET /api/v1/pedidos/{id}`
- `GET /api/v1/pedidos/usuario/{usuarioId}`
- `POST /api/v1/restaurantes`
- `GET /api/v1/restaurantes`
- `GET /api/v1/restaurantes/{id}`
- `PUT /api/v1/restaurantes/{id}`
- `DELETE /api/v1/restaurantes/{id}`
- `POST /api/v1/itens-cardapio`
- `GET /api/v1/itens-cardapio/{id}`
- `GET /api/v1/itens-cardapio/restaurante/{restauranteId}`
- `PUT /api/v1/itens-cardapio/{id}`
- `DELETE /api/v1/itens-cardapio/{id}`

## 🔄 Sequência

1. Pedido criado
2. Pedido chama pagamento
3. Pagamento responde
4. Pedido atualiza status:
   * PAGO
   * PENDENTE_PAGAMENTO

## ▶️ Execução

```bash
./mvnw spring-boot:run
```
