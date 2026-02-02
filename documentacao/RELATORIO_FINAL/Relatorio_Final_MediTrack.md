# RELATÓRIO FINAL
## MediTrack - Sistema de Gerenciamento de Medicamentos

**Projeto Final da Disciplina:** Análise de Sistemas de Informação  
**Professor:** Otávio Calaça Xavier  
**Instituição:** Instituto Federal de Educação, Ciência e Tecnologia - Goiás  
**Campus:** Goiânia  
**Curso:** Bacharelado em Sistemas de Informação  
**Data:** 09/12/2025

---

## 1. Introdução

Este relatório apresenta o trabalho final da disciplina de Análise de Sistemas de Informação, consistindo na análise e modelagem completa do sistema **MediTrack**, um aplicativo mobile Android para gerenciamento de medicamentos.

O MediTrack foi desenvolvido para auxiliar pacientes e cuidadores no controle da administração de medicamentos, oferecendo funcionalidades como cadastro de medicamentos, lembretes automáticos, histórico de uso e exportação de relatórios.

Este documento consolida todas as etapas do projeto, desde a definição do problema e elicitação de requisitos até a modelagem completa do sistema com diagramas UML e a rastreabilidade dos requisitos.

---

## 2. Elicitação de Requisitos

### 2.1. Definição do Problema

O MediTrack é um sistema de gerenciamento de medicamentos desenvolvido para atender à necessidade de controle eficiente da administração medicamentosa. O sistema auxilia pessoas que precisam tomar múltiplos medicamentos diariamente, idosos com dificuldades de memória, pacientes com doenças crônicas e cuidadores responsáveis pelo gerenciamento medicamentoso de terceiros.

**Contexto de Uso:**
O sistema é destinado a dispositivos móveis Android e funciona completamente offline, garantindo privacidade e disponibilidade constante. O contexto de uso abrange desde ambientes domésticos até acompanhamento em consultas médicas.

### 2.2. Técnicas de Elicitação Aplicadas

Foram aplicadas três técnicas de elicitação de requisitos:

#### 2.2.1. Entrevista Estruturada
- **Participante:** Dr. João Silva, Clínico Geral
- **Principais descobertas:**
  - Necessidade de registro detalhado de medicamentos
  - Importância de notificações para melhorar adesão
  - Necessidade de histórico para acompanhamento médico
  - Facilidade de uso, especialmente para idosos
  - Possibilidade de exportação de relatórios

#### 2.2.2. Questionário Online
- **Público-alvo:** Usuários potenciais (pacientes e cuidadores)
- **Respostas coletadas:** 52 questionários válidos
- **Principais necessidades:**
  - 89% consideram notificações muito importantes
  - 76% precisam de histórico detalhado
  - 68% desejam exportar relatórios para médicos
  - 82% preferem interface simples e intuitiva
  - 54% gostariam de cadastrar múltiplos medicamentos rapidamente

#### 2.2.3. Observação de Contexto
- **Local:** Farmácia Comunitária do Centro de Goiânia
- **Observações:**
  - Muitos pacientes anotam medicamentos em papéis
  - Dificuldade em lembrar horários corretos
  - Todos os observados possuíam smartphone
  - Interesse demonstrado por aplicativo de controle

### 2.3. Requisitos Elicitados

#### 2.3.1. Requisitos Funcionais (10 requisitos)

| ID | Requisito | Prioridade | Fonte |
|----|-----------|------------|-------|
| RF01 | Cadastrar Medicamento | Alta | Entrevista, Questionário, Observação |
| RF02 | Listar Medicamentos | Alta | Entrevista, Questionário |
| RF03 | Visualizar Detalhes do Medicamento | Média | Questionário |
| RF04 | Editar Medicamento | Alta | Entrevista, Questionário |
| RF05 | Marcar Medicamento como Tomado | Alta | Entrevista, Questionário, Observação |
| RF06 | Cadastro em Lote | Média | Questionário |
| RF07 | Notificações de Lembrete | Alta | Entrevista, Questionário, Observação |
| RF08 | Histórico de Uso | Alta | Entrevista, Questionário |
| RF09 | Exportar Histórico em PDF | Média | Entrevista, Questionário |
| RF10 | Validar Dados de Cadastro | Alta | Entrevista |

#### 2.3.2. Requisitos Não Funcionais (8 requisitos)

| ID | Requisito | Prioridade | Fonte |
|----|-----------|------------|-------|
| RNF01 | Performance (resposta em até 2s) | Alta | Questionário |
| RNF02 | Usabilidade (interface simples) | Alta | Entrevista, Questionário, Observação |
| RNF03 | Confiabilidade (integridade de dados) | Alta | Entrevista |
| RNF04 | Disponibilidade (funcionamento offline) | Média | Questionário |
| RNF05 | Escalabilidade (até 100 medicamentos) | Média | Questionário |
| RNF06 | Segurança (armazenamento local) | Alta | Entrevista |
| RNF07 | Compatibilidade (Android 7.0+) | Alta | Análise de mercado |
| RNF08 | Notificações confiáveis (WorkManager) | Alta | Entrevista, Questionário |

---

## 3. Modelagem do Sistema

### 3.1. Casos de Uso

Foram identificados e modelados **8 casos de uso** principais:

1. **CU01 - Cadastrar Medicamento:** Permite cadastrar um novo medicamento com todas as informações necessárias
2. **CU02 - Listar Medicamentos:** Exibe lista de todos os medicamentos cadastrados
3. **CU03 - Visualizar Detalhes do Medicamento:** Mostra informações detalhadas de um medicamento
4. **CU04 - Editar Medicamento:** Permite editar informações de um medicamento existente
5. **CU05 - Marcar Medicamento como Tomado:** Registra a administração do medicamento
6. **CU06 - Cadastrar Medicamentos em Lote:** Permite cadastrar múltiplos medicamentos simultaneamente
7. **CU07 - Receber Notificação de Lembrete:** Sistema envia notificações automáticas
8. **CU08 - Exportar Histórico em PDF:** Gera relatório em PDF do histórico de medicamentos

Cada caso de uso foi detalhado com:
- Título e descrição
- Atores envolvidos
- Pré-condições
- Fluxo principal
- Fluxos alternativos
- Fluxos de exceção

### 3.2. Diagrama de Casos de Uso

O diagrama UML de casos de uso foi criado mostrando:
- **Ator Principal:** Usuário
- **Ator Secundário:** Sistema (WorkManager) para notificações
- **Relacionamentos:**
  - CU03 inclui CU04 (para editar)
  - CU03 inclui CU05 (para marcar como tomado)
  - CU04 estende CU01 (comportamento similar)
  - CU07 é iniciado pelo Sistema

O diagrama está disponível em formato PlantUML: `documentacao/ENTREGA_PARCIAL_2/diagrama_casos_uso.puml`

### 3.3. Diagramas UML Estruturais e Comportamentais

#### 3.3.1. Diagrama de Classes

O diagrama de classes apresenta a estrutura estática do sistema com as seguintes classes principais:

**Camada de Dados:**
- `Medication`: Entidade que representa um medicamento
- `MedicationDao`: Interface de acesso a dados
- `MedicationDatabase`: Banco de dados Room
- `MedicationRepository` / `MedicationRepositoryImpl`: Camada de repositório

**Camada de Apresentação:**
- `MedicationViewModel`: Gerencia a lógica de apresentação (MVVM)
- `MedicationListScreen`, `AddEditMedicationScreen`, `MedicationDetailScreen`: Telas do aplicativo

**Camada de Serviços:**
- `NotificationScheduler`: Agenda notificações
- `NotificationWorker`: Executa notificações via WorkManager
- `PdfGenerator`: Gera relatórios em PDF

**Injeção de Dependências:**
- `AppContainer` / `AppDataContainer`: Container de dependências

**Conceitos OOP Aplicados:**
- **Encapsulamento:** Dados e comportamentos encapsulados nas classes
- **Herança/Implementação:** Interfaces e implementações claramente definidas
- **Polimorfismo:** Uso de interfaces permite substituição de implementações
- **Abstração:** Separação de responsabilidades através de abstrações
- **Relacionamentos:** Associação, Agregação e Composição apropriadamente utilizados

#### 3.3.2. Diagrama de Atividades

O diagrama de atividades foi criado para o processo crítico de **Cadastrar Medicamento e Agendar Notificação**, mostrando:
- Fluxo desde o preenchimento do formulário
- Validação de dados
- Persistência no banco de dados
- Agendamento de notificações para múltiplos horários

#### 3.3.3. Diagrama de Sequência

O diagrama de sequência foi criado para o processo de **Marcar Medicamento como Tomado**, mostrando:
- Interação entre `MedicationDetailScreen`, `MedicationViewModel`, `MedicationRepository`, `MedicationDao` e `MedicationDatabase`
- Fluxo de dados reativo através de `Flow`
- Atualização automática da interface

#### 3.3.4. Diagrama de Estados

O diagrama de estados mostra o ciclo de vida de um **Medicamento**:
- **Estados:** Pendente, Tomado, Atrasado
- **Transições:** 
  - Cadastrar → Pendente
  - Marcar como Tomado → Tomado
  - Horário Passou → Atrasado (futuro)

---

## 4. Gerenciamento e Rastreabilidade

### 4.1. Ferramenta de Gerenciamento

Foi escolhida a ferramenta **Trello** para gerenciamento do projeto devido a:
- Facilidade de uso e colaboração
- Organização através de boards, listas e cards
- Possibilidade de rastreamento através de labels, checklists e links
- Acesso gratuito e multiplataforma

**Link do Board:** [INSERIR LINK DO TRELLO]

### 4.2. Estrutura do Projeto

O board Trello foi organizado com as seguintes listas:
- 📋 Backlog de Requisitos
- 📝 Entregas Parciais
- 🔄 Em Andamento
- ✅ Concluído

### 4.3. Rastreabilidade dos Requisitos

Foi criada uma **Matriz de Rastreabilidade** completa conectando:
- Cada requisito funcional aos seus casos de uso correspondentes
- Cada requisito aos diagramas UML relacionados
- Cada requisito à sua implementação no código

**Métricas de Rastreabilidade:**
- Total de Requisitos: 18 (10 funcionais + 8 não funcionais)
- Requisitos Rastreados: 18 (100%)
- Cobertura: 100% dos requisitos possuem artefatos relacionados

### 4.4. Planejamento de Entregas

O projeto foi dividido em 5 sprints:
1. **Sprint 1:** Elicitação de Requisitos ✅
2. **Sprint 2:** Modelagem de Casos de Uso ✅
3. **Sprint 3:** Diagramas UML ✅
4. **Sprint 4:** Gerenciamento e Rastreabilidade ✅
5. **Sprint 5:** Relatório Final ✅

---

## 5. Principais Desafios e Aprendizados

### 5.1. Desafios Enfrentados

1. **Elicitação de Requisitos:**
   - Desafio: Encontrar participantes adequados para entrevistas
   - Solução: Diversificação de técnicas (entrevista, questionário, observação)

2. **Modelagem de Casos de Uso:**
   - Desafio: Determinar o nível de detalhamento apropriado
   - Solução: Seguir padrão estabelecido na disciplina e incluir fluxos alternativos e de exceção

3. **Diagramas UML:**
   - Desafio: Representar corretamente relacionamentos complexos
   - Solução: Estudo de padrões UML e revisão de exemplos

4. **Rastreabilidade:**
   - Desafio: Manter consistência entre requisitos e artefatos
   - Solução: Uso de ferramenta de gerenciamento (Trello) e criação de matriz detalhada

### 5.2. Aprendizados

1. **Importância da Elicitação:**
   - A aplicação de múltiplas técnicas forneceu uma visão mais completa dos requisitos
   - A participação de profissionais da área trouxe insights valiosos

2. **Valor da Modelagem:**
   - Os diagramas UML facilitaram a compreensão do sistema
   - A modelagem ajudou a identificar inconsistências antes da implementação

3. **Rastreabilidade Essencial:**
   - A rastreabilidade permitiu validar que todos os requisitos foram atendidos
   - Facilita futuras manutenções e evoluções do sistema

4. **Organização e Planejamento:**
   - O uso de ferramenta de gerenciamento facilitou o acompanhamento do progresso
   - A organização em sprints permitiu entregas incrementais e validação contínua

---

## 6. Considerações Finais

O projeto MediTrack foi desenvolvido seguindo metodologias de Engenharia de Requisitos e Modelagem de Sistemas, resultando em uma documentação completa e rastreável. 

Os principais resultados alcançados foram:
- ✅ Elicitação completa de requisitos utilizando múltiplas técnicas
- ✅ Modelagem detalhada através de 8 casos de uso
- ✅ Criação de 5 diagramas UML (Casos de Uso, Classes, Atividades, Sequência, Estados)
- ✅ Rastreabilidade 100% dos requisitos
- ✅ Documentação completa e organizada

O sistema MediTrack demonstra a aplicação prática dos conhecimentos adquiridos na disciplina, desde a identificação das necessidades dos usuários até a modelagem completa da solução proposta.

---

## 7. Referências

### 7.1. Documentos do Projeto

- Entrega Parcial 1: Definição do Problema e Elicitação de Requisitos
- Entrega Parcial 2: Modelagem dos Casos de Uso
- Entrega Parcial 3: Modelagem Estrutural e Comportamental
- Entrega Parcial 4: Gerenciamento e Rastreabilidade dos Requisitos

### 7.2. Ferramentas Utilizadas

- **Modelagem UML:** PlantUML
- **Gerenciamento:** Trello
- **Documentação:** Markdown
- **Desenvolvimento:** Android Studio, Kotlin, Jetpack Compose

### 7.3. Bibliografia

- Sommerville, Ian. Engenharia de Software. 10ª edição.
- Pressman, Roger S. Engenharia de Software. 8ª edição.
- Larman, Craig. Utilizando UML e Padrões. 3ª edição.
- Documentação oficial Android: https://developer.android.com

---

**Documento elaborado por:** [Nomes dos integrantes da dupla]  
**Data de conclusão:** [Data]

---

## Apêndices

### Apêndice A: Estrutura de Pastas do Projeto

```
documentacao/
├── ENTREGA_PARCIAL_1/
│   └── 01_Definicao_Problema_Elicitacao_Requisitos.md
├── ENTREGA_PARCIAL_2/
│   ├── 02_Modelagem_Casos_Uso.md
│   └── diagrama_casos_uso.puml
├── ENTREGA_PARCIAL_3/
│   ├── 03_Modelagem_Estrutural_Comportamental.md
│   ├── diagrama_classes.puml
│   ├── diagrama_atividades.puml
│   ├── diagrama_sequencia.puml
│   └── diagrama_estados.puml
├── ENTREGA_PARCIAL_4/
│   └── 04_Gerenciamento_Rastreabilidade.md
└── RELATORIO_FINAL/
    └── Relatorio_Final_MediTrack.md
```

### Apêndice B: Link do Board Trello

[AQUI DEVE SER INSERIDO O LINK DO BOARD TRELLO DO PROJETO]
