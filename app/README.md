# Example Application

This is an Example Application which involved next technologies/approaches/features:
- MVI architecture
- Kotlin DSL (Kotlin-based Gradle build scripts)
- Koin
- Instan App experience

## Instant App
To have possibility to start this app as an InstantApp instance
- ANDROID_HOME environment variable should be set up
- add this line to the end of `local.properties file` - `bundletool=bundletool-all-1.2.0.jar`

To start InstantApp the `runinstantInstantDebug` gradle task should be executed either with a url parameter set to `http://example.com/main` or without it.
```bash
./gradlew runinstantInstantDebug --url=http://example.com/main
```
You should delete instant App from device settings -> applications to have possibility to run it again