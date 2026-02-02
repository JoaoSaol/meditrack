# ENTREGA PARCIAL 2
## Modelagem dos Casos de Uso

**Projeto:** MediTrack - Sistema de Gerenciamento de Medicamentos  
**Disciplina:** Análise de Sistemas de Informação
**Alunos:** Sophia Lannah, João Marcos e Victor Gabriel
**Professor:** Otávio Calaça Xavier  
**Data:** 09/12/2025

---

## 1. Introdução

Este documento apresenta a modelagem dos casos de uso do sistema MediTrack, descrevendo as interações entre os atores e o sistema, bem como os fluxos principais, alternativos e de exceção para cada funcionalidade identificada durante a etapa de elicitação de requisitos.

---

## 2. Casos de Uso

### CU01 - Cadastrar Medicamento

**Título:** Cadastrar Medicamento

**Descrição:** Este caso de uso permite que o usuário cadastre um novo medicamento no sistema, informando nome, dosagem, frequência e horários de administração.

**Atores:** Usuário

**Pré-condições:**
- O usuário deve estar na tela de lista de medicamentos.
- O aplicativo deve estar instalado e funcionando corretamente.

**Fluxo Principal:**
1. O usuário acessa a tela de lista de medicamentos.
2. O usuário clica no botão "Adicionar Medicamento".
3. O sistema exibe o formulário de cadastro de medicamento.
4. O usuário preenche o campo "Nome do Medicamento".
5. O usuário preenche o campo "Dosagem".
6. O usuário preenche o campo "Frequência".
7. O usuário preenche o campo "Horário" (ex: 8h, 14h, 20h).
8. O usuário clica no botão "Salvar".
9. O sistema valida se todos os campos obrigatórios foram preenchidos.
10. O sistema salva o medicamento no banco de dados.
11. O sistema agenda as notificações conforme o horário informado.
12. O sistema retorna à tela de lista de medicamentos.
13. O caso de uso é encerrado.

**Fluxos Alternativos:**

**FA1 - Validação Falha:**
- No passo 9, se algum campo obrigatório não foi preenchido:
  - O sistema exibe mensagem de erro solicitando o preenchimento de todos os campos.
  - O fluxo retorna ao passo 4.

**FA2 - Cancelar Cadastro:**
- A qualquer momento entre os passos 4 e 7, o usuário pode clicar no botão "Voltar":
  - O sistema descarta as informações preenchidas.
  - O sistema retorna à tela de lista de medicamentos.
  - O caso de uso é encerrado.

**Fluxos de Exceção:**

**FE1 - Erro ao Salvar:**
- No passo 10, se ocorrer erro ao salvar no banco de dados:
  - O sistema exibe mensagem de erro informando que não foi possível salvar.
  - O sistema mantém o formulário preenchido.
  - O fluxo retorna ao passo 4.

**FE2 - Erro ao Agendar Notificação:**
- No passo 11, se ocorrer erro ao agendar a notificação:
  - O sistema salva o medicamento normalmente.
  - O sistema registra o erro internamente.
  - O sistema exibe mensagem informando que o medicamento foi salvo, mas a notificação não foi configurada.
  - O fluxo continua no passo 12.

---

### CU02 - Listar Medicamentos

**Título:** Listar Medicamentos

**Descrição:** Este caso de uso permite que o usuário visualize uma lista de todos os medicamentos cadastrados, exibindo informações básicas e o status de cada um.

**Atores:** Usuário

**Pré-condições:**
- O aplicativo deve estar instalado e funcionando corretamente.
- O usuário deve ter pelo menos um medicamento cadastrado (opcional).

**Fluxo Principal:**
1. O usuário abre o aplicativo.
2. O sistema carrega os medicamentos do banco de dados.
3. O sistema exibe a tela de lista de medicamentos.
4. O sistema apresenta cada medicamento com: nome, dosagem e status (Pendente, Tomado, Atrasado).
5. O caso de uso é encerrado.

**Fluxos Alternativos:**

**FA1 - Lista Vazia:**
- No passo 3, se não houver medicamentos cadastrados:
  - O sistema exibe mensagem informando que nenhum medicamento foi cadastrado.
  - O sistema sugere ao usuário adicionar um medicamento.
  - O caso de uso é encerrado.

**FA2 - Filtrar Medicamentos:**
- No passo 4, o usuário pode filtrar medicamentos por status:
  - O sistema permite selecionar filtros (Todos, Pendentes, Tomados, Atrasados).
  - O sistema atualiza a lista conforme o filtro selecionado.
  - O fluxo continua no passo 4.

**Fluxos de Exceção:**

**FE1 - Erro ao Carregar Dados:**
- No passo 2, se ocorrer erro ao carregar do banco de dados:
  - O sistema exibe mensagem de erro informando que não foi possível carregar os medicamentos.
  - O sistema oferece opção de tentar novamente.
  - Se o usuário escolher tentar novamente, o fluxo retorna ao passo 2.
  - Caso contrário, o caso de uso é encerrado.

---

### CU03 - Visualizar Detalhes do Medicamento

**Título:** Visualizar Detalhes do Medicamento

**Descrição:** Este caso de uso permite que o usuário visualize informações detalhadas de um medicamento específico selecionado da lista.

**Atores:** Usuário

**Pré-condições:**
- O usuário deve estar na tela de lista de medicamentos.
- Deve existir pelo menos um medicamento cadastrado.

**Fluxo Principal:**
1. O usuário está na tela de lista de medicamentos.
2. O usuário clica em um medicamento da lista.
3. O sistema carrega os dados completos do medicamento selecionado.
4. O sistema exibe a tela de detalhes com todas as informações: nome, dosagem, frequência, horário e status.
5. O caso de uso é encerrado.

**Fluxos Alternativos:**

**FA1 - Editar Medicamento:**
- Após o passo 4, o usuário pode clicar em "Editar":
  - O sistema abre o formulário de edição preenchido com os dados atuais.
  - O fluxo segue para o caso de uso CU04 (Editar Medicamento).

**FA2 - Marcar como Tomado:**
- Após o passo 4, se o status não for "Tomado", o usuário pode clicar em "Marcar como Tomado":
  - O fluxo segue para o caso de uso CU05 (Marcar Medicamento como Tomado).

**Fluxos de Exceção:**

**FE1 - Medicamento Não Encontrado:**
- No passo 3, se o medicamento não for encontrado:
  - O sistema exibe mensagem de erro informando que o medicamento não foi encontrado.
  - O sistema retorna à tela de lista de medicamentos.
  - O caso de uso é encerrado.

---

### CU04 - Editar Medicamento

**Título:** Editar Medicamento

**Descrição:** Este caso de uso permite que o usuário edite informações de um medicamento já cadastrado.

**Atores:** Usuário

**Pré-condições:**
- O usuário deve estar visualizando os detalhes de um medicamento.
- O medicamento deve existir no sistema.

**Fluxo Principal:**
1. O usuário está na tela de detalhes do medicamento.
2. O usuário clica em "Editar".
3. O sistema carrega os dados do medicamento no formulário de edição.
4. O sistema exibe o formulário preenchido com os dados atuais.
5. O usuário modifica os campos desejados (nome, dosagem, frequência ou horário).
6. O usuário clica no botão "Salvar".
7. O sistema valida se todos os campos obrigatórios foram preenchidos.
8. O sistema atualiza o medicamento no banco de dados.
9. O sistema atualiza as notificações agendadas conforme os novos horários.
10. O sistema retorna à tela de detalhes do medicamento atualizado.
11. O caso de uso é encerrado.

**Fluxos Alternativos:**

**FA1 - Validação Falha:**
- No passo 7, se algum campo obrigatório não foi preenchido:
  - O sistema exibe mensagem de erro solicitando o preenchimento de todos os campos.
  - O fluxo retorna ao passo 5.

**FA2 - Cancelar Edição:**
- A qualquer momento entre os passos 5 e 6, o usuário pode clicar em "Voltar":
  - O sistema descarta as alterações não salvas.
  - O sistema retorna à tela de detalhes do medicamento.
  - O caso de uso é encerrado.

**Fluxos de Exceção:**

**FE1 - Erro ao Atualizar:**
- No passo 8, se ocorrer erro ao atualizar no banco de dados:
  - O sistema exibe mensagem de erro informando que não foi possível atualizar.
  - O sistema mantém o formulário com as alterações.
  - O fluxo retorna ao passo 5.

---

### CU05 - Marcar Medicamento como Tomado

**Título:** Marcar Medicamento como Tomado

**Descrição:** Este caso de uso permite que o usuário registre que tomou um medicamento, atualizando seu status para "Tomado" e registrando a data/hora da ação.

**Atores:** Usuário

**Pré-condições:**
- O usuário deve estar visualizando os detalhes de um medicamento.
- O medicamento deve ter status diferente de "Tomado".

**Fluxo Principal:**
1. O usuário está na tela de detalhes do medicamento.
2. O usuário visualiza que o status do medicamento é "Pendente" ou "Atrasado".
3. O usuário clica no botão "Marcar como Tomado".
4. O sistema atualiza o status do medicamento para "Tomado".
5. O sistema registra a data e hora em que o medicamento foi marcado como tomado.
6. O sistema salva a atualização no banco de dados.
7. O sistema atualiza a exibição mostrando o novo status.
8. O botão "Marcar como Tomado" desaparece da interface.
9. O caso de uso é encerrado.

**Fluxos Alternativos:**

**FA1 - Marcar da Lista:**
- O usuário pode marcar como tomado diretamente da lista de medicamentos:
  - O usuário está na tela de lista.
  - O usuário clica em uma ação rápida "Marcar como Tomado" no item da lista.
  - O fluxo segue do passo 4 em diante.

**Fluxos de Exceção:**

**FE1 - Erro ao Salvar:**
- No passo 6, se ocorrer erro ao salvar no banco de dados:
  - O sistema exibe mensagem de erro informando que não foi possível atualizar o status.
  - O status permanece inalterado.
  - O fluxo retorna ao passo 2.

---

### CU06 - Cadastrar Medicamentos em Lote

**Título:** Cadastrar Medicamentos em Lote

**Descrição:** Este caso de uso permite que o usuário cadastre múltiplos medicamentos simultaneamente através de uma funcionalidade de cadastro em lote.

**Atores:** Usuário

**Pré-condições:**
- O usuário deve estar na tela de lista de medicamentos.
- O aplicativo deve estar instalado e funcionando corretamente.

**Fluxo Principal:**
1. O usuário acessa a tela de lista de medicamentos.
2. O usuário clica no botão "Adicionar Medicamento".
3. O sistema exibe menu com opções: "Adicionar 1 Medicamento" e "Adicionar Múltiplos".
4. O usuário seleciona "Adicionar Múltiplos".
5. O sistema exibe o formulário de cadastro em lote com múltiplos campos.
6. O usuário preenche as informações do primeiro medicamento (nome, dosagem, frequência, horário).
7. O usuário adiciona mais medicamentos através do botão "Adicionar Outro".
8. O sistema adiciona novos campos para preenchimento.
9. O usuário repete os passos 6 e 7 para cada medicamento adicional.
10. O usuário clica no botão "Salvar Todos".
11. O sistema valida todos os medicamentos preenchidos.
12. O sistema salva todos os medicamentos válidos no banco de dados.
13. O sistema agenda as notificações para cada medicamento salvo.
14. O sistema retorna à tela de lista de medicamentos.
15. O caso de uso é encerrado.

**Fluxos Alternativos:**

**FA1 - Validação Parcial:**
- No passo 11, se alguns medicamentos estão válidos e outros não:
  - O sistema salva apenas os medicamentos válidos.
  - O sistema exibe mensagem informando quantos foram salvos e quantos apresentaram erros.
  - O sistema mantém os campos inválidos para correção.
  - O usuário pode corrigir e tentar salvar novamente ou cancelar.

**FA2 - Remover Medicamento da Lista:**
- Entre os passos 6 e 10, o usuário pode remover um medicamento da lista:
  - O usuário clica no botão "Remover" ao lado do medicamento.
  - O sistema remove os campos daquele medicamento da tela.
  - O fluxo retorna ao passo 6.

**Fluxos de Exceção:**

**FE1 - Nenhum Medicamento Válido:**
- No passo 11, se nenhum medicamento estiver válido:
  - O sistema exibe mensagem informando que nenhum medicamento pôde ser salvo.
  - O sistema solicita a correção dos dados.
  - O fluxo retorna ao passo 6.

---

### CU07 - Receber Notificação de Lembrete

**Título:** Receber Notificação de Lembrete

**Descrição:** Este caso de uso descreve o comportamento do sistema ao enviar notificações automáticas para lembrar o usuário de tomar seus medicamentos nos horários programados.

**Atores:** Sistema (WorkManager), Usuário

**Pré-condições:**
- Deve existir pelo menos um medicamento cadastrado com horário definido.
- O sistema de notificações do dispositivo deve estar habilitado.
- O aplicativo deve ter permissão para exibir notificações.

**Fluxo Principal:**
1. O sistema verifica os horários programados dos medicamentos.
2. O sistema identifica que chegou o horário de um medicamento.
3. O sistema envia uma notificação para o dispositivo do usuário.
4. A notificação exibe: nome do medicamento, dosagem e horário.
5. O usuário visualiza a notificação.
6. O caso de uso é encerrado.

**Fluxos Alternativos:**

**FA1 - Usuário Clica na Notificação:**
- No passo 5, se o usuário clicar na notificação:
  - O sistema abre o aplicativo.
  - O sistema exibe a tela de detalhes do medicamento notificado.
  - O fluxo segue para o caso de uso CU03 (Visualizar Detalhes do Medicamento).

**FA2 - Múltiplos Medicamentos no Mesmo Horário:**
- No passo 2, se houver múltiplos medicamentos no mesmo horário:
  - O sistema agrupa as notificações ou envia múltiplas notificações.
  - Cada notificação contém informações de um medicamento.
  - O fluxo continua no passo 3.

**Fluxos de Exceção:**

**FE1 - Notificação Não Entregue:**
- No passo 3, se a notificação não puder ser entregue:
  - O sistema registra o erro internamente.
  - O sistema tenta reagendar a notificação.
  - Se persistir o erro, o sistema marca o medicamento como não notificado no histórico.
  - O caso de uso é encerrado.

---

### CU08 - Exportar Histórico em PDF

**Título:** Exportar Histórico em PDF

**Descrição:** Este caso de uso permite que o usuário gere e exporte um relatório em PDF contendo o histórico de uso dos medicamentos.

**Atores:** Usuário

**Pré-condições:**
- O usuário deve estar na tela de lista de medicamentos.
- Deve existir pelo menos um medicamento cadastrado.
- O dispositivo deve ter permissão de escrita em armazenamento externo.

**Fluxo Principal:**
1. O usuário está na tela de lista de medicamentos.
2. O usuário clica no ícone de "Exportar Histórico" (ícone de compartilhar).
3. O sistema verifica se possui permissão de escrita.
4. O sistema carrega todos os medicamentos e seus históricos.
5. O sistema gera o arquivo PDF com o histórico.
6. O sistema salva o arquivo PDF no armazenamento do dispositivo.
7. O sistema exibe mensagem informando o local onde o arquivo foi salvo.
8. O caso de uso é encerrado.

**Fluxos Alternativos:**

**FA1 - Solicitar Permissão:**
- No passo 3, se o sistema não possuir permissão:
  - O sistema solicita permissão de escrita ao usuário.
  - Se o usuário conceder permissão, o fluxo continua no passo 4.
  - Se o usuário negar permissão, o fluxo segue para FE1.

**FA2 - Compartilhar PDF:**
- Após o passo 7, o sistema pode oferecer opção de compartilhar:
  - O usuário pode escolher compartilhar via outros aplicativos.
  - O sistema utiliza o sistema de compartilhamento do Android.
  - O caso de uso é encerrado.

**Fluxos de Exceção:**

**FE1 - Permissão Negada:**
- No passo FA1, se o usuário negar a permissão:
  - O sistema exibe mensagem informando que não é possível gerar o PDF sem permissão.
  - O caso de uso é encerrado.

**FE2 - Erro ao Gerar PDF:**
- No passo 5, se ocorrer erro ao gerar o PDF:
  - O sistema exibe mensagem de erro informando que não foi possível gerar o arquivo.
  - O caso de uso é encerrado.

**FE3 - Erro ao Salvar:**
- No passo 6, se ocorrer erro ao salvar o arquivo:
  - O sistema exibe mensagem de erro informando que não foi possível salvar o arquivo.
  - O caso de uso é encerrado.

---

## 3. Diagrama de Casos de Uso

O diagrama UML de casos de uso está disponível no arquivo `diagrama_casos_uso.puml` (formato PlantUML) e pode ser visualizado através de ferramentas como:
- PlantUML Online: http://www.plantuml.com/plantuml/uml/
- Plugin do VS Code ou IntelliJ IDEA
- Aplicativo PlantUML standalone

O diagrama também está disponível em formato de imagem PNG: `diagrama_casos_uso.png`

### Resumo dos Casos de Uso e Relacionamentos:

- **Ator Principal:** Usuário
- **Casos de Uso:** 
  - CU01: Cadastrar Medicamento
  - CU02: Listar Medicamentos
  - CU03: Visualizar Detalhes do Medicamento
  - CU04: Editar Medicamento
  - CU05: Marcar Medicamento como Tomado
  - CU06: Cadastrar Medicamentos em Lote
  - CU07: Receber Notificação de Lembrete
  - CU08: Exportar Histórico em PDF

- **Relacionamentos:**
  - CU03 inclui CU04 (para editar)
  - CU03 inclui CU05 (para marcar como tomado)
  - CU04 estende CU01 (comportamento similar de validação e salvamento)
  - CU07 é iniciado pelo Sistema (WorkManager)

---

**Documento elaborado por:** [Nomes dos integrantes da dupla]  
**Data de conclusão:** [Data]
