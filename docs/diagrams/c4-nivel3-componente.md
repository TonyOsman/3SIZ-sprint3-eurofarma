# Diagrama C4 - Nivel 3: Componentes

## Training Service - Detalhamento

```
+------------------------------------------------------------------+
|                       TRAINING SERVICE                            |
|                        [Spring Boot]                              |
+------------------------------------------------------------------+

+------------------------------------------------------------------+
|                      Controllers (REST API)                       |
+------------------------------------------------------------------+
|                                                                   |
|  +----------------+  +----------------+  +----------------+       |
|  | CursoController|  |ModuloController|  |LicaoController |       |
|  |                |  |                |  |                |       |
|  | GET /cursos    |  | GET /modulos   |  | GET /licoes    |       |
|  | GET /cursos/{id}  | POST /modulos  |  | PUT /licoes/{id}       |
|  | POST /cursos   |  | PUT /modulos   |  | POST /progresso|       |
|  +-------+--------+  +-------+--------+  +-------+--------+       |
|          |                   |                   |                |
+----------+-------------------+-------------------+----------------+
           |                   |                   |
           v                   v                   v
+------------------------------------------------------------------+
|                        Services (Logica de Negocio)               |
+------------------------------------------------------------------+
|                                                                   |
|  +----------------+  +----------------+  +----------------+       |
|  | CursoService   |  | ModuloService  |  | ProgressoService      |
|  |                |  |                |  |                |       |
|  | - listarCursos |  | - criarModulo  |  | - registrar    |       |
|  | - obterDetalhes|  | - atualizarOrdem  | - calcularXP   |       |
|  | - calcProgress |  | - vincularLicoes  | - verificar    |       |
|  +-------+--------+  +-------+--------+  |   Conquistas   |       |
|          |                   |           +-------+--------+       |
|          |                   |                   |                |
+----------+-------------------+-------------------+----------------+
           |                   |                   |
           v                   v                   v
+------------------------------------------------------------------+
|                      Repositories (Data Access)                   |
+------------------------------------------------------------------+
|                                                                   |
|  +----------------+  +----------------+  +----------------+       |
|  |CursoRepository |  |ModuloRepository|  |ProgressoRepository    |
|  | [JPA/Hibernate]|  | [JPA/Hibernate]|  | [JPA/Hibernate]|       |
|  +----------------+  +----------------+  +----------------+       |
|                                                                   |
+------------------------------------------------------------------+
           |                   |                   |
           +-------------------+-------------------+
                               |
                               v
                      +------------------+
                      |    PostgreSQL    |
                      |    [Database]    |
                      +------------------+


+------------------------------------------------------------------+
|                         Domain Models                             |
+------------------------------------------------------------------+
|                                                                   |
|  +----------------+  +----------------+  +----------------+       |
|  |     Curso      |  |     Modulo     |  |     Licao      |       |
|  +----------------+  +----------------+  +----------------+       |
|  | id: Long       |  | id: Long       |  | id: Long       |       |
|  | titulo: String |  | titulo: String |  | titulo: String |       |
|  | descricao: Text|  | ordem: Int     |  | subtitulo: Str |       |
|  | tipo: Enum     |  | curso: Curso   |  | videoUrl: Str  |       |
|  | thumbnail: Str |  | licoes: List   |  | modulo: Modulo |       |
|  | modulos: List  |  +----------------+  +----------------+       |
|  +----------------+                                               |
|                                                                   |
|  +----------------+  +----------------+  +----------------+       |
|  |   Progresso    |  |   Comentario   |  |   Usuario      |       |
|  +----------------+  +----------------+  +----------------+       |
|  | id: Long       |  | id: Long       |  | id: Long       |       |
|  | usuario: User  |  | usuario: User  |  | nome: String   |       |
|  | licao: Licao   |  | curso: Curso   |  | email: String  |       |
|  | concluida: Bool|  | texto: String  |  | departamento   |       |
|  | concluidaEm: DT|  | criadoEm: DT   |  | xpTotal: Int   |       |
|  +----------------+  +----------------+  +----------------+       |
|                                                                   |
+------------------------------------------------------------------+


+------------------------------------------------------------------+
|                    Cross-Cutting Concerns                         |
+------------------------------------------------------------------+
|                                                                   |
|  +------------------+  +------------------+  +------------------+ |
|  | SecurityConfig   |  | ExceptionHandler |  | EventPublisher   | |
|  |                  |  |                  |  |                  | |
|  | - JWT Filter     |  | - GlobalHandler  |  | - ProgressEvent  | |
|  | - CORS Config    |  | - Validation     |  | - AchievementEvt | |
|  | - Endpoints      |  | - Error Response |  | - XPEvent        | |
|  +------------------+  +------------------+  +------------------+ |
|                                                                   |
+------------------------------------------------------------------+
```

## Descricao dos Componentes

### Controllers
| Componente | Responsabilidade |
|------------|------------------|
| CursoController | Endpoints CRUD de cursos |
| ModuloController | Gerenciamento de modulos |
| LicaoController | Licoes e registro de progresso |
| ComentarioController | CRUD de comentarios |

### Services
| Componente | Responsabilidade |
|------------|------------------|
| CursoService | Logica de listagem e detalhes de cursos |
| ModuloService | Ordenacao e vinculacao de licoes |
| ProgressoService | Calculo de progresso e XP |
| EventPublisher | Publicacao de eventos para gamificacao |

### Repositories
| Componente | Tecnologia | Responsabilidade |
|------------|------------|------------------|
| CursoRepository | Spring Data JPA | Acesso a tabela cursos |
| ModuloRepository | Spring Data JPA | Acesso a tabela modulos |
| LicaoRepository | Spring Data JPA | Acesso a tabela licoes |
| ProgressoRepository | Spring Data JPA | Acesso a tabela progresso |

### Models
| Entidade | Relacionamentos |
|----------|-----------------|
| Curso | 1:N Modulos |
| Modulo | 1:N Licoes, N:1 Curso |
| Licao | N:1 Modulo |
| Progresso | N:1 Usuario, N:1 Licao |
| Usuario | 1:N Progresso, 1:N Comentarios |
