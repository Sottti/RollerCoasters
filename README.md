# Roller Coasters Android Playground

Roller Coasters is a personal project where I experiment with modern Android development practices. The project is split into many Gradle modules following a clean architecture approach.

## Features

- **Jetpack Compose** for all UI components and previews
- **Hilt** for dependency injection
- **Room**, **Paging** and **DataStore** for local persistence
- **Ktor** client for networking
- **WorkManager** for background tasks
- **Paparazzi** screenshot tests
- **Dynamic theming** (Material 3, dynamic colors and color contrast)
- Support for multiple languages and measurement systems
- Modular design system components

The codebase is intentionally extensive and is intended as a playground to try out the latest Android APIs.

## Project Structure

```
app/                        - main application module
presentation/               - feature UI modules (Compose)
domain/                     - business logic
data/                       - local/network data sources
buildSrc/                   - Gradle build configuration
```

## Running the project

This project requires the Android SDK to be installed and `local.properties` pointing to it. Once configured you can build the project using:

```bash
./gradlew assembleDebug
```

Snapshot tests can be run with:

```bash
./gradlew verifyPaparazziDebug
```

## License

This repository is provided for learning purposes and does not include an explicit license.
