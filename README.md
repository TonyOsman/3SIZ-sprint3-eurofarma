# Eurofarma - Plataforma de Treinamento Corporativo

Sistema de treinamento corporativo com gamificacao para colaboradores da Eurofarma.

## Arquitetura

- **Mobile**: App Android (Kotlin + Jetpack Compose)
- **Backend**: API REST (Spring Boot + Kotlin)
- **Banco de Dados**: PostgreSQL
- **Documentacao**: Swagger/OpenAPI 3.0

## Estrutura do Projeto

```
eurofarma-projeto/
├── mobile/                    # App Android
│   └── app/src/main/java/     # Codigo fonte Kotlin
├── backend/                   # API REST
│   ├── src/main/kotlin/       # Codigo fonte
│   └── src/test/kotlin/       # Testes
├── docs/                      # Documentacao
│   ├── diagrams/              # Diagramas C4
│   └── swagger.yaml           # Especificacao OpenAPI
└── README.md
```

## Pre-requisitos

### Backend
- JDK 17+
- Gradle 8.x
- PostgreSQL 14+
- Docker (opcional)

### Mobile
- Android Studio Hedgehog+
- SDK Android 24+ (minSdk) / 34 (targetSdk)
- Kotlin 1.9+

## Como Executar

### 1. Banco de Dados

```bash
# Via Docker
docker run -d --name eurofarma-db \
  -e POSTGRES_USER=eurofarma \
  -e POSTGRES_PASSWORD=eurofarma123 \
  -e POSTGRES_DB=eurofarma_db \
  -p 5432:5432 \
  postgres:14

# Ou configure manualmente no PostgreSQL local
```

### 2. Backend

```bash
cd backend

# Configurar variaveis de ambiente (opcional)
export DB_HOST=localhost
export DB_PORT=5432
export DB_NAME=eurofarma_db
export DB_USER=eurofarma
export DB_PASSWORD=eurofarma123

# Executar
./gradlew bootRun

# API disponivel em: http://localhost:8080
# Swagger UI: http://localhost:8080/swagger-ui.html
```

### 3. Mobile

```bash
cd mobile

# Via Android Studio
# 1. Abrir o projeto no Android Studio
# 2. Sync Gradle
# 3. Run > Run 'app'

# Via linha de comando
./gradlew assembleDebug
./gradlew installDebug
```

## Endpoints da API

| Metodo | Endpoint | Descricao |
|--------|----------|-----------|
| POST | /api/auth/login | Autenticacao |
| GET | /api/usuarios/{id} | Perfil do usuario |
| PUT | /api/usuarios/{id} | Atualizar perfil |
| GET | /api/cursos | Listar cursos |
| GET | /api/cursos/{id} | Detalhes do curso |
| POST | /api/cursos/{id}/progresso | Registrar progresso |
| GET | /api/ranking | Ranking geral |
| GET | /api/ranking/departamento/{nome} | Ranking por departamento |
| GET | /api/conquistas | Listar conquistas |
| POST | /api/conquistas/{id}/desbloquear | Desbloquear conquista |

## Testes

### Backend
```bash
cd backend
./gradlew test              # Testes unitarios
./gradlew integrationTest   # Testes de integracao
./gradlew jacocoTestReport  # Relatorio de cobertura
```

### Mobile
```bash
cd mobile
./gradlew test              # Testes unitarios
./gradlew connectedTest     # Testes instrumentados
```

## Documentacao

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs
- **Diagramas C4**: docs/diagrams/

## Variaveis de Ambiente

| Variavel | Descricao | Padrao |
|----------|-----------|--------|
| DB_HOST | Host do PostgreSQL | localhost |
| DB_PORT | Porta do PostgreSQL | 5432 |
| DB_NAME | Nome do banco | eurofarma_db |
| DB_USER | Usuario do banco | eurofarma |
| DB_PASSWORD | Senha do banco | eurofarma123 |
| JWT_SECRET | Secret para JWT | (gerado) |
| JWT_EXPIRATION | Expiracao JWT (ms) | 86400000 |

## Licenca

Copyright 2026 Inacia Santos - Todos os direitos reservados.
