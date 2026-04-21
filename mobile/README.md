# Onboard360 - Plataforma de Onboarding Eurofarma

**Grupo:** 3SIZ - Onboard360  
**Challenge:** Eurofarma - Sistema de Onboarding Corporativo

Plataforma centralizada de onboarding que integra treinamentos, gamificação e compliance para novos colaboradores da Eurofarma.

## Estrutura do Projeto

```
Eurofarma/
├── app/                       # App Android (Kotlin + Jetpack Compose)
│   └── src/main/java/         # Telas: Login, Cursos, Ranking, Perfil, 3D
├── backend/                   # API REST (Spring Boot + Kotlin)
│   ├── src/main/kotlin/       # Controllers, Services, Models, Repositories
│   └── src/test/kotlin/       # Testes unitarios e integracao
├── docs/                      # Documentacao
│   ├── swagger.yaml           # Documentacao OpenAPI/Swagger
│   └── diagrams/              # Diagramas C4 (niveis 1, 2 e 3)
└── README.md                  # Este arquivo
```

## Tecnologias Utilizadas

### Mobile (Android)
- Kotlin 1.9.21
- Jetpack Compose (UI declarativa)
- Navigation Compose
- Filament (renderizacao 3D)
- Material Design 3

### Backend
- Spring Boot 3.2
- Kotlin
- Spring Data JPA
- PostgreSQL
- Swagger/OpenAPI 3.0
- JWT para autenticacao

## Como Executar o App Android

1. Abrir o projeto no Android Studio
2. Aguardar o Gradle Sync
3. Conectar dispositivo ou iniciar emulador
4. Run > Run 'app'

## Como Executar o Backend

```bash
cd backend

# Subir PostgreSQL (Docker)
docker run -d --name eurofarma-db \
  -e POSTGRES_USER=eurofarma \
  -e POSTGRES_PASSWORD=eurofarma123 \
  -e POSTGRES_DB=eurofarma_db \
  -p 5432:5432 postgres:14

# Executar API
./gradlew bootRun

# Swagger UI: http://localhost:8080/swagger-ui.html
```

## Endpoints da API

| Metodo | Endpoint | Descricao |
|--------|----------|-----------|
| POST | /api/auth/login | Autenticacao |
| GET | /api/usuarios/{id} | Perfil do usuario |
| GET | /api/cursos | Listar cursos |
| GET | /api/cursos/{id} | Detalhes do curso |
| POST | /api/cursos/{id}/progresso | Registrar progresso |
| GET | /api/ranking | Ranking geral |
| GET | /api/conquistas | Listar conquistas |

## Testes

```bash
# Backend - Testes unitarios
cd backend && ./gradlew test

# Backend - Testes de integracao  
cd backend && ./gradlew integrationTest

# Mobile - Testes unitarios
./gradlew test
```

---

## ENTREGAVEIS DO PROJETO

### a) Documentacao Swagger das APIs
- **Arquivo**: `docs/swagger.yaml`
- **Conteudo**: Especificacao OpenAPI 3.0 com todos os endpoints, schemas e contratos
- **Visualizar**: http://localhost:8080/swagger-ui.html (com backend rodando)

### b) Esqueleto Funcional da Solucao

**Modulos Mobile** (`app/src/main/java/br/com/inaciasantos/eurofarma/`):
| Arquivo | Descricao |
|---------|-----------|
| LoginScreen.kt | Tela de autenticacao |
| MainScreen.kt | Lista de treinamentos com cards de progresso |
| CursoScreen.kt | Detalhes do curso com video, modulos e comentarios |
| RankingScreen.kt | Ranking gamificado de colaboradores |
| PerfilScreen.kt | Perfil do usuario com conquistas e XP |
| Model3DScreen.kt | Visualizacao de modelo 3D (Filament) |
| AppNavigation.kt | Navegacao entre telas |

**Modulos Backend** (`backend/src/main/kotlin/com/eurofarma/`):
| Pasta | Descricao |
|-------|-----------|
| controller/ | Endpoints REST (CursoController, RankingController) |
| service/ | Logica de negocio (CursoService, GamificacaoService, ProgressoService) |
| repository/ | Acesso a dados JPA |
| model/ | Entidades (Usuario, Curso, Modulo, Licao, Progresso, Conquista) |
| config/ | Configuracoes (Swagger, Security) |

### c) Integracao com Banco de Dados
- **Banco**: PostgreSQL via Spring Data JPA
- **Entidades**: Usuario, Curso, Modulo, Licao, Progresso, Conquista, Comentario
- **Configuracao**: `backend/src/main/resources/application.yml`

### d) README Detalhado
- **Este arquivo** com instrucoes completas de execucao

### e) Diagramas C4

| Nivel | Arquivo | Descricao |
|-------|---------|-----------|
| 1 | `docs/diagrams/c4-nivel1-contexto.md` | Visao geral: usuarios e sistemas externos |
| 2 | `docs/diagrams/c4-nivel2-container.md` | Containers: Mobile, API, Database, Cache |
| 3 | `docs/diagrams/c4-nivel3-componente.md` | Componentes internos do backend |

### f) Testes Unitarios e de Integracao

| Arquivo | Tipo | Descricao |
|---------|------|-----------|
| `backend/src/test/.../CursoServiceTest.kt` | Unitario | Testes do servico de cursos |
| `backend/src/test/.../GamificacaoServiceTest.kt` | Unitario | Testes de ranking e conquistas |
| `backend/src/test/.../CursoControllerIntegrationTest.kt` | Integracao | Testes E2E dos endpoints |
| `app/src/test/.../LoginScreenTest.kt` | Unitario | Testes do app Android |
| `app/src/test/.../Model3DScreenTest.kt` | Unitario | Testes do app Android |

---

## Equipe - Grupo Onboard360

| Nome | RM |
|------|-----|
| Tony Khaled Osman | RM553050 |
| Enzo Gabriel Nicolosi Croquer | RM553213 |
| Rickson Shiniti Hirata | RM553921 |
| Bruna Oliveira Gomes | RM553135 |
| Inacia dos Santos Silva | RM553401 |

## Licenca
Copyright 2026 - FIAP - Todos os direitos reservados.
