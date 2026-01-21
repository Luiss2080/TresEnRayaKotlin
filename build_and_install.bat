@echo off
echo ========================================
echo    COMPILANDO TRES EN RAYA - KOTLIN
echo ========================================
echo.

echo [1/4] Limpiando proyecto anterior...
call gradlew.bat clean
if %errorlevel% neq 0 (
    echo ❌ ERROR: Fallo en clean
    pause
    exit /b 1
)
echo ✅ Clean completado

echo.
echo [2/4] Compilando aplicación...
call gradlew.bat assembleDebug
if %errorlevel% neq 0 (
    echo ❌ ERROR: Fallo en assembleDebug
    pause
    exit /b 1
)
echo ✅ Compilación exitosa

echo.
echo [3/4] Verificando dispositivos conectados...
adb devices
echo.

echo [4/4] Instalando en dispositivo/emulador...
call gradlew.bat installDebug
if %errorlevel% neq 0 (
    echo ❌ ERROR: Fallo en installDebug
    echo Asegúrate de que hay un dispositivo/emulador conectado
    pause
    exit /b 1
)

echo.
echo ========================================
echo ✅ ¡BUILD COMPLETADO EXITOSAMENTE! ✅
echo ========================================
echo.
echo La aplicación ha sido instalada en tu dispositivo.
echo Puedes abrirla desde el launcher o con:
echo adb shell am start -n com.example.tresenrayakotlin.debug/.MainActivity
echo.
pause
