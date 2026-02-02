# ENTREGA PARCIAL 4
## Gerenciamento e Rastreabilidade dos Requisitos

**Projeto:** MediTrack - Sistema de Gerenciamento de Medicamentos  
**Disciplina:** Análise de Sistemas de Informação
**Alunos:** Sophia Lannah, João Marcos e Victor Gabriel
**Professor:** Otávio Calaça Xavier  
**Data:** 09/12/2025

---

## 1. Ferramenta de Gerenciamento Escolhida

### 1.1. Trello

Foi escolhida a ferramenta **Trello** para o gerenciamento do projeto devido às seguintes vantagens:

- **Gratuita:** Acesso completo às funcionalidades básicas sem custo
- **Intuitiva:** Interface simples e fácil de usar, adequada para equipes pequenas
- **Flexível:** Permite organização personalizada através de boards, listas e cards
- **Colaborativa:** Facilita o trabalho em dupla com comentários e atribuições
- **Rastreável:** Possibilita o rastreamento de requisitos através de labels, checklists e descrições
- **Acessível:** Disponível via web e aplicativos móveis

### 1.2. Link do Projeto

**Link do Board Trello:** [AQUI DEVE SER INSERIDO O LINK DO BOARD TRELLO]

Exemplo de estrutura:
- `https://trello.com/b/[ID_DO_BOARD]/meditrack-projeto-final`

---

## 2. Estrutura do Projeto no Trello

### 2.1. Organização do Board

O board foi organizado com as seguintes **Listas (Columns)**:

1. **📋 Backlog de Requisitos**
   - Requisitos funcionais (RF01 a RF10)
   - Requisitos não funcionais (RNF01 a RNF08)

2. **📝 Entregas Parciais**
   - Entrega Parcial 1: Definição do Problema e Elicitação
   - Entrega Parcial 2: Casos de Uso
   - Entrega Parcial 3: Diagramas UML
   - Entrega Parcial 4: Gerenciamento e Rastreabilidade
   - Entrega Final: Relatório e Apresentação

3. **🔄 Em Andamento**
   - Tarefas atualmente sendo trabalhadas

4. **✅ Concluído**
   - Tarefas finalizadas

### 2.2. Cards de Requisitos

Cada requisito foi cadastrado como um **Card** separado com as seguintes informações:

**Formato do Card:**

- **Título:** [ID] [Nome do Requisito]
  - Exemplo: `RF01 - Cadastrar Medicamento`
  
- **Descrição:**
  - Tipo: Funcional ou Não Funcional
  - Prioridade: Alta, Média ou Baixa
  - Fonte: Entrevista, Questionário, Observação
  - Descrição detalhada do requisito

- **Labels (Etiquetas):**
  - `Requisito Funcional` (verde)
  - `Requisito Não Funcional` (azul)
  - `Alta Prioridade` (vermelho)
  - `Média Prioridade` (amarelo)
  - `Baixa Prioridade` (cinza)

- **Checklist:** Artefatos relacionados
  - [ ] Caso de Uso
  - [ ] Diagrama de Casos de Uso
  - [ ] Diagrama de Classes
  - [ ] Diagrama de Atividades
  - [ ] Diagrama de Sequência
  - [ ] Diagrama de Estados
  - [ ] Implementação no código

### 2.3. Cards de Entregas Parciais

Cada entrega parcial foi cadastrada como um **Card** com:

- **Título:** Entrega Parcial X - [Nome]
- **Descrição:** Resumo da entrega e requisitos abordados
- **Checklist:** Itens que devem ser entregues
- **Anexos:** Links para documentos e diagramas

---

## 3. Rastreabilidade dos Requisitos

### 3.1. Matriz de Rastreabilidade

A rastreabilidade foi estabelecida conectando cada requisito aos seus artefatos relacionados. A seguir está a matriz completa:

#### Requisitos Funcionais

| ID | Requisito | Casos de Uso | Diagramas UML | Implementação |
|----|-----------|--------------|---------------|---------------|
| RF01 | Cadastrar Medicamento | CU01 | Classes, Atividades | `AddEditMedicationScreen.kt` |
| RF02 | Listar Medicamentos | CU02 | Classes | `MedicationListScreen.kt` |
| RF03 | Visualizar Detalhes | CU03 | Classes, Sequência | `MedicationDetailScreen.kt` |
| RF04 | Editar Medicamento | CU04 | Classes, Atividades | `AddEditMedicationScreen.kt` |
| RF05 | Marcar como Tomado | CU05 | Classes, Sequência, Estados | `MedicationViewModel.kt` |
| RF06 | Cadastro em Lote | CU06 | Classes | `BulkAddMedicationScreen.kt` |
| RF07 | Notificações de Lembrete | CU07 | Classes, Atividades | `NotificationScheduler.kt`, `NotificationWorker.kt` |
| RF08 | Histórico de Uso | CU02, CU03 | Classes | `MedicationListScreen.kt` |
| RF09 | Exportar Histórico PDF | CU08 | Classes | `PdfGenerator.kt` |
| RF10 | Validar Dados | CU01, CU04 | Classes | `Medication.kt` |

#### Requisitos Não Funcionais

| ID | Requisito | Artefatos Relacionados | Implementação |
|----|-----------|------------------------|---------------|
| RNF01 | Performance | Diagrama de Classes | Arquitetura MVVM |
| RNF02 | Usabilidade | Casos de Uso | Interface Jetpack Compose |
| RNF03 | Confiabilidade | Diagrama de Classes | Room Database |
| RNF04 | Disponibilidade | Diagrama de Classes | Armazenamento local |
| RNF05 | Escalabilidade | Diagrama de Classes | Room Database otimizado |
| RNF06 | Segurança | Diagrama de Classes | Armazenamento local |
| RNF07 | Compatibilidade | Documentação | `build.gradle.kts` |
| RNF08 | Notificações | Diagrama de Atividades | WorkManager |

### 3.2. Rastreabilidade no Trello

No Trello, a rastreabilidade é mantida através de:

1. **Descrição dos Cards:** Cada card de requisito contém links para:
   - Cards de casos de uso relacionados
   - Cards de diagramas UML relacionados
   - Cards de implementação relacionados

2. **Comentários:** Utilizados para registrar:
   - Mudanças de escopo
   - Decisões de projeto
   - Atualizações de status

3. **Links:** Cada card possui links diretos para:
   - Documentos de entrega parcial
   - Diagramas UML (arquivos .puml ou imagens)
   - Código fonte (se disponível em repositório Git)

4. **Checklists:** Permitem rastrear o progresso de cada artefato relacionado ao requisito

### 3.3. Exemplo de Card Completo no Trello

**Card: RF01 - Cadastrar Medicamento**

```
Tipo: Requisito Funcional
Prioridade: Alta
Fonte: Entrevista, Questionário, Observação

Descrição:
O sistema deve permitir que o usuário cadastre um medicamento informando 
nome, dosagem, frequência e horários de administração.

Artefatos Relacionados:
- Caso de Uso: CU01 - Cadastrar Medicamento
- Diagrama: Diagrama de Casos de Uso (CU01)
- Diagrama: Diagrama de Classes (Medication, MedicationViewModel)
- Diagrama: Diagrama de Atividades (Processo de Cadastro)
- Implementação: AddEditMedicationScreen.kt, MedicationViewModel.kt

Checklist:
✅ Caso de Uso CU01
✅ Diagrama de Casos de Uso
✅ Diagrama de Classes
✅ Diagrama de Atividades
✅ Implementação no código

Links:
- Caso de Uso: [Link para documento Entrega Parcial 2]
- Diagrama Classes: [Link para diagrama_classes.puml]
- Diagrama Atividades: [Link para diagrama_atividades.puml]
```

---

## 4. Planejamento de Entregas

### 4.1. Backlog de Tarefas

O backlog foi organizado seguindo a ordem das entregas parciais:

#### Sprint 1: Elicitação de Requisitos
- [x] Escolher sistema a ser modelado
- [x] Aplicar técnicas de elicitação (Entrevista, Questionário, Observação)
- [x] Documentar requisitos funcionais
- [x] Documentar requisitos não funcionais
- [x] Elaborar Entrega Parcial 1

#### Sprint 2: Modelagem de Casos de Uso
- [x] Criar casos de uso detalhados
- [x] Elaborar diagrama de casos de uso
- [x] Revisar casos de uso
- [x] Elaborar Entrega Parcial 2

#### Sprint 3: Diagramas UML Estruturais e Comportamentais
- [x] Criar diagrama de classes
- [x] Criar diagrama de atividades
- [x] Criar diagrama de sequência
- [x] Criar diagrama de estados
- [x] Elaborar Entrega Parcial 3

#### Sprint 4: Gerenciamento e Rastreabilidade
- [x] Configurar ferramenta de gerenciamento (Trello)
- [x] Cadastrar todos os requisitos
- [x] Estabelecer rastreabilidade
- [x] Criar matriz de rastreabilidade
- [x] Elaborar Entrega Parcial 4

#### Sprint 5: Relatório Final
- [ ] Consolidar todas as entregas parciais
- [ ] Elaborar relatório final
- [ ] Criar apresentação (slides)
- [ ] Revisar documentação completa
- [ ] Preparar apresentação oral

### 4.2. Timeline do Projeto

| Entrega | Data Limite | Status |
|---------|-------------|--------|
| Entrega Parcial 1 | [Data] | ✅ Concluído |
| Entrega Parcial 2 | [Data] | ✅ Concluído |
| Entrega Parcial 3 | [Data] | ✅ Concluído |
| Entrega Parcial 4 | [Data] | ✅ Concluído |
| Entrega Final | 10/02/2026 | ⏳ Em andamento |

---

## 5. Evidências de Rastreabilidade

### 5.1. Capturas de Tela do Trello

**Nota:** As capturas de tela devem ser anexadas mostrando:
- Board completo do projeto
- Cards de requisitos detalhados
- Matriz de rastreabilidade visual
- Checklists preenchidas

### 5.2. Links Diretos

Cada requisito no Trello possui links diretos para:
- Documentos Markdown das entregas parciais
- Arquivos PlantUML dos diagramas
- Código fonte (quando aplicável)

---

## 6. Métricas de Rastreabilidade

### 6.1. Cobertura de Rastreabilidade

- **Total de Requisitos:** 18 (10 funcionais + 8 não funcionais)
- **Requisitos Rastreados:** 18 (100%)
- **Artefatos Criados:** 
  - 8 Casos de Uso
  - 4 Diagramas UML (Classes, Atividades, Sequência, Estados)
  - 1 Diagrama de Casos de Uso

### 6.2. Status dos Requisitos

- **Implementados:** 18/18 (100%)
- **Documentados:** 18/18 (100%)
- **Rastreados:** 18/18 (100%)

---

## 7. Conclusão

O gerenciamento de requisitos através do Trello permitiu:

1. **Organização:** Todos os requisitos e artefatos estão organizados e acessíveis
2. **Rastreabilidade:** É possível rastrear cada requisito desde sua origem até a implementação
3. **Colaboração:** A dupla pode trabalhar de forma colaborativa e acompanhar o progresso
4. **Controle:** O checklist permite verificar se todos os artefatos foram criados
5. **Histórico:** Comentários e mudanças de status mantêm o histórico do projeto

A matriz de rastreabilidade garante que todos os requisitos estão conectados aos seus respectivos artefatos, facilitando a validação e a manutenção do sistema.

---

**Documento elaborado por:** [Nomes dos integrantes da dupla]  
**Data de conclusão:** [Data]

**Link do Board Trello:** [INSERIR LINK AQUI]
