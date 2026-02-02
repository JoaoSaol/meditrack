# ENTREGA PARCIAL 3
## Modelagem Estrutural e Comportamental

**Projeto:** MediTrack - Sistema de Gerenciamento de Medicamentos  
**Disciplina:** Análise de Sistemas de Informação  
**Professor:** Otávio Calaça Xavier  
**Data:** 09/12/2025

---

## 1. Introdução

Este documento apresenta a modelagem estrutural e comportamental do sistema MediTrack através de diagramas UML. A modelagem estrutural é representada pelo Diagrama de Classes, enquanto a modelagem comportamental é representada por Diagramas de Atividades, Sequência e Estados.

---

## 2. Diagrama de Classes

O Diagrama de Classes apresenta a estrutura estática do sistema, mostrando as classes principais, seus atributos, métodos e relacionamentos.

### 2.1. Descrição das Classes

#### Medication (Entidade)
Classe que representa um medicamento no sistema. Possui informações sobre nome, dosagem, frequência, horários e status.

**Atributos:**
- `id: Int` - Identificador único do medicamento
- `name: String` - Nome do medicamento
- `dosage: String` - Dosagem do medicamento
- `frequency: String` - Frequência de administração
- `schedule: String` - Horários de administração (ex: "8h, 14h, 20h")
- `status: String` - Status atual (Pendente, Tomado, Atrasado)

**Métodos:**
- `isValid(): Boolean` - Valida se todos os campos obrigatórios foram preenchidos

#### MedicationDao (Interface)
Interface de acesso a dados (DAO) que define operações de persistência para medicamentos.

**Métodos:**
- `insert(medication: Medication): suspend` - Insere um novo medicamento
- `update(medication: Medication): suspend` - Atualiza um medicamento existente
- `getAll(): Flow<List<Medication>>` - Obtém todos os medicamentos
- `getById(id: Int): suspend Medication?` - Obtém um medicamento por ID

#### MedicationRepository (Interface)
Interface do repositório que abstrai o acesso aos dados.

**Métodos:**
- `insertMedication(medication: Medication): suspend`
- `updateMedication(medication: Medication): suspend`
- `getAllMedications(): Flow<List<Medication>>`
- `getMedicationById(id: Int): suspend Medication?`

#### MedicationRepositoryImpl (Implementação)
Implementação do repositório que utiliza o DAO para realizar operações.

**Relacionamentos:**
- Utiliza `MedicationDao` para acesso aos dados

#### MedicationDatabase
Classe abstrata que representa o banco de dados Room.

**Métodos:**
- `medicationDao(): MedicationDao` - Retorna a instância do DAO

**Relacionamentos:**
- Contém `Medication` como entidade

#### MedicationViewModel
Classe que gerencia a lógica de apresentação seguindo o padrão MVVM.

**Atributos:**
- `allMedications: Flow<List<Medication>>` - Fluxo reativo de todos os medicamentos

**Métodos:**
- `insertMedication(medication: Medication)`
- `updateMedication(medication: Medication)`
- `markMedicationAsTaken(medication: Medication)`
- `getMedicationById(id: Int): Flow<Medication?>`

**Relacionamentos:**
- Utiliza `MedicationRepository` para operações de dados

#### NotificationScheduler
Classe responsável por agendar notificações de lembretes.

**Métodos:**
- `scheduleNotification(medication: Medication)` - Agenda notificações para um medicamento

#### NotificationWorker
Classe que executa o trabalho de envio de notificações usando WorkManager.

**Métodos:**
- `doWork(): Result` - Método principal executado pelo WorkManager
- `showNotification(medicationName: String, medicationDosage: String): private` - Exibe a notificação

#### PdfGenerator
Classe responsável pela geração de relatórios em PDF.

**Métodos:**
- `generateMedicationHistoryPdf(medications: List<Medication>): File?` - Gera PDF com histórico

#### AppContainer (Interface)
Interface que define o container de dependências.

**Atributos:**
- `medicationDatabase: MedicationDatabase`
- `medicationRepository: MedicationRepository`

#### AppDataContainer (Implementação)
Implementação do container de dependências usando injeção manual.

### 2.2. Diagrama de Classes UML

O diagrama está disponível no arquivo `diagrama_classes.puml` (PlantUML) e mostra:

- **Herança:** `MedicationRepositoryImpl` implementa `MedicationRepository`
- **Associação:** `MedicationViewModel` utiliza `MedicationRepository`
- **Agregação:** `MedicationDatabase` contém `MedicationDao`
- **Dependência:** `NotificationScheduler` depende de `Medication`
- **Composição:** `MedicationDatabase` compõe `Medication` (persistência)

---

## 3. Diagrama de Atividades

O Diagrama de Atividades representa o fluxo de atividades do processo crítico de **Cadastrar Medicamento e Agendar Notificação**.

### 3.1. Descrição do Processo

Este processo envolve desde o preenchimento do formulário pelo usuário até o agendamento das notificações pelo sistema, sendo um processo crítico para o funcionamento do aplicativo.

### 3.2. Fluxo de Atividades

1. Usuário acessa formulário de cadastro
2. Sistema exibe campos do formulário
3. Usuário preenche informações (nome, dosagem, frequência, horário)
4. Sistema valida campos obrigatórios
5. Se inválido: exibe mensagem de erro e retorna ao passo 3
6. Se válido: cria objeto Medication
7. Sistema salva medicamento no banco de dados
8. Sistema agenda notificações para os horários informados
9. Sistema parse do horário (ex: "8h, 14h, 20h")
10. Para cada horário:
    - Sistema calcula delay até o horário
    - Sistema cria PeriodicWorkRequest
    - Sistema agenda notificação com WorkManager
11. Sistema retorna à tela de lista
12. Fim do processo

### 3.3. Diagrama de Atividades UML

O diagrama está disponível no arquivo `diagrama_atividades.puml` (PlantUML).

---

## 4. Diagrama de Sequência

O Diagrama de Sequência representa a interação temporal entre objetos durante o processo de **Marcar Medicamento como Tomado**.

### 4.1. Descrição do Processo

Este processo mostra a sequência de chamadas entre os componentes do sistema quando o usuário marca um medicamento como tomado.

### 4.2. Participantes

- **Usuário:** Ator que interage com o sistema
- **MedicationDetailScreen:** Tela de detalhes do medicamento
- **MedicationViewModel:** ViewModel que gerencia a lógica
- **MedicationRepository:** Camada de repositório
- **MedicationDao:** Camada de acesso a dados
- **MedicationDatabase:** Banco de dados Room

### 4.3. Fluxo de Sequência

1. Usuário clica em "Marcar como Tomado"
2. MedicationDetailScreen chama `markMedicationAsTaken()` no ViewModel
3. MedicationViewModel cria cópia do medicamento com status "Tomado"
4. MedicationViewModel chama `updateMedication()` no Repository
5. MedicationRepository chama `update()` no DAO
6. MedicationDao executa UPDATE no Database
7. MedicationDatabase persiste a atualização
8. O fluxo reativo (`Flow`) notifica os observadores
9. MedicationViewModel atualiza o estado
10. MedicationDetailScreen atualiza a interface
11. Botão "Marcar como Tomado" desaparece da tela

### 4.4. Diagrama de Sequência UML

O diagrama está disponível no arquivo `diagrama_sequencia.puml` (PlantUML).

---

## 5. Diagrama de Estados

O Diagrama de Estados representa os possíveis estados de um **Medicamento** e as transições entre eles.

### 5.1. Estados do Medicamento

1. **Pendente:** Estado inicial quando o medicamento é cadastrado
2. **Tomado:** Estado quando o usuário marca o medicamento como tomado
3. **Atrasado:** Estado quando o horário passou e o medicamento não foi marcado como tomado (futuro)

### 5.2. Transições

- **Cadastrar:** [Estado inicial] → Pendente
- **Marcar como Tomado:** Pendente → Tomado
- **Marcar como Tomado:** Atrasado → Tomado
- **Horário Passou (sistema):** Pendente → Atrasado (lógica futura)

### 5.3. Diagrama de Estados UML

O diagrama está disponível no arquivo `diagrama_estados.puml` (PlantUML).

---

## 6. Aplicação de Conceitos OOP

### 6.1. Encapsulamento

- Todas as classes encapsulam seus dados e comportamentos
- Atributos privados são acessados através de métodos públicos
- Exemplo: `Medication.isValid()` encapsula a lógica de validação

### 6.2. Herança/Implementação

- `MedicationRepositoryImpl` implementa a interface `MedicationRepository`
- `AppDataContainer` implementa a interface `AppContainer`
- `NotificationWorker` herda de `Worker` (Android WorkManager)

### 6.3. Polimorfismo

- O uso de interfaces permite polimorfismo
- `MedicationRepository` pode ser substituído por diferentes implementações
- Diferentes tipos de `Worker` podem ser usados para diferentes tarefas

### 6.4. Abstração

- Interfaces (`MedicationRepository`, `MedicationDao`) abstraem implementações
- A camada de ViewModel abstrai a lógica de negócio da UI

### 6.5. Relacionamentos

- **Associação:** `MedicationViewModel` → `MedicationRepository`
- **Agregação:** `MedicationDatabase` → `MedicationDao`
- **Composição:** `MedicationDatabase` compõe `Medication` (persistência)
- **Dependência:** `NotificationScheduler` depende de `Medication`

---

**Documento elaborado por:** [Nomes dos integrantes da dupla]  
**Data de conclusão:** [Data]
