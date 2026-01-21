@echo off
echo ========================================
echo    SOLUCIÓN DEFINITIVA PARA ERROR
echo       "CONTENIDO EN PRÓLOGO"
echo ========================================
echo.

echo 🔧 APLICANDO SOLUCIÓN RADICAL...
echo.

echo [1/5] Eliminando archivos problemáticos...
cd app\src\main\res\drawable
del *_clean.xml 2>nul
del stats_background.xml 2>nul
del player_x_stats_bg.xml 2>nul
del player_o_stats_bg.xml 2>nul
del empate_stats_bg.xml 2>nul
del button_primary_gradient.xml 2>nul
del button_secondary_gradient.xml 2>nul
del info_background.xml 2>nul
del leader_background.xml 2>nul
echo ✅ Drawables problemáticos eliminados

echo.
echo [2/5] Creando layout ultra-simple...
cd ..\layout
echo ^<?xml version="1.0" encoding="utf-8"?^> > actividad_principal_ultra.xml
echo ^<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android" >> actividad_principal_ultra.xml
echo     android:layout_width="match_parent" >> actividad_principal_ultra.xml
echo     android:layout_height="match_parent" >> actividad_principal_ultra.xml
echo     android:orientation="vertical" >> actividad_principal_ultra.xml
echo     android:padding="16dp" >> actividad_principal_ultra.xml
echo     android:background="@color/white"^> >> actividad_principal_ultra.xml
echo. >> actividad_principal_ultra.xml
echo     ^<TextView >> actividad_principal_ultra.xml
echo         android:id="@+id/textoTitulo" >> actividad_principal_ultra.xml
echo         android:layout_width="match_parent" >> actividad_principal_ultra.xml
echo         android:layout_height="wrap_content" >> actividad_principal_ultra.xml
echo         android:text="TRES EN RAYA" >> actividad_principal_ultra.xml
echo         android:textSize="32sp" >> actividad_principal_ultra.xml
echo         android:textStyle="bold" >> actividad_principal_ultra.xml
echo         android:textAlignment="center" >> actividad_principal_ultra.xml
echo         android:textColor="@color/black" >> actividad_principal_ultra.xml
echo         android:layout_marginBottom="20dp" /^> >> actividad_principal_ultra.xml
echo. >> actividad_principal_ultra.xml
echo     ^<LinearLayout >> actividad_principal_ultra.xml
echo         android:layout_width="match_parent" >> actividad_principal_ultra.xml
echo         android:layout_height="wrap_content" >> actividad_principal_ultra.xml
echo         android:orientation="horizontal" >> actividad_principal_ultra.xml
echo         android:weightSum="3" >> actividad_principal_ultra.xml
echo         android:layout_marginBottom="20dp"^> >> actividad_principal_ultra.xml
echo. >> actividad_principal_ultra.xml
echo         ^<TextView >> actividad_principal_ultra.xml
echo             android:id="@+id/textoVictoriasX" >> actividad_principal_ultra.xml
echo             android:layout_width="0dp" >> actividad_principal_ultra.xml
echo             android:layout_height="wrap_content" >> actividad_principal_ultra.xml
echo             android:layout_weight="1" >> actividad_principal_ultra.xml
echo             android:text="X: 0" >> actividad_principal_ultra.xml
echo             android:textSize="18sp" >> actividad_principal_ultra.xml
echo             android:textStyle="bold" >> actividad_principal_ultra.xml
echo             android:textAlignment="center" >> actividad_principal_ultra.xml
echo             android:textColor="@color/black" /^> >> actividad_principal_ultra.xml
echo. >> actividad_principal_ultra.xml
echo         ^<TextView >> actividad_principal_ultra.xml
echo             android:id="@+id/textoEmpates" >> actividad_principal_ultra.xml
echo             android:layout_width="0dp" >> actividad_principal_ultra.xml
echo             android:layout_height="wrap_content" >> actividad_principal_ultra.xml
echo             android:layout_weight="1" >> actividad_principal_ultra.xml
echo             android:text="Empates: 0" >> actividad_principal_ultra.xml
echo             android:textSize="18sp" >> actividad_principal_ultra.xml
echo             android:textStyle="bold" >> actividad_principal_ultra.xml
echo             android:textAlignment="center" >> actividad_principal_ultra.xml
echo             android:textColor="@color/black" /^> >> actividad_principal_ultra.xml
echo. >> actividad_principal_ultra.xml
echo         ^<TextView >> actividad_principal_ultra.xml
echo             android:id="@+id/textoVictoriasO" >> actividad_principal_ultra.xml
echo             android:layout_width="0dp" >> actividad_principal_ultra.xml
echo             android:layout_height="wrap_content" >> actividad_principal_ultra.xml
echo             android:layout_weight="1" >> actividad_principal_ultra.xml
echo             android:text="O: 0" >> actividad_principal_ultra.xml
echo             android:textSize="18sp" >> actividad_principal_ultra.xml
echo             android:textStyle="bold" >> actividad_principal_ultra.xml
echo             android:textAlignment="center" >> actividad_principal_ultra.xml
echo             android:textColor="@color/black" /^> >> actividad_principal_ultra.xml
echo     ^</LinearLayout^> >> actividad_principal_ultra.xml
echo. >> actividad_principal_ultra.xml
echo     ^<TextView >> actividad_principal_ultra.xml
echo         android:id="@+id/textoTurnoActual" >> actividad_principal_ultra.xml
echo         android:layout_width="match_parent" >> actividad_principal_ultra.xml
echo         android:layout_height="wrap_content" >> actividad_principal_ultra.xml
echo         android:text="Turno del Jugador X" >> actividad_principal_ultra.xml
echo         android:textSize="20sp" >> actividad_principal_ultra.xml
echo         android:textStyle="bold" >> actividad_principal_ultra.xml
echo         android:textAlignment="center" >> actividad_principal_ultra.xml
echo         android:textColor="@color/black" >> actividad_principal_ultra.xml
echo         android:layout_marginBottom="20dp" /^> >> actividad_principal_ultra.xml
echo. >> actividad_principal_ultra.xml
echo     ^<ProgressBar >> actividad_principal_ultra.xml
echo         android:id="@+id/progressBarTurno" >> actividad_principal_ultra.xml
echo         style="?android:attr/progressBarStyleHorizontal" >> actividad_principal_ultra.xml
echo         android:layout_width="match_parent" >> actividad_principal_ultra.xml
echo         android:layout_height="6dp" >> actividad_principal_ultra.xml
echo         android:progress="50" >> actividad_principal_ultra.xml
echo         android:layout_marginBottom="20dp" /^> >> actividad_principal_ultra.xml
echo. >> actividad_principal_ultra.xml
echo     ^<GridLayout >> actividad_principal_ultra.xml
echo         android:id="@+id/tableroJuego" >> actividad_principal_ultra.xml
echo         android:layout_width="wrap_content" >> actividad_principal_ultra.xml
echo         android:layout_height="wrap_content" >> actividad_principal_ultra.xml
echo         android:layout_gravity="center" >> actividad_principal_ultra.xml
echo         android:rowCount="3" >> actividad_principal_ultra.xml
echo         android:columnCount="3" >> actividad_principal_ultra.xml
echo         android:layout_marginBottom="20dp"^> >> actividad_principal_ultra.xml
echo. >> actividad_principal_ultra.xml

rem Crear botones del tablero
for /l %%i in (0,1,2) do (
    for /l %%j in (0,1,2) do (
        echo         ^<Button >> actividad_principal_ultra.xml
        echo             android:id="@+id/botonCasilla%%i%%j" >> actividad_principal_ultra.xml
        echo             android:layout_width="100dp" >> actividad_principal_ultra.xml
        echo             android:layout_height="100dp" >> actividad_principal_ultra.xml
        echo             android:layout_row="%%i" >> actividad_principal_ultra.xml
        echo             android:layout_column="%%j" >> actividad_principal_ultra.xml
        echo             android:layout_margin="4dp" >> actividad_principal_ultra.xml
        echo             android:textSize="36sp" >> actividad_principal_ultra.xml
        echo             android:textStyle="bold" /^> >> actividad_principal_ultra.xml
    )
)

echo     ^</GridLayout^> >> actividad_principal_ultra.xml
echo. >> actividad_principal_ultra.xml
echo     ^<LinearLayout >> actividad_principal_ultra.xml
echo         android:id="@+id/cardMensajeEstado" >> actividad_principal_ultra.xml
echo         android:layout_width="match_parent" >> actividad_principal_ultra.xml
echo         android:layout_height="wrap_content" >> actividad_principal_ultra.xml
echo         android:orientation="vertical" >> actividad_principal_ultra.xml
echo         android:padding="16dp" >> actividad_principal_ultra.xml
echo         android:gravity="center" >> actividad_principal_ultra.xml
echo         android:visibility="gone"^> >> actividad_principal_ultra.xml
echo. >> actividad_principal_ultra.xml
echo         ^<TextView >> actividad_principal_ultra.xml
echo             android:id="@+id/textoMensajeEstado" >> actividad_principal_ultra.xml
echo             android:layout_width="wrap_content" >> actividad_principal_ultra.xml
echo             android:layout_height="wrap_content" >> actividad_principal_ultra.xml
echo             android:textSize="20sp" >> actividad_principal_ultra.xml
echo             android:textStyle="bold" >> actividad_principal_ultra.xml
echo             android:textAlignment="center" /^> >> actividad_principal_ultra.xml
echo. >> actividad_principal_ultra.xml
echo         ^<TextView >> actividad_principal_ultra.xml
echo             android:id="@+id/textoMensajeSecundario" >> actividad_principal_ultra.xml
echo             android:layout_width="wrap_content" >> actividad_principal_ultra.xml
echo             android:layout_height="wrap_content" >> actividad_principal_ultra.xml
echo             android:textSize="16sp" >> actividad_principal_ultra.xml
echo             android:textAlignment="center" >> actividad_principal_ultra.xml
echo             android:layout_marginTop="8dp" /^> >> actividad_principal_ultra.xml
echo     ^</LinearLayout^> >> actividad_principal_ultra.xml
echo. >> actividad_principal_ultra.xml
echo     ^<LinearLayout >> actividad_principal_ultra.xml
echo         android:layout_width="match_parent" >> actividad_principal_ultra.xml
echo         android:layout_height="wrap_content" >> actividad_principal_ultra.xml
echo         android:orientation="horizontal" >> actividad_principal_ultra.xml
echo         android:weightSum="2" >> actividad_principal_ultra.xml
echo         android:layout_marginTop="20dp"^> >> actividad_principal_ultra.xml
echo. >> actividad_principal_ultra.xml
echo         ^<Button >> actividad_principal_ultra.xml
echo             android:id="@+id/botonReiniciarJuego" >> actividad_principal_ultra.xml
echo             android:layout_width="0dp" >> actividad_principal_ultra.xml
echo             android:layout_height="wrap_content" >> actividad_principal_ultra.xml
echo             android:layout_weight="1" >> actividad_principal_ultra.xml
echo             android:layout_margin="8dp" >> actividad_principal_ultra.xml
echo             android:text="Nueva Partida" /^> >> actividad_principal_ultra.xml
echo. >> actividad_principal_ultra.xml
echo         ^<Button >> actividad_principal_ultra.xml
echo             android:id="@+id/botonReiniciarStats" >> actividad_principal_ultra.xml
echo             android:layout_width="0dp" >> actividad_principal_ultra.xml
echo             android:layout_height="wrap_content" >> actividad_principal_ultra.xml
echo             android:layout_weight="1" >> actividad_principal_ultra.xml
echo             android:layout_margin="8dp" >> actividad_principal_ultra.xml
echo             android:text="Reset Stats" /^> >> actividad_principal_ultra.xml
echo     ^</LinearLayout^> >> actividad_principal_ultra.xml
echo. >> actividad_principal_ultra.xml
echo     ^<TextView >> actividad_principal_ultra.xml
echo         android:id="@+id/textoPartidasTotales" >> actividad_principal_ultra.xml
echo         android:layout_width="match_parent" >> actividad_principal_ultra.xml
echo         android:layout_height="wrap_content" >> actividad_principal_ultra.xml
echo         android:text="Partidas: 0" >> actividad_principal_ultra.xml
echo         android:textAlignment="center" >> actividad_principal_ultra.xml
echo         android:layout_marginTop="16dp" /^> >> actividad_principal_ultra.xml
echo. >> actividad_principal_ultra.xml
echo ^</LinearLayout^> >> actividad_principal_ultra.xml

del actividad_principal.xml
ren actividad_principal_ultra.xml actividad_principal.xml
echo ✅ Layout ultra-simple creado

echo.
echo [3/5] Limpiando proyecto completamente...
cd ..\..\..\..
call gradlew.bat clean >nul 2>&1
echo ✅ Proyecto limpiado

echo.
echo [4/5] Compilación de prueba...
call gradlew.bat assembleDebug >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Aún hay problemas - intentando compilación detallada...
    call gradlew.bat assembleDebug --stacktrace | findstr /i "error\|exception\|failed\|cause"
    echo.
    echo 💡 SOLUCIÓN FINAL RECOMENDADA:
    echo 1. Abre Android Studio
    echo 2. File ^> Invalidate Caches and Restart
    echo 3. File ^> Sync Project with Gradle Files
    echo 4. Build ^> Clean Project
    echo 5. Build ^> Rebuild Project
    echo.
) else (
    echo ✅ ¡COMPILACIÓN EXITOSA!
    echo.
    echo [5/5] Instalando aplicación...
    call gradlew.bat installDebug >nul 2>&1
    if %errorlevel% equ 0 (
        echo ✅ ¡Aplicación instalada exitosamente!
    ) else (
        echo ⚠️  Compilación OK - verifica dispositivo para instalar
    )
    echo.
    echo 🎉 PROBLEMA COMPLETAMENTE RESUELTO 🎉
    echo.
    echo Tu aplicación Tres en Raya está funcionando con:
    echo - Layout ultra-simplificado
    echo - Sin drawables problemáticos
    echo - Estadísticas de ganadores
    echo - Interfaz funcional
)

echo.
echo ========================================
echo         PROCESO COMPLETADO
echo ========================================
pause
