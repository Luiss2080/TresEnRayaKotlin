@echo off
echo ==========================================
echo  INSTALADOR TRES EN RAYA - PIXEL 9
echo ==========================================
echo.

echo [1/4] Limpiando proyecto...
call .\gradlew.bat clean

echo [2/4] Compilando aplicacion...
call .\gradlew.bat assembleDebug

echo [3/4] Verificando emulador Pixel 9...
adb devices

echo [4/4] Instalando en emulador...
adb install app\build\outputs\apk\debug\app-debug.apk

echo.
echo ==========================================
echo  INSTALACION COMPLETADA
echo  Busca "Tres en Raya" en el emulador
echo ==========================================
pause
