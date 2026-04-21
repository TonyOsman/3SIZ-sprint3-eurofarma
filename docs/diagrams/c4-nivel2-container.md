# Diagrama C4 - Nivel 2: Container

```
+------------------------------------------------------------------+
|                    PLATAFORMA DE TREINAMENTO                      |
+------------------------------------------------------------------+

+------------------+                              +------------------+
|   Colaborador    |                              |     Gestor/RH    |
|    [Pessoa]      |                              |     [Pessoa]     |
+--------+---------+                              +--------+---------+
         |                                                 |
         | HTTPS                                          | HTTPS
         v                                                 v
+------------------+                              +------------------+
|    App Mobile    |                              |   Portal Web     |
|    [Android]     |                              |   (Futuro)       |
|                  |                              |   [SPA React]    |
| Kotlin/Compose   |                              |                  |
| Filament 3D      |                              |                  |
+--------+---------+                              +--------+---------+
         |                                                 |
         +------------------------+------------------------+
                                  |
                                  | REST/JSON + JWT
                                  v
                         +------------------+
                         |    API Gateway   |
                         |  [Spring Cloud]  |
                         +--------+---------+
                                  |
         +------------------------+------------------------+
         |                        |                        |
         v                        v                        v
+------------------+     +------------------+     +------------------+
|  Auth Service    |     |  Training        |     |  Gamification    |
|                  |     |  Service         |     |  Service         |
| [Spring Boot]    |     | [Spring Boot]    |     | [Spring Boot]    |
|                  |     |                  |     |                  |
| - Login/Logout   |     | - Cursos/Modulos |     | - Ranking        |
| - JWT Tokens     |     | - Licoes         |     | - XP/Pontos      |
| - Refresh Token  |     | - Progresso      |     | - Conquistas     |
+--------+---------+     +--------+---------+     +--------+---------+
         |                        |                        |
         v                        v                        v
+------------------------------------------------------------------+
|                         PostgreSQL                                |
|                    [Banco de Dados Relacional]                    |
|                                                                   |
| - usuarios, departamentos, perfis                                 |
| - cursos, modulos, licoes, conteudos                             |
| - progresso_usuario, comentarios                                  |
| - ranking, conquistas, conquistas_usuario                         |
+------------------------------------------------------------------+
                                  |
                                  | Event Streaming
                                  v
                         +------------------+
                         |     Redis        |
                         |  [Cache/Queue]   |
                         |                  |
                         | - Cache sessoes  |
                         | - Cache ranking  |
                         | - Event queue    |
                         +------------------+
```

## Containers

| Container | Tecnologia | Responsabilidade |
|-----------|------------|------------------|
| App Mobile | Android/Kotlin | Interface do usuario mobile |
| API Gateway | Spring Cloud Gateway | Roteamento, rate limiting, autenticacao |
| Auth Service | Spring Boot | Autenticacao e autorizacao |
| Training Service | Spring Boot | Gestao de cursos e progresso |
| Gamification Service | Spring Boot | Sistema de pontos e conquistas |
| PostgreSQL | PostgreSQL 14 | Persistencia de dados |
| Redis | Redis 7 | Cache e filas de eventos |

## Comunicacao

| Origem | Destino | Protocolo | Descricao |
|--------|---------|-----------|-----------|
| Mobile | API Gateway | HTTPS + JWT | Requisicoes autenticadas |
| API Gateway | Services | HTTP | Roteamento interno |
| Services | PostgreSQL | TCP/5432 | Persistencia |
| Services | Redis | TCP/6379 | Cache e eventos |
