@echo off
echo Compilando Tres en Raya...
call gradlew.bat clean
if %errorlevel% neq 0 (
    echo Error en clean
    exit /b 1
)

call gradlew.bat assembleDebug
if %errorlevel% neq 0 (
    echo Error en assembleDebug
    exit /b 1
)

echo Instalando en dispositivo...
call gradlew.bat installDebug
if %errorlevel% neq 0 (
    echo Error en installDebug
    exit /b 1
)

echo Build completado exitosamente!
pause
