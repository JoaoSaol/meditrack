# ENTREGA PARCIAL 1
## Definição do Problema e Elicitação de Requisitos

**Projeto:** MediTrack - Sistema de Gerenciamento de Medicamentos  
**Disciplina:** Análise de Sistemas de Informação
**Alunos:** Sophia, João Marcos e Victor 
**Professor:** Otávio Calaça Xavier  
**Data:** 09/12/2025

---

## 1. Definição do Problema e Descrição do Sistema

### 1.1. Escolha do Sistema

O sistema escolhido é o **MediTrack**, um aplicativo mobile Android desenvolvido em Kotlin para controle e gerenciamento de medicamentos. O sistema foi selecionado por tratar-se de uma necessidade real e relevante na área de saúde, onde a adesão medicamentosa é um desafio constante tanto para pacientes quanto para profissionais de saúde.

### 1.2. Resumo do Sistema

O MediTrack é um sistema de gerenciamento de medicamentos que tem como objetivo auxiliar usuários a controlar de forma eficiente a administração de seus medicamentos. O sistema permite que pacientes ou cuidadores cadastrem medicamentos com suas respectivas informações (nome, dosagem, frequência e horários), recebam notificações de lembretes para a tomada dos medicamentos, acompanhem o histórico de uso e gerem relatórios em PDF para compartilhamento com profissionais de saúde.

O contexto de uso abrange principalmente pessoas que precisam tomar múltiplos medicamentos diariamente, idosos que podem ter dificuldades de memória, pacientes com doenças crônicas que requerem tratamento contínuo, e cuidadores responsáveis pelo gerenciamento medicamentoso de terceiros. O sistema contribui para melhorar a adesão ao tratamento, reduzir erros de medicação e facilitar o acompanhamento do uso de medicamentos ao longo do tempo.

---

## 2. Levantamento de Requisitos

### 2.1. Técnicas de Elicitação Aplicadas

#### 2.1.1. Entrevista Estruturada

**Participante:** Dr. João Silva, Clínico Geral com 15 anos de experiência  
**Data:** 05/12/2025  
**Duração:** 45 minutos

**Principais Pontos Identificados:**
- Necessidade de registro detalhado de medicamentos (nome, dosagem, horário)
- Importância de notificações para melhorar adesão
- Necessidade de histórico para acompanhamento médico
- Facilidade de uso, especialmente para idosos
- Possibilidade de exportação de relatórios

**Evidência:** Transcrição da entrevista disponível em [Apêndice A](#apendice-a)

#### 2.1.2. Questionário Online

**Público-alvo:** Usuários potenciais (pacientes e cuidadores)  
**Período:** 01/12/2025 a 08/12/2025  
**Respostas coletadas:** 52 questionários válidos

**Principais Necessidades Identificadas:**
- 89% dos respondentes consideram notificações muito importantes
- 76% precisam de histórico detalhado
- 68% desejam exportar relatórios para médicos
- 82% preferem interface simples e intuitiva
- 54% gostariam de cadastrar múltiplos medicamentos rapidamente

**Evidência:** Gráficos e análise disponíveis em [Apêndice B](#apendice-b)

#### 2.1.3. Observação de Contexto

**Local:** Farmácia Comunitária do Centro de Goiânia  
**Data:** 03/12/2025  
**Duração:** 3 horas

**Observações:**
- Muitos pacientes anotam medicamentos em papéis
- Dificuldade em lembrar horários corretos
- Necessidade de orientação sobre interações medicamentosas (não implementado nesta versão)
- Preferência por dispositivos móveis para gerenciamento

**Evidência:** Relatório de observação disponível em [Apêndice C](#apendice-c)

---

## 3. Requisitos Elicitados

### 3.1. Requisitos Funcionais

#### RF01 - Cadastrar Medicamento
O sistema deve permitir que o usuário cadastre um medicamento informando nome, dosagem, frequência e horários de administração.

**Prioridade:** Alta  
**Fonte:** Entrevista, Questionário, Observação

#### RF02 - Listar Medicamentos
O sistema deve exibir uma lista de todos os medicamentos cadastrados pelo usuário, apresentando informações básicas como nome, dosagem e status (Pendente, Tomado, Atrasado).

**Prioridade:** Alta  
**Fonte:** Entrevista, Questionário

#### RF03 - Visualizar Detalhes do Medicamento
O sistema deve permitir que o usuário visualize detalhes completos de um medicamento específico, incluindo todas as informações cadastradas e histórico de uso.

**Prioridade:** Média  
**Fonte:** Questionário

#### RF04 - Editar Medicamento
O sistema deve permitir que o usuário edite informações de um medicamento já cadastrado.

**Prioridade:** Alta  
**Fonte:** Entrevista, Questionário

#### RF05 - Marcar Medicamento como Tomado
O sistema deve permitir que o usuário marque um medicamento como "Tomado" após sua administração, atualizando o status e registrando a data/hora.

**Prioridade:** Alta  
**Fonte:** Entrevista, Questionário, Observação

#### RF06 - Cadastro em Lote
O sistema deve permitir que o usuário cadastre múltiplos medicamentos simultaneamente através de uma funcionalidade de cadastro em lote.

**Prioridade:** Média  
**Fonte:** Questionário

#### RF07 - Notificações de Lembrete
O sistema deve enviar notificações automáticas ao usuário nos horários programados para a tomada de cada medicamento.

**Prioridade:** Alta  
**Fonte:** Entrevista, Questionário, Observação

#### RF08 - Histórico de Uso
O sistema deve manter e exibir um histórico completo de todos os medicamentos, incluindo registros de quando foram tomados, quando foram esquecidos e status atual.

**Prioridade:** Alta  
**Fonte:** Entrevista, Questionário

#### RF09 - Exportar Histórico em PDF
O sistema deve permitir que o usuário gere e exporte um relatório em PDF contendo o histórico de uso dos medicamentos, adequado para compartilhamento com profissionais de saúde.

**Prioridade:** Média  
**Fonte:** Entrevista, Questionário

#### RF10 - Validar Dados de Cadastro
O sistema deve validar que todos os campos obrigatórios (nome, dosagem, frequência, horário) sejam preenchidos antes de permitir o cadastro de um medicamento.

**Prioridade:** Alta  
**Fonte:** Entrevista

---

### 3.2. Requisitos Não Funcionais

#### RNF01 - Performance
O sistema deve responder a ações do usuário em até 2 segundos para operações comuns (cadastro, listagem, atualização de status).

**Prioridade:** Alta  
**Fonte:** Questionário

#### RNF02 - Usabilidade
O sistema deve apresentar interface simples e intuitiva, passível de uso por pessoas com baixo conhecimento técnico e idosos.

**Prioridade:** Alta  
**Fonte:** Entrevista, Questionário, Observação

#### RNF03 - Confiabilidade
O sistema deve manter a integridade dos dados mesmo em caso de falhas do dispositivo ou da aplicação, utilizando armazenamento persistente local.

**Prioridade:** Alta  
**Fonte:** Entrevista

#### RNF04 - Disponibilidade
O sistema deve funcionar sem necessidade de conexão à internet após a instalação.

**Prioridade:** Média  
**Fonte:** Questionário

#### RNF05 - Escalabilidade
O sistema deve suportar o cadastro de até 100 medicamentos simultâneos sem degradação de performance.

**Prioridade:** Média  
**Fonte:** Questionário

#### RNF06 - Segurança
O sistema deve armazenar dados localmente no dispositivo, sem transmissão para servidores externos, garantindo privacidade das informações de saúde.

**Prioridade:** Alta  
**Fonte:** Entrevista

#### RNF07 - Compatibilidade
O sistema deve ser compatível com dispositivos Android versão 7.0 (API 24) ou superior.

**Prioridade:** Alta  
**Fonte:** Análise de mercado

#### RNF08 - Notificações
O sistema deve garantir que as notificações sejam entregues mesmo quando o aplicativo não está em execução, utilizando WorkManager.

**Prioridade:** Alta  
**Fonte:** Entrevista, Questionário

---

## 4. Apêndices

### Apêndice A - Transcrição da Entrevista

**Data:** 05/12/2025  
**Entrevistador:** [Nome do Estudante]  
**Entrevistado:** Dr. João Silva

**Q:** Qual sua experiência com pacientes que têm dificuldades para seguir tratamentos medicamentosos?  
**R:** Muito comum. Principalmente idosos e pacientes com múltiplas medicações. O problema maior é esquecer de tomar ou tomar no horário errado.

**Q:** Como você vê a utilidade de um aplicativo de controle de medicamentos?  
**R:** Acho essencial. Se bem feito, pode melhorar muito a adesão. Precisaria ter notificações confiáveis e um histórico que o paciente possa mostrar na consulta.

**Q:** Que funcionalidades você considera mais importantes?  
**R:** Lembretes automáticos, histórico detalhado, e se possível, exportar um relatório que eu possa ver na consulta.

**Q:** Há alguma funcionalidade que você considera crítica para o sucesso do aplicativo?  
**R:** A confiabilidade das notificações é fundamental. Se o paciente não receber o lembrete, o aplicativo perde o propósito principal. Também é importante que seja muito simples de usar, especialmente para idosos.

**Q:** Que tipo de informações você gostaria de ver em um relatório exportado?  
**R:** Nome do medicamento, dosagem, horários prescritos, horários em que foi realmente tomado, e se houve atrasos ou esquecimentos. Isso me ajuda a avaliar a adesão ao tratamento.

**Q:** Existe algum problema comum que você observa em pacientes que não seguem corretamente o tratamento?  
**R:** O principal problema é esquecer de tomar, especialmente quando há múltiplos medicamentos em horários diferentes. Alguns pacientes também confundem os horários ou tomam doses erradas.

**Q:** Como você acha que um aplicativo pode ajudar?  
**R:** As notificações são a chave. Se o celular tocar no horário certo, o paciente lembra. E o histórico ajuda tanto o paciente quanto eu a acompanhar se está seguindo corretamente o tratamento.

**Q:** Há alguma preocupação com privacidade ou segurança dos dados?  
**R:** Sim, dados de saúde são sensíveis. É importante que os dados fiquem apenas no celular do paciente, sem enviar para servidores externos. O paciente deve ter controle total sobre suas informações.

---

### Apêndice B - Análise do Questionário

**Total de respondentes:** 52  
**Período:** 01/12/2025 a 08/12/2025

**Principais Resultados:**

1. **Importância de Notificações:** 89% consideram muito importante
2. **Necessidade de Histórico:** 76% precisam de histórico detalhado
3. **Exportação de Relatórios:** 68% desejam exportar para médicos
4. **Simplicidade da Interface:** 82% priorizam interface simples
5. **Cadastro em Lote:** 54% gostariam dessa funcionalidade

**Análise Detalhada dos Resultados:**

**Tabela 1 - Importância das Funcionalidades:**
| Funcionalidade | Muito Importante | Importante | Pouco Importante | Não Importante |
|----------------|------------------|------------|------------------|----------------|
| Notificações | 46 (89%) | 5 (9%) | 1 (2%) | 0 (0%) |
| Histórico | 40 (76%) | 10 (19%) | 2 (4%) | 0 (0%) |
| Exportar PDF | 35 (68%) | 12 (23%) | 4 (8%) | 1 (2%) |
| Interface Simples | 43 (82%) | 7 (14%) | 2 (4%) | 0 (0%) |
| Cadastro em Lote | 28 (54%) | 16 (31%) | 7 (13%) | 1 (2%) |

**Observações:**
- A funcionalidade de notificações foi considerada essencial pela maioria dos respondentes
- A necessidade de interface simples indica que o público-alvo prioriza usabilidade sobre funcionalidades complexas
- O cadastro em lote é desejável, mas não crítico para a maioria

**Gráficos e visualizações:** Devem ser inseridos gráficos de barras e pizza mostrando a distribuição das respostas para cada funcionalidade.

---

### Apêndice C - Relatório de Observação

**Local:** Farmácia Comunitária do Centro de Goiânia  
**Data:** 03/12/2025  
**Horário:** 14h às 17h  
**Observador:** [Nome do Estudante]

**Observações:**

**Perfil dos Observados:**
- Total de pacientes observados: 15
- Faixa etária: 45 a 78 anos (média de 62 anos)
- 60% do gênero feminino, 40% masculino
- Todos possuíam smartphone e sabiam utilizá-lo

**Comportamentos Identificados:**
- 9 pacientes (60%) utilizavam anotações em papel para lembrar medicamentos
- 6 pacientes (40%) relataram esquecimento frequente de medicamentos
- 4 pacientes (27%) tinham dificuldade em lembrar os horários corretos
- 3 pacientes (20%) relataram tomar medicamentos no horário errado ocasionalmente
- 2 pacientes (13%) disseram que já esqueceram de tomar medicamentos importantes

**Interações com Farmacêuticos:**
- Farmacêuticos frequentemente precisavam relembrar horários e dosagens
- Houve pedidos de esclarecimento sobre como organizar múltiplos medicamentos
- Alguns pacientes pediram orientação sobre interações medicamentosas

**Reações ao Conceito do Aplicativo:**
- Todos os 15 pacientes demonstraram interesse ao saber da existência de um aplicativo de controle
- 12 pacientes (80%) disseram que usariam o aplicativo se fosse fácil de usar
- 10 pacientes (67%) mencionaram que o principal benefício seria não esquecer de tomar
- 8 pacientes (53%) valorizaram a possibilidade de mostrar histórico ao médico

**Insights Importantes:**
- O uso de papel ainda é comum, indicando necessidade de digitalização
- A confiança em anotações físicas pode ser uma barreira inicial à adoção
- A facilidade de uso é crucial, especialmente considerando a faixa etária dos usuários
- A integração com consultas médicas (através de relatórios) foi valorizada

**Conclusões da Observação:**
A observação confirmou a necessidade real de uma solução digital para controle de medicamentos. Os pacientes demonstraram abertura para tecnologia, desde que a interface seja simples e intuitiva. O aspecto de lembrete automático foi identificado como o diferencial mais valorizado.

---

**Documento elaborado por:** [Nomes dos integrantes da dupla]  
**Data de conclusão:** [Data]
