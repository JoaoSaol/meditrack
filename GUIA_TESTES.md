# 🧪 Guia de Testes Automatizados - MediTrack

## 📋 Comandos Principais

### 1. **Executar Todos os Testes Unitários**
```bash
./gradlew testDebugUnitTest
```
- Executa apenas os testes da pasta `src/test/`
- Mais rápido, não precisa de dispositivo/emulador
- Testa lógica de negócio e validações

### 2. **Executar Todos os Testes**
```bash
./gradlew test
```
- Executa testes unitários de todas as variantes (debug/release)
- Comando mais abrangente para testes locais

### 3. **Executar Testes de Instrumentação**
```bash
./gradlew connectedAndroidTest
```
- ⚠️ **Requer dispositivo Android conectado ou emulador rodando**
- Testa interface do usuário e integração com Android
- Executa testes da pasta `src/androidTest/`

### 4. **Verificar Dispositivos Conectados**
```bash
adb devices
```
- Lista dispositivos/emuladores disponíveis
- Necessário antes dos testes de instrumentação

### 5. **Executar Teste Específico**
```bash
./gradlew testDebugUnitTest --tests "*MedicationTest*"
./gradlew testDebugUnitTest --tests "*NomeDoTeste*"
```

### 6. **Testes com Mais Informações**
```bash
./gradlew test --info
./gradlew testDebugUnitTest --info
```
- Mostra logs detalhados da execução
- Útil para debugging

### 7. **Continuar Mesmo com Falhas**
```bash
./gradlew test --continue
```
- Executa todos os testes mesmo se alguns falharem

## 📁 Estrutura de Testes no Projeto

### 🔹 **Testes Unitários** (`app/src/test/`)
```
📂 src/test/java/com/example/meditrack/
└── 📂 data/model/
    └── 📄 MedicationTest.kt
```

**O que testa:**
- ✅ Validação de medicamentos válidos
- ✅ Validação de medicamentos inválidos (campos vazios)
- ✅ Método `isValid()` da classe Medication

### 🔹 **Testes de Instrumentação** (`app/src/androidTest/`)
```
📂 src/androidTest/java/com/example/meditrack/
├── 📂 data/dao/
│   └── 📄 MedicationDaoTest.kt
└── 📂 ui/
    └── 📄 MedicationUiTest.kt
```

**O que testa:**
- ✅ Operações de banco de dados (DAO)
- ✅ Interface do usuário com Compose
- ✅ Integração entre componentes

## 📊 Relatórios de Teste

### 🌐 **Relatórios HTML** (Abrir no navegador)
```
📂 app/build/reports/tests/
├── 📄 testDebugUnitTest/index.html
└── 📄 connectedAndroidTest/index.html
```

### 📋 **Resultados XML**
```
📂 app/build/test-results/
├── 📂 testDebugUnitTest/
└── 📂 connectedAndroidTest/
```

## 🚀 Fluxo Recomendado

### **1. Desenvolvimento Local**
```bash
# 1. Executar testes unitários (rápido)
./gradlew testDebugUnitTest

# 2. Se passou, executar build
./gradlew assembleDebug
```

### **2. Antes de Commit/Deploy**
```bash
# 1. Verificar dispositivo conectado
adb devices

# 2. Executar todos os testes
./gradlew test connectedAndroidTest

# 3. Se passou, fazer commit
git add .
git commit -m "feat: nova funcionalidade com testes"
```

### **3. Debugging de Testes**
```bash
# 1. Executar com informações detalhadas
./gradlew testDebugUnitTest --info

# 2. Executar teste específico que falhou
./gradlew testDebugUnitTest --tests "*NomeDoTesteFalhando*"

# 3. Ver logs do emulador (para testes de instrumentação)
adb logcat
```

## 🔧 Comandos de Manutenção

### **Limpar Build e Executar Testes**
```bash
./gradlew clean
./gradlew test
```

### **Executar Testes em Paralelo**
```bash
./gradlew test --parallel
```

### **Gerar Relatório de Cobertura** (se configurado)
```bash
./gradlew jacocoTestReport
```

## 📱 Configuração do Emulador

### **Iniciar Emulador via Command Line**
```bash
# Listar AVDs disponíveis
emulator -list-avds

# Iniciar emulador específico
emulator -avd NOME_DO_AVD

# Iniciar em modo headless (sem interface gráfica)
emulator -avd NOME_DO_AVD -no-window
```

## ⚠️ Troubleshooting

### **Problema: "No devices connected"**
```bash
# Verificar dispositivos
adb devices

# Reiniciar ADB se necessário
adb kill-server
adb start-server
```

### **Problema: "Build failed - dependency conflict"**
```bash
# Limpar build
./gradlew clean

# Invalidar caches (Android Studio)
# File > Invalidate Caches and Restart
```

### **Problema: "Tests failed"**
```bash
# Ver relatório detalhado
# Abrir: app/build/reports/tests/testDebugUnitTest/index.html

# Executar teste específico com debug
./gradlew testDebugUnitTest --tests "*NomeDoTeste*" --info
```

## 📈 Status Atual dos Testes

### ✅ **Funcionando**
- ✅ Testes unitários (MedicationTest.kt)
- ✅ Build do projeto
- ✅ Configuração de KSP/Room

### ⚠️ **Precisam Correção**
- ⚠️ Alguns testes de instrumentação (UI)
- ⚠️ Configuração de mocks para banco de dados

## 🎯 Boas Práticas

1. **Execute testes unitários frequentemente** durante desenvolvimento
2. **Execute testes de instrumentação antes de commits importantes**
3. **Mantenha emulador/dispositivo sempre conectado** para testes UI
4. **Use `--continue`** para ver todas as falhas de uma vez
5. **Verifique relatórios HTML** para análise detalhada
6. **Commit apenas código com testes passando**

## 📞 Comandos de Emergência

```bash
# Parar todos os processos Gradle
./gradlew --stop

# Limpar tudo e reconstruir
./gradlew clean build

# Forçar download de dependências
./gradlew build --refresh-dependencies
```

---

📝 **Nota**: Este guia foi criado para o projeto MediTrack em 29/06/2025. Mantenha atualizado conforme evolução do projeto.
