# MyWebServiceApp (Android Kotlin + Retrofit)

## Jalankan Server dulu
Lihat folder `server/`.

## Jalankan Android
1. Buka Android Studio -> Open -> pilih folder `android-studio/MyWebServiceApp`
2. Sync Gradle
3. Run di emulator

### Catatan IP / Base URL
- Emulator: `http://10.0.2.2:3000/`
- HP fisik: ganti baseUrl menjadi `http://IP_LAPTOP:3000/` (1 Wi-Fi)

Endpoint:
- GET `api/data`
- POST `api/data` body: `{ "name": "..." }`
