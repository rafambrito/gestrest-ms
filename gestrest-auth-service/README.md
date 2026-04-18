# gestrest-auth-service

Gestão de usuários e autenticação.

## Arquitetura

Clean Architecture com Ports and Adapters.

## Estrutura

```
src/main/java/br/com/gestrest/auth/service/
├── adapters/
│   ├── in/web/
│   └── out/persistence/
├── application/usecase/
├── domain/
│   ├── model/
│   └── ports/
└── infrastructure/config/
```

## Entidades

- Usuario
- TipoUsuario

## Use Cases

- CriarUsuarioUseCase
- AutenticarUsuarioUseCase

## Endpoints

- `POST /api/v1/usuarios`
- `POST /api/v1/auth/login`

## Execução

```bash
./mvnw spring-boot:run
```
