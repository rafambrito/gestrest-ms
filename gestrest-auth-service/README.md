# gestrest-auth-service

Gestão de usuários e autenticação.

## 📌 Responsabilidade
Autenticação e gestão de usuários.

## 🧱 Arquitetura
Clean Architecture com Ports and Adapters.
Cliente → Auth → Token

## 📂 Estrutura

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

## 🧩 Entidades principais

- Usuario
- TipoUsuario

## ⚙️ Use cases principais

- CriarUsuarioUseCase
- AutenticarUsuarioUseCase

## 🌐 Endpoints

- `POST /api/v1/usuarios`
- `POST /api/v1/auth/login`

## 🔄 Sequência

1. Cliente envia credenciais
2. Auth valida usuário
3. Auth retorna token

## ▶️ Execução

```bash
./mvnw spring-boot:run
```
