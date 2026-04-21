# Diagrama C4 - Nivel 1: Contexto do Sistema

```
+------------------------------------------------------------------+
|                        EUROFARMA TRAINING                         |
+------------------------------------------------------------------+

                          +------------------+
                          |   Colaborador    |
                          |    Eurofarma     |
                          |    [Pessoa]      |
                          +--------+---------+
                                   |
                                   | Acessa treinamentos,
                                   | acompanha progresso,
                                   | visualiza ranking
                                   v
                          +------------------+
                          |    Plataforma    |
                          |  de Treinamento  |
                          |    Eurofarma     |
                          |    [Sistema]     |
                          +--------+---------+
                                   |
              +--------------------+--------------------+
              |                    |                    |
              v                    v                    v
    +------------------+  +------------------+  +------------------+
    |    Sistema de    |  |    Provedor de   |  |    Sistema de    |
    |       RH         |  |      Video       |  |    Notificacao   |
    | [Sistema Externo]|  | [Sistema Externo]|  | [Sistema Externo]|
    +------------------+  +------------------+  +------------------+
    Sincroniza dados      Hospeda videos      Envia push
    de funcionarios       dos cursos         notifications
```

## Descricao

O sistema de Treinamento Corporativo da Eurofarma permite que colaboradores:
- Realizem cursos obrigatorios e especificos
- Acompanhem seu progresso de aprendizagem
- Participem de sistema de gamificacao com ranking
- Desbloqueiem conquistas e acumulem XP
- Visualizem conteudo em video com modelo 3D

### Atores

| Ator | Descricao |
|------|-----------|
| Colaborador Eurofarma | Usuario principal que consome os treinamentos |
| Gestor/RH | Administra cursos e acompanha progresso da equipe |

### Sistemas Externos

| Sistema | Integracao |
|---------|------------|
| Sistema RH | Sincronizacao de dados de funcionarios (API REST) |
| Provedor de Video | Hospedagem e streaming de conteudo (CDN) |
| Sistema de Notificacao | Push notifications (Firebase Cloud Messaging) |
