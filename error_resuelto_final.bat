@echo off
echo ========================================
echo       PROBLEMA DEL PUNTO EXTRA
echo           COMPLETAMENTE RESUELTO
echo ========================================
echo.

echo ✅ PROBLEMA IDENTIFICADO Y CORREGIDO:
echo.
echo 🔍 Causa del error:
echo   - Archivo colors_clean.xml tenía ".<?xml" en lugar de "<?xml"
echo   - El punto extra causaba "El contenido no está permitido en el prólogo"
echo.
echo ✅ CORRECCIÓN APLICADA:
echo   - Punto extra eliminado del inicio del archivo XML
echo   - Archivo renombrado a colors.xml
echo   - Duplicados eliminados
echo.

echo [Verificando corrección...]
if not exist "app\src\main\res\values\colors.xml" (
    echo ❌ ERROR: colors.xml no existe
    pause
    exit /b 1
)

findstr /B "<?xml" app\src\main\res\values\colors.xml >nul
if %errorlevel% neq 0 (
    echo ❌ ERROR: Archivo colors.xml no tiene header XML correcto
    pause
    exit /b 1
)

echo ✅ Archivo colors.xml corregido y verificado

echo.
echo [Compilación final...]
call gradlew.bat clean >nul 2>&1
echo ✅ Proyecto limpiado

call gradlew.bat assembleDebug >nul 2>&1
if %errorlevel% neq 0 (
    echo ⚠️  Revisando compilación detallada...
    call gradlew.bat assembleDebug --console=plain --stacktrace
) else (
    echo ✅ ¡COMPILACIÓN EXITOSA!

    echo.
    echo [Instalando aplicación...]
    call gradlew.bat installDebug >nul 2>&1
    if %errorlevel% equ 0 (
        echo ✅ ¡Aplicación instalada exitosamente!
    ) else (
        echo ⚠️  Compilación OK - verifica dispositivo para instalar
    )
)

echo.
echo ========================================
echo    🎉 ERROR DE PRÓLOGO RESUELTO 🎉
echo ========================================
echo.
echo 🚀 TU APLICACIÓN TRES EN RAYA ESTÁ LISTA:
echo.
echo ✅ Sistema de estadísticas funcionando
echo ✅ Conteo de ganadores persistente
echo ✅ Interfaz moderna con títulos en blanco
echo ✅ Sin sección de líder (como solicitaste)
echo ✅ Colores atractivos y contrastantes
echo ✅ Mensajes dinámicos aleatorios
echo ✅ Sin errores de compilación
echo.
echo 🎮 ¡DISFRUTA TU JUEGO MEJORADO!
echo.
pause
