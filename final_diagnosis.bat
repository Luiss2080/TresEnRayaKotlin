@echo off
echo ========================================
echo      DIAGNÓSTICO Y SOLUCIÓN FINAL
echo         ERROR "CONTENIDO EN PRÓLOGO"
echo ========================================
echo.

echo 🔍 PASOS REALIZADOS:
echo.
echo ✅ 1. Archivos XML recreados desde cero
echo ✅ 2. Eliminados caracteres BOM/invisibles
echo ✅ 3. Layout simplificado sin drawables complejos
echo ✅ 4. Archivos duplicados eliminados
echo.

echo [Verificando estructura final...]

if not exist "app\src\main\res\values\colors.xml" (
    echo ❌ ERROR: colors.xml no existe
    pause
    exit /b 1
)

if not exist "app\src\main\res\values\themes.xml" (
    echo ❌ ERROR: themes.xml no existe
    pause
    exit /b 1
)

if not exist "app\src\main\res\values-night\themes.xml" (
    echo ❌ ERROR: themes nocturno no existe
    pause
    exit /b 1
)

echo ✅ Estructura correcta verificada

echo.
echo [Compilación de prueba...]
call gradlew.bat clean >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Error en clean
) else (
    echo ✅ Clean exitoso
)

echo.
echo Intentando compilación...
call gradlew.bat assembleDebug >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Aún hay errores de compilación
    echo.
    echo 🔧 EJECUTANDO DIAGNÓSTICO DETALLADO...
    echo.
    call gradlew.bat assembleDebug --stacktrace --info | findstr /i "error\|exception\|failed"
    echo.
    echo 💡 POSIBLES CAUSAS RESTANTES:
    echo - Versión de Android Gradle Plugin incompatible
    echo - Configuración de compileSdk incorrecta
    echo - Dependencias conflictivas
    echo.
    echo 🚨 SOLUCIÓN RECOMENDADA:
    echo 1. Sincroniza proyecto en Android Studio (Sync)
    echo 2. Invalida cachés: File > Invalidate Caches and Restart
    echo 3. Verifica targetSdk y compileSdk en build.gradle
    echo.
) else (
    echo ✅ ¡COMPILACIÓN EXITOSA!
    echo.
    echo 🎉 PROBLEMA RESUELTO:
    echo - Error "contenido no permitido en prólogo" eliminado
    echo - Archivos XML limpios y funcionales
    echo - Layout simplificado funcionando
    echo.
    echo 🚀 INSTALANDO APLICACIÓN...
    call gradlew.bat installDebug >nul 2>&1
    if %errorlevel% equ 0 (
        echo ✅ ¡Aplicación instalada exitosamente!
        echo.
        echo Tu Tres en Raya está listo para usar! 🎮
    ) else (
        echo ⚠️  Compilación OK, pero verifica dispositivo para instalar
    )
)

echo.
echo ========================================
echo         DIAGNÓSTICO COMPLETADO
echo ========================================
pause
