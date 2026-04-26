# 🔐 **GestRest Auth Service – Gestão de Usuários e Acesso**

## 📌 Responsabilidade
Autenticação, gestão de usuários e proteção de endpoints via JWT.

## 🧱 Arquitetura
Clean Architecture com Ports and Adapters.

## 📂 Estrutura

```
src/main/java/br/com/gestrest/auth/service/
├── adapters/
│   ├── in/web/
│   │   ├── controller/
│   │   ├── filter/
│   │   ├── dto/
│   │   └── mapper/
│   └── out/persistence/
├── application/usecase/
├── domain/
│   ├── model/
│   └── ports/
└── infrastructure/config/
```

## 🧩 Entidades principais

- **Usuario**: Usuário do sistema com dados de autenticação
- **TipoUsuario**: Tipo/role do usuário (Cliente, Dono de Restaurante)

## 👤 Roles

- `ROLE_CLIENTE`: Usuário cliente
- `ROLE_DONO`: Dono de restaurante

## ⚙️ Use cases principais

- CriarUsuarioUseCase
- AutenticarUsuarioUseCase

## 🔐 Autenticação (JWT)

Token JWT contém:
- `userId`: ID do usuário
- `tipoUsuario`: Tipo de usuário (define a role)
- `subject`: Login do usuário

Validação e extração de claims realizados via JwtTokenProviderAdapter.

## 🔒 Proteção de Endpoints

### Públicos
- `POST /api/v1/auth/login`
- `POST /api/v1/usuarios`

### Protegidos (requerem token JWT)
- Demais endpoints (quando implementados)

A segurança é aplicada via `SecurityConfig` e `JwtAuthenticationFilter`. O filtro extrai o token do header `Authorization: Bearer <token>`, valida e monta o `SecurityContext` com UserDetails baseado nos dados reais do usuário.

## 🌐 Endpoints

| Método | Path | Descrição | Autenticação |
|--------|------|-----------|--------------|
| POST | `/api/v1/auth/login` | Login e geração de JWT | Não |
| POST | `/api/v1/usuarios` | Criar novo usuário | Não |

## 🔄 Fluxo de Autenticação

1. Cliente faz login com credenciais (`POST /api/v1/auth/login`)
2. Auth Service valida usuário e senha
3. Auth Service gera JWT com dados reais (id, tipoUsuario)
4. Cliente armazena o token
5. Cliente envia token em requisições subsequentes (`Authorization: Bearer <token>`)
6. JwtAuthenticationFilter valida o token e monta o SecurityContext com roles corretas

## ▶️ Execução

```bash
./mvnw spring-boot:run
```

## 🧪 Testes

```bash
./mvnw test
```
