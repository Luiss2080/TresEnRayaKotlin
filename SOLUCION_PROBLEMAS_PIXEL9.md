# 🔧 SOLUCIÓN DE PROBLEMAS PIXEL 9 EMULATOR

## 🚨 PROBLEMAS COMUNES Y SOLUCIONES

### 1. El emulador no inicia
```bash
# Verificar que el SDK esté instalado
%ANDROID_HOME%/tools/android list targets

# Crear nuevo AVD si es necesario
%ANDROID_HOME%/tools/android create avd -n Pixel9 -t android-34
```

### 2. Error "Unable to locate adb"
1. Abrir Android Studio
2. File → Settings → Appearance & Behavior → System Settings → Android SDK
3. Verificar que "Android SDK Platform-Tools" esté instalado
4. Añadir a PATH: `C:\Users\LuissxD\AppData\Local\Android\Sdk\platform-tools`

### 3. Gradle build falla
```bash
# Limpiar completamente
./gradlew clean
./gradlew build --refresh-dependencies

# Si sigue fallando, eliminar .gradle folder
rmdir /s .gradle
```

### 4. App se instala pero crashea
1. Verificar en Android Studio Logcat
2. Buscar errores relacionados con:
   - Permisos
   - Versión API incorrecta
   - Recursos faltantes

### 5. Emulador muy lento
1. Verificar que esté habilitado HAXM o Hyper-V
2. Aumentar RAM del emulador (mínimo 4GB)
3. Habilitar "Use Host GPU" en AVD settings

### 6. Pantalla en blanco o no responde
1. Cold boot del emulador:
   ```
   emulator -avd Pixel9 -no-snapshot-load
   ```
2. Wipe data si es necesario

## 🎯 PASOS ESPECÍFICOS PARA PIXEL 9

### Crear emulador optimizado:
1. Tools → AVD Manager → Create Virtual Device
2. Seleccionar Pixel 9 (API 34)
3. Configuraciones recomendadas:
   - RAM: 4096 MB
   - VM Heap: 512 MB
   - Internal Storage: 8 GB
   - Use Host GPU: ✅
   - Store a snapshot for faster startup: ✅

### Verificar compatibilidad API:
- **minSdk: 24** (Android 7.0)
- **targetSdk: 34** (Android 14)
- **compileSdk: 34**

### Variables de entorno necesarias:
```bash
ANDROID_HOME=C:\Users\LuissxD\AppData\Local\Android\Sdk
PATH=%ANDROID_HOME%\tools;%ANDROID_HOME%\platform-tools;%PATH%
```

## 📱 COMANDOS ÚTILES

### Verificar dispositivos conectados:
```bash
adb devices
```

### Instalar APK manualmente:
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Ver logs en tiempo real:
```bash
adb logcat | findstr "TresEnRayaKotlin"
```

### Reiniciar ADB:
```bash
adb kill-server
adb start-server
```

## 🆘 ÚLTIMA OPCIÓN

Si nada funciona:
1. Desinstalar y reinstalar Android Studio
2. Borrar completamente carpeta `%ANDROID_HOME%`
3. Reinstalar Android SDK
4. Crear nuevo proyecto desde plantilla
5. Copiar código del Tres en Raya

## ✅ VERIFICACIÓN FINAL

Antes de ejecutar:
- [ ] Emulador Pixel 9 creado y funcionando
- [ ] Android Studio actualizado
- [ ] Proyecto sincronizado sin errores
- [ ] Variables de entorno configuradas
- [ ] APK compilado exitosamente

🎮 **¡Tu juego debería funcionar perfectamente en Pixel 9!**
