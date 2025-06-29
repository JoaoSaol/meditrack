# Script PowerShell para Testes Automatizados - MediTrack
# Uso: .\rodar-testes.ps1 [opcao]
# Opções: unit, ui, all, clean

param(
    [string]$Tipo = "unit"
)

Write-Host "🧪 MediTrack - Executando Testes..." -ForegroundColor Cyan

switch ($Tipo.ToLower()) {
    "unit" {
        Write-Host "▶️ Executando testes unitários..." -ForegroundColor Green
        .\gradlew testDebugUnitTest
    }
    "ui" {
        Write-Host "📱 Verificando dispositivos conectados..." -ForegroundColor Yellow
        adb devices
        Write-Host "▶️ Executando testes de UI..." -ForegroundColor Green
        .\gradlew connectedAndroidTest
    }
    "all" {
        Write-Host "▶️ Executando todos os testes..." -ForegroundColor Green
        Write-Host "1️⃣ Testes unitários..." -ForegroundColor Yellow
        .\gradlew testDebugUnitTest
        Write-Host "2️⃣ Verificando dispositivos..." -ForegroundColor Yellow
        adb devices
        Write-Host "3️⃣ Testes de instrumentação..." -ForegroundColor Yellow
        .\gradlew connectedAndroidTest
    }
    "clean" {
        Write-Host "🧹 Limpando build..." -ForegroundColor Yellow
        .\gradlew clean
        Write-Host "▶️ Executando testes unitários após limpeza..." -ForegroundColor Green
        .\gradlew testDebugUnitTest
    }
    default {
        Write-Host "❌ Opção inválida. Use: unit, ui, all, clean" -ForegroundColor Red
        Write-Host "Exemplos:" -ForegroundColor White
        Write-Host "  .\rodar-testes.ps1 unit   # Testes unitários" -ForegroundColor Gray
        Write-Host "  .\rodar-testes.ps1 ui     # Testes de UI" -ForegroundColor Gray
        Write-Host "  .\rodar-testes.ps1 all    # Todos os testes" -ForegroundColor Gray
        Write-Host "  .\rodar-testes.ps1 clean  # Limpar e testar" -ForegroundColor Gray
        exit 1
    }
}

# Mostrar localização dos relatórios
Write-Host ""
Write-Host "📊 Relatórios disponíveis em:" -ForegroundColor Magenta
Write-Host "   HTML: app\build\reports\tests\testDebugUnitTest\index.html" -ForegroundColor Gray
Write-Host "   XML:  app\build\test-results\testDebugUnitTest\" -ForegroundColor Gray

Write-Host ""
Write-Host "✅ Script finalizado!" -ForegroundColor Green
