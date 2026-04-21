# Diagrama C4 - Nível 1: Contexto do Sistema

## Onboard360 - Plataforma de Onboarding Eurofarma

[![Diagrama de Contexto - Nível 1](https://mermaid.ink/img/pako:eNp9k01P1EAYx7_KZE6QLCxL125pCElZXjRhYaWYGKmHaTvdTmhn6rwoCtw8S4gkRi5Go_FojDHxvt_EL6AfwZlpWagHepp5nvk9_-etJzBhKYY-zAr2IskRl-BgPaJAf0LFE46qHCihECdMHEbwkVDTj-Ycwaf1K_MlrECx9v79cPEZDM2FcZQyvhrz7trcLnvOQKZoQhi18HwbViQ1j2v-Cxg29-42FvI6xohRoi8I4GeKVLgdgeeWPf8ENq0X7N-vqSAtCSVC2mRuGEzTiP5XI6MxQzx13CUb6803sDeztMRSwnEidSmzPpkPVZXl3n4HQVWBEYtJgW0SAU05I2mX7IWtOKgimvh99f7Pr3MQjB-AdZQc6cwsFFac0AlYZ0y2xePDOSPz7rWhxkzICcfhwx0Lbegq9Vzm76gSH0vMqZ1kqPuCSyTAZmNrCRWlqOu5As1DsDMKrcxQccGEDmXsVOI2KFDdiItLEAZjEKokwUJsoURPT9ykqUfUwrKktNjlJdjSDY6RwGBrOLLALpMkIwmafp3-vKV2qz67f2BhYe00goHRQ0ByUuSoVgwSVaoCgcfjCJ6aUTVUs2gNOFuxijPdVSFYnS7OCMWgxBKJFs7zBhwympGJ4m3RbawNHBdITn_UP0zD1rQ-glXNmz24tpDaksZtgylK78f-ZnhggujR3OnXE7jx1-6xErlx6S7DDpxwkkJfcoU7sMS8ROYKTwwUQZnjEkfQ18cU8aMIRvRMMxWiTxgrrzHO1CSHfoYKoW-qSpHEGwTpJStnVq4HhPmQKSqh3_McGwT6J_AY-gues9jzvL7nOp7bd5xeB76E_sBb7A9c11serDgrg8HyWQe-sqK9xd5S75677HkDZ6Xvat_ZPxpukCE?type=png)](https://mermaid.ai/live/edit#pako:eNp9k01P1EAYx7_KZE6QLCxL125pCElZXjRhYaWYGKmHaTvdTmhn6rwoCtw8S4gkRi5Go_FojDHxvt_EL6AfwZlpWagHepp5nvk9_-etJzBhKYY-zAr2IskRl-BgPaJAf0LFE46qHCihECdMHEbwkVDTj-Ycwaf1K_MlrECx9v79cPEZDM2FcZQyvhrz7trcLnvOQKZoQhi18HwbViQ1j2v-Cxg29-42FvI6xohRoi8I4GeKVLgdgeeWPf8ENq0X7N-vqSAtCSVC2mRuGEzTiP5XI6MxQzx13CUb6803sDeztMRSwnEidSmzPpkPVZXl3n4HQVWBEYtJgW0SAU05I2mX7IWtOKgimvh99f7Pr3MQjB-AdZQc6cwsFFac0AlYZ0y2xePDOSPz7rWhxkzICcfhwx0Lbegq9Vzm76gSH0vMqZ1kqPuCSyTAZmNrCRWlqOu5As1DsDMKrcxQccGEDmXsVOI2KFDdiItLEAZjEKokwUJsoURPT9ykqUfUwrKktNjlJdjSDY6RwGBrOLLALpMkIwmafp3-vKV2qz67f2BhYe00goHRQ0ByUuSoVgwSVaoCgcfjCJ6aUTVUs2gNOFuxijPdVSFYnS7OCMWgxBKJFs7zBhwympGJ4m3RbawNHBdITn_UP0zD1rQ-glXNmz24tpDaksZtgylK78f-ZnhggujR3OnXE7jx1-6xErlx6S7DDpxwkkJfcoU7sMS8ROYKTwwUQZnjEkfQ18cU8aMIRvRMMxWiTxgrrzHO1CSHfoYKoW-qSpHEGwTpJStnVq4HhPmQKSqh3_McGwT6J_AY-gues9jzvL7nOp7bd5xeB76E_sBb7A9c11serDgrg8HyWQe-sqK9xd5S75677HkDZ6Xvat_ZPxpukCE)

## Descrição do Sistema

O **Onboard360** é a plataforma de onboarding corporativo da Eurofarma que unifica:

| Módulo | Função | Sprint |
|--------|--------|--------|
| **Onboarding Interativo** | Trilhas guiadas com checklists, vídeos e microetapas | Sprint 1 |
| **Gamificação** | XP, badges, ranking - cada tarefa vale 10XP | Sprint 1 |
| **Treinamentos Obrigatórios** | Cursos essenciais por cargo com controle de conclusão | Sprint 2 |
| **Métricas Pessoais** | Dashboard com progresso individual vs média da equipe | Sprint 2 |
| **Chat com IA** | Assistente virtual para dúvidas sobre políticas | Sprint 2 |
| **Compliance Hub** | Políticas internas com aceite eletrônico | Sprint 1 |

## Atores do Sistema

| Ator | Responsabilidades |
|------|-------------------|
| **Colaborador** | Completa onboarding, assiste treinamentos, acumula XP, visualiza ranking |
| **Cuidador/Gestor** | Monitora equipe, propõe desafios semanais, acompanha métricas |
| **Equipe RH** | Configura trilhas por cargo, gera relatórios de compliance |

## Integrações Externas

| Sistema | Protocolo | Dados Trocados |
|---------|-----------|----------------|
| SAP SuccessFactors | API REST | Perfis, cargos, departamentos |
| Sistema LMS | API REST | Cursos, certificados, progresso |
| Firebase FCM | Push | Notificações e lembretes automáticos |
