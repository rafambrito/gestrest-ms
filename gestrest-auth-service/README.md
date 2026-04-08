# 🔐 **GestRest Auth Service – Gestão de Usuários e Acesso**

## 🎯 Responsabilidade

Serviço de usuários e dados de acesso, com validações de cadastro e persistência por tipo de usuário.

## 🏗️ Arquitetura

- Clean Architecture
- Ports and Adapters (Hexagonal)

## 📁 Estrutura

- `domain` (model, exception, ports.in, ports.out)
- `application` (usecase.command, usecase.impl)
- `adapters` (in.web, out.persistence)
- `infrastructure.config`

## 🧱 Entidades principais

- Usuario
- TipoUsuario

## ⚙️ Use cases principais

- CriarUsuarioUseCase

## 🌐 Endpoints

- `POST /api/v1/usuarios`

## ▶️ Execução

```bash
./mvnw spring-boot:run
```
