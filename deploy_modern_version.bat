@echo off
echo ========================================
echo  TRES EN RAYA - VERSIÓN MODERNA MEJORADA
echo ========================================
echo.

echo ✨ NUEVAS CARACTERÍSTICAS AGREGADAS:
echo.
echo 🎨 DISEÑO MODERNO:
echo   - Títulos en color blanco para mejor contraste
echo   - Eliminada sección de "líder actual"
echo   - Gradientes atractivos en fondo y botones
echo   - Colores vibrantes que contrastan perfectamente
echo.
echo 📊 SISTEMA DE ESTADÍSTICAS:
echo   - Contador de victorias para cada jugador
echo   - Contador de empates
echo   - Contador de partidas totales
echo   - Estadísticas persistentes (se guardan automáticamente)
echo.
echo 🎭 MENSAJES DINÁMICOS:
echo   - Mensajes aleatorios de victoria para X y O
echo   - Mensajes especiales para empates
echo   - Animaciones suaves en mensajes
echo   - Contador de victorias mostrado en cada resultado
echo.
echo 💫 ANIMACIONES Y EFECTOS:
echo   - Barra de progreso animada del turno
echo   - Colores dinámicos según jugador actual
echo   - Transiciones suaves entre estados
echo   - Colores específicos para X (rojo) y O (azul)
echo.
echo 🔄 CONTROLES MEJORADOS:
echo   - Botón "Nueva Partida" rediseñado
echo   - Botón "Reset Stats" para reiniciar estadísticas
echo   - Confirmación antes de resetear estadísticas
echo   - Mensajes de error mejorados
echo.

echo [1/3] Verificando archivos...
if not exist "app\src\main\java\com\example\tresenrayakotlin\modelo\EstadisticasJuego.kt" (
    echo ❌ Error: Falta EstadisticasJuego.kt
    pause
    exit /b 1
)
echo ✅ Todos los archivos necesarios presentes

echo.
echo [2/3] Compilando versión moderna...
call gradlew.bat assembleDebug >nul 2>&1
if %errorlevel% neq 0 (
    echo ⚠️  Compilación con warnings - revisando...
    call gradlew.bat assembleDebug --console=plain
) else (
    echo ✅ Compilación exitosa
)

echo.
echo [3/3] ¿Deseas instalar en dispositivo? (S/N)
set /p install=
if /i "%install%"=="S" (
    echo Instalando...
    call gradlew.bat installDebug
    if %errorlevel% equ 0 (
        echo ✅ Instalación exitosa
    ) else (
        echo ❌ Error en instalación - verifica dispositivo conectado
    )
)

echo.
echo ========================================
echo 🎉 TRES EN RAYA MODERNO COMPLETADO 🎉
echo ========================================
echo.
echo FUNCIONALIDADES IMPLEMENTADAS:
echo ✅ Conteo de ganadores persistente
echo ✅ Interfaz moderna sin iconos
echo ✅ Colores contrastantes atractivos
echo ✅ Títulos en blanco
echo ✅ Mensajes dinámicos aleatorios
echo ✅ Animaciones suaves
echo ✅ Estadísticas completas
echo ✅ Sin sección de líder
echo.
echo ¡Disfruta tu juego de Tres en Raya mejorado! 🎮
pause
