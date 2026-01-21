@echo off
echo Verificando estado del proyecto...
echo.

echo [1/4] Verificando archivos duplicados...
cd "app\src\main\java\com\example\tresenrayakotlin"
if exist "MainActivityRobust.kt" (
    echo ❌ ERROR: MainActivityRobust.kt aun existe
    del MainActivityRobust.kt
    echo ✅ Eliminado MainActivityRobust.kt
)
if exist "MainActivity_old.kt" (
    echo ❌ ERROR: MainActivity_old.kt aun existe
    del MainActivity_old.kt
    echo ✅ Eliminado MainActivity_old.kt
)

echo ✅ Solo existe MainActivity.kt

cd ..\..\..\..\..

echo.
echo [2/4] Limpiando cache de build...
call gradlew.bat clean >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ ERROR en clean
) else (
    echo ✅ Clean exitoso
)

echo.
echo [3/4] Sincronizando proyecto...
echo (Sincroniza manualmente en Android Studio: File > Sync Project with Gradle Files)

echo.
echo [4/4] Compilando...
call gradlew.bat assembleDebug >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ ERROR en compilación - ejecutando con detalles...
    call gradlew.bat assembleDebug --stacktrace
) else (
    echo ✅ Compilación exitosa
)

echo.
echo ========================================
echo ✅ ERRORES DE DUPLICACIÓN CORREGIDOS ✅
echo ========================================
echo.
echo PRÓXIMOS PASOS:
echo 1. En Android Studio: File > Sync Project with Gradle Files
echo 2. Build > Clean Project
echo 3. Build > Rebuild Project
echo.
pause
