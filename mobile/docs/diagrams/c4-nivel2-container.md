# Diagrama C4 - Nível 2: Containers

## Onboard360 - Arquitetura de Containers

[![Diagrama de Containers - Nível 2](https://mermaid.ink/img/pako:eNqNVM1uGzcQfhWCpxiVnchytBvBMCDLdfNjIYqlxkW6PVC7lJbw7nLBnzqpYyCHHIskRY0EzaVokKJADy2KIkDuepO8QPoIHZK7sii5QffE-Wbmm-HONzzFMU8o7uBJxk_ilAiFRrtRgeCTejwVpEyRlpoIxuXXEf7n5x9-RV9KPfvFABH-xoWaL-YZGbuQt6hnDC5IwoUfpFliwJqqV9lekEit-_kbdHhz7qBFEhVLjU0ELxQ4bPiPf6H9yvbYcj5mGa1DumWJ-hbZHourO9ts5w5XGSvQZ-g2VSWJj6H3vOSSbl9lO3XMPstITguFWnsG9vhPqLv19y_QgAtFMnREx3XiISWxQlcmWmnB17zcyy40hvruPh9e__Tx_XO06wC0joalYMUU7XKuvPKkZLb8-RPUHdxCXxBFT8ijun6V1cu4Tua-5RtcnOaNSCq-ZTE1I--zWHBrz35bGrmtr5Ub1_kL1IUzGrpU28Hto1EDHfApK1bylCCsgN7cYF6jUWV7-T0tJJcN1J_9nejMnA6gi9k7utrHlORswmKiGC8s57M_4MIXmMf71aABvzaZUmA8JMUx1F0h5MWYE5HULT77E92dIx7ZSLAsJcDUS2l8nDGpVruDzVKW5u1LCCMK3ep6HF0pIQ8URtF9JpQmmUdhtfJfojEL5Hbz1VOjmQEVhmz2exEz4tGArNVUwFSvRHjgzsN7B7aBPUOCjFpiVhIGN1hb3EiaMJt1aA5uNCROKWzNkErpBrL2CWHTh4qKomoTNuXzyvbay3JZq-GgP_RckpTuyThHw-7Ac03i3OnvHO33-petl32Z0Pr6TvUWVGj19Kw4RGoh2OuawHkN-jjCN0ejwdA8F0ejCD82--eCIP4TES4GjpbbLI2P1Ovgo4ui9j0X6vRxI7R5NbOOBqznvgTbqTqsLn9J-GIP_8e9wHrR5CWJptMleKmXbfc74VkzvxHUsdC_7wN5eL8BPAMtU-MCeeAGngqW4I4SmjZwTkVOjIlPTVKEVUpzGuEOHBMijiMcFWeQU5LiAed5nSa4nqa4MyGZBEuXCbyme4yAvvM5KkB1VPS4LhTuNMPAkuDOKX6IO-tha6MZhlthuxW2t1qtZgM_wp0g3NgK2u1wM7jRuhEEm2cN_J0t2txoXmteb2-G14P2ZhC2w-DsX4yGhAg?type=png)](https://mermaid.ai/live/edit#pako:eNqNVM1uGzcQfhWCpxiVnchytBvBMCDLdfNjIYqlxkW6PVC7lJbw7nLBnzqpYyCHHIskRY0EzaVokKJADy2KIkDuepO8QPoIHZK7sii5QffE-Wbmm-HONzzFMU8o7uBJxk_ilAiFRrtRgeCTejwVpEyRlpoIxuXXEf7n5x9-RV9KPfvFABH-xoWaL-YZGbuQt6hnDC5IwoUfpFliwJqqV9lekEit-_kbdHhz7qBFEhVLjU0ELxQ4bPiPf6H9yvbYcj5mGa1DumWJ-hbZHourO9ts5w5XGSvQZ-g2VSWJj6H3vOSSbl9lO3XMPstITguFWnsG9vhPqLv19y_QgAtFMnREx3XiISWxQlcmWmnB17zcyy40hvruPh9e__Tx_XO06wC0joalYMUU7XKuvPKkZLb8-RPUHdxCXxBFT8ijun6V1cu4Tua-5RtcnOaNSCq-ZTE1I--zWHBrz35bGrmtr5Ub1_kL1IUzGrpU28Hto1EDHfApK1bylCCsgN7cYF6jUWV7-T0tJJcN1J_9nejMnA6gi9k7utrHlORswmKiGC8s57M_4MIXmMf71aABvzaZUmA8JMUx1F0h5MWYE5HULT77E92dIx7ZSLAsJcDUS2l8nDGpVruDzVKW5u1LCCMK3ep6HF0pIQ8URtF9JpQmmUdhtfJfojEL5Hbz1VOjmQEVhmz2exEz4tGArNVUwFSvRHjgzsN7B7aBPUOCjFpiVhIGN1hb3EiaMJt1aA5uNCROKWzNkErpBrL2CWHTh4qKomoTNuXzyvbay3JZq-GgP_RckpTuyThHw-7Ac03i3OnvHO33-petl32Z0Pr6TvUWVGj19Kw4RGoh2OuawHkN-jjCN0ejwdA8F0ejCD82--eCIP4TES4GjpbbLI2P1Ovgo4ui9j0X6vRxI7R5NbOOBqznvgTbqTqsLn9J-GIP_8e9wHrR5CWJptMleKmXbfc74VkzvxHUsdC_7wN5eL8BPAMtU-MCeeAGngqW4I4SmjZwTkVOjIlPTVKEVUpzGuEOHBMijiMcFWeQU5LiAed5nSa4nqa4MyGZBEuXCbyme4yAvvM5KkB1VPS4LhTuNMPAkuDOKX6IO-tha6MZhlthuxW2t1qtZgM_wp0g3NgK2u1wM7jRuhEEm2cN_J0t2txoXmteb2-G14P2ZhC2w-DsX4yGhAg)

## Containers do Sistema

| Container | Tecnologia | Responsabilidade |
|-----------|------------|------------------|
| **App Mobile** | Kotlin + Jetpack Compose + Filament | Interface mobile com visualização 3D |
| **Portal Web** | React (futuro) | Interface para gestores e RH |
| **API Gateway** | Spring Cloud Gateway | Roteamento, autenticação, rate limiting |
| **Auth Service** | Spring Boot | Login, JWT, refresh tokens, integração SAP |
| **Training Service** | Spring Boot | Cursos, módulos, lições, progresso |
| **Gamification Service** | Spring Boot | XP (10 por tarefa), badges, ranking |
| **Onboarding Service** | Spring Boot | Trilhas guiadas, checklists, etapas |
| **Chat IA Service** | Spring Boot + OpenAI | Assistente virtual para dúvidas |
| **PostgreSQL** | PostgreSQL 14 | Persistência principal |
| **Redis** | Redis 7 | Cache de sessões e ranking |

## Fluxo de Dados

[![Fluxo de Dados - Sequence Diagram](https://mermaid.ink/img/pako:eNplks2O0zAUhV_l4i2dtiZNf7wYqZ2iaqSpKLRFCGVzG3tSi8QOtlNmqCqxYMMGhEBCYsMCJHZI7NjPA8Ej4DTpINHs7POdc30cb0msuSCMWPG8ECoWY4mJwSxS4L8cjZOxzFE5WAJa-PPl_Tc40ymutEGuzTE2rbAPP2GqVzIVx8SwIj6-guHsHCboxAu8PsYWddBnWBiUSqrkmJlUzNsfPiaTlzJGJ7U65sajCvz0-vevdzDT1iVGzB9eRDW7PDk9nTJfTMVpISGVN99vvupKm3ptyGD2YL6AFuayFRfGatvaSr5r5Ub7JGtrdujZBYNHIpHWGYT_5IWXxyMGc0w3CPF-mr0dVKoTBvc3QjnN4MK30fWJOFbIpA4Yct9MK4S7tA1PZv9E738szP4mYIU8EfagVQ23V_kE1drH03YDlN7gqKQYRORcOaN5UTUnu0N571uy8o7f1MPukAZJjOSEOVOIBsmEybBckm3piYhbi0xEpMzkaJ5FJFI77_E_4qnW2cFmdJGsCbvE1PpVkXP_Duq3d7trhOLCnOlCOcJor70PIWxLrgg7ocGg2esOgqDfv-c1GvYb5NpjtNukYW-_Oeh3abhrkJf7ubQZ0O4gbFMahGHQ6QSd3V-E1f3G?type=png)](https://mermaid.ai/live/edit#pako:eNplks2O0zAUhV_l4i2dtiZNf7wYqZ2iaqSpKLRFCGVzG3tSi8QOtlNmqCqxYMMGhEBCYsMCJHZI7NjPA8Ej4DTpINHs7POdc30cb0msuSCMWPG8ECoWY4mJwSxS4L8cjZOxzFE5WAJa-PPl_Tc40ymutEGuzTE2rbAPP2GqVzIVx8SwIj6-guHsHCboxAu8PsYWddBnWBiUSqrkmJlUzNsfPiaTlzJGJ7U65sajCvz0-vevdzDT1iVGzB9eRDW7PDk9nTJfTMVpISGVN99vvupKm3ptyGD2YL6AFuayFRfGatvaSr5r5Ub7JGtrdujZBYNHIpHWGYT_5IWXxyMGc0w3CPF-mr0dVKoTBvc3QjnN4MK30fWJOFbIpA4Yct9MK4S7tA1PZv9E738szP4mYIU8EfagVQ23V_kE1drH03YDlN7gqKQYRORcOaN5UTUnu0N571uy8o7f1MPukAZJjOSEOVOIBsmEybBckm3piYhbi0xEpMzkaJ5FJFI77_E_4qnW2cFmdJGsCbvE1PpVkXP_Duq3d7trhOLCnOlCOcJor70PIWxLrgg7ocGg2esOgqDfv-c1GvYb5NpjtNukYW-_Oeh3abhrkJf7ubQZ0O4gbFMahGHQ6QSd3V-E1f3G)

## Comunicação entre Containers

| Origem | Destino | Protocolo | Descrição |
|--------|---------|-----------|-----------|
| Mobile/Web | API Gateway | HTTPS + JWT | Requisições autenticadas |
| API Gateway | Services | HTTP/gRPC | Roteamento interno |
| Services | PostgreSQL | TCP/5432 | Persistência |
| Services | Redis | TCP/6379 | Cache e eventos |
| Gamification | Training | Events | Notificação de conquistas |
