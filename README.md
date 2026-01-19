# 🎮 Tres en Raya - Kotlin Android

Un juego completo de Tres en Raya (Tic Tac Toe) desarrollado en Kotlin para Android, siguiendo el patrón Modelo-Vista-Controlador (MVC).

## 📱 Características

- ✅ Juego completo de Tres en Raya para dos jugadores
- ✅ Interfaz intuitiva y responsive
- ✅ Detección automática de victoria y empate
- ✅ Reinicio rápido del juego
- ✅ Soporte para orientación portrait
- ✅ Compatible con Android API 24+ (Android 7.0+)
- ✅ Optimizado para emuladores Pixel 9 y dispositivos modernos

## 🏗️ Arquitectura

El proyecto sigue el patrón **MVC (Modelo-Vista-Controlador)**:

### Modelo
- `Juego.kt` - Lógica principal del juego
- `Tablero.kt` - Gestión del tablero 3x3
- `Casilla.kt` - Representación de cada casilla
- `Jugador.kt` - Gestión de jugadores
- `DetectorVictoria.kt` - Detección de condiciones de victoria
- `ValidadorMovimiento.kt` - Validación de movimientos

### Vista
- `actividad_principal.xml` - Layout de la interfaz
- `strings.xml` - Textos en español
- `colors.xml` - Paleta de colores

### Controlador
- `MainActivity.kt` - Actividad principal que conecta Modelo y Vista

## 🚀 Configuración para Desarrollo

### Requisitos
- **Android Studio** Arctic Fox o superior
- **Android SDK** API 24-34
- **Kotlin** 1.9.20+
- **Gradle** 8.5+

### Configuraciones del Proyecto

#### 1. Configuración del Module (app/build.gradle.kts)
```kotlin
android {
    namespace = "com.example.tresenrayakotlin"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tresenrayakotlin"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
```

#### 2. AndroidManifest.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@android:style/Theme.Material.Light"
        tools:targetApi="31">
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:label="@string/app_name"
            android:screenOrientation="portrait"
            android:configChanges="orientation|screenSize|keyboardHidden">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

## 📋 Instrucciones de Instalación

### Para Desarrolladores

1. **Clonar/Abrir el proyecto en Android Studio**
   ```bash
   # Si usas Git
   git clone [url-del-repositorio]
   cd TresEnRayaKotlin
   ```

2. **Sincronizar el proyecto**
   - Android Studio → File → Sync Project with Gradle Files

3. **Configurar el emulador**
   - Crear un emulador Pixel 9 con API 34
   - O conectar un dispositivo físico con depuración USB activada

4. **Ejecutar el proyecto**
   - Presionar el botón "Run" (▶️) en Android Studio
   - O usar el comando: `./gradlew assembleDebug`

### Solución de Problemas Comunes

#### Error de compilación
Si encuentras errores de compilación:
```bash
# Limpiar el proyecto
./gradlew clean

# Reconstruir
./gradlew build
```

#### Problemas con el emulador
- Asegúrate de que el emulador tenga al menos API 24
- Verifica que tengas suficiente RAM disponible (mínimo 4GB)
- Habilita la aceleración de hardware en BIOS/UEFI

#### Errores de dependencias
- Verifica que Android SDK esté actualizado
- Sincroniza nuevamente el proyecto
- Invalida cachés: File → Invalidate Caches and Restart

## 🎯 Cómo Jugar

1. **Inicio**: El jugador X siempre comienza
2. **Turnos**: Los jugadores alternan tocando casillas vacías
3. **Victoria**: Tres símbolos iguales en línea (horizontal, vertical o diagonal)
4. **Empate**: Tablero lleno sin ganador
5. **Reiniciar**: Presionar "Reiniciar Juego" para una nueva partida

## 🔧 Estructura de Archivos

```
TresEnRayaKotlin/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/tresenrayakotlin/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   └── modelo/
│   │   │   │       ├── Juego.kt
│   │   │   │       ├── Tablero.kt
│   │   │   │       ├── Casilla.kt
│   │   │   │       ├── Jugador.kt
│   │   │   │       ├── DetectorVictoria.kt
│   │   │   │       └── ValidadorMovimiento.kt
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   └── actividad_principal.xml
│   │   │   │   ├── values/
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   └── colors.xml
│   │   │   │   └── ...
│   │   │   └── AndroidManifest.xml
│   │   └── ...
│   └── build.gradle.kts
├── gradle/
│   └── libs.versions.toml
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

## ✨ Próximas Mejoras

- [ ] Modo de juego contra IA
- [ ] Animaciones de movimiento
- [ ] Sonidos y efectos
- [ ] Contador de puntuación
- [ ] Temas personalizables
- [ ] Modo multijugador online

## 📝 Notas Técnicas

- **Lenguaje**: Kotlin 100%
- **Arquitectura**: MVC (Modelo-Vista-Controlador)
- **Compatibilidad**: Android 7.0+ (API 24+)
- **Pantallas**: Optimizado para móviles en portrait
- **Dependencias**: Mínimas (solo Android SDK estándar)

## 🐛 Reporte de Bugs

Si encuentras algún problema:
1. Verifica que cumples los requisitos mínimos
2. Intenta limpiar y reconstruir el proyecto
3. Revisa los logs de Android Studio
4. Crea un issue con detalles del error

---

**¡Disfruta jugando Tres en Raya!** 🎉
