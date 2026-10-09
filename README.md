# Roller Coasters

![API](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/Sottti/RollerCoasters/refs/heads/dev/gradle/libs.versions.toml&query=$.versions.minSdk&label=API&color=brightgreen&suffix=%2B&logo=android&logoColor=white)
![Compose BOM](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/Sottti/RollerCoasters/refs/heads/dev/gradle/libs.versions.toml&query=$.versions.compose-bom&label=Compose%20BOM&color=007ACC&logo=jetpackcompose&logoColor=white)
![Navigation Compose](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/Sottti/RollerCoasters/refs/heads/dev/gradle/libs.versions.toml&query=$.versions.compose-navigation2&label=Navigation%20Compose&color=4CAF50&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/Sottti/RollerCoasters/refs/heads/dev/gradle/libs.versions.toml&query=$.versions.kotlin&label=Kotlin&color=7F52FF&logo=kotlin&logoColor=white)
![GitHub last commit](https://img.shields.io/github/last-commit/Sottti/RollerCoasters?logo=github&logoColor=white)
![GitHub repo size](https://img.shields.io/github/repo-size/Sottti/RollerCoasters)
[![License](https://img.shields.io/badge/License-MIT-blue?style=flat&logo=opensourceinitiative&logoColor=white)](https://opensource.org/licenses/MIT)

## 🧭 Overview

Roller Coasters is a personal playground where I experiment with modern Android development
practices, libraries, and tools. It is a space to try new APIs, patterns, and approaches,
especially around Jetpack Compose, without the constraints of production code.

The project is under active development and the codebase is not yet stable or nearly finished.

## 📷 Screenshots

### 💡 Light Theme

<p align="center">
   <img src="https://github.com/user-attachments/assets/1d66db52-e2d5-4a5b-af38-9dfd4201cff2" width="24%"/>
   <img src="https://github.com/user-attachments/assets/723cf128-155c-4ed2-9dae-f694d5bf8d33" width="24%"/>
   <img src="https://github.com/user-attachments/assets/b155a20a-e8d2-4047-8ff2-b804b0abf548" width="24%"/>
   <img src="https://github.com/user-attachments/assets/cc648cd7-0ec9-4bf4-8e05-9ae1818cf3aa" width="24%"/>
</p>

### 🌙 Dark Theme

<p align="center">
    <img src="https://github.com/user-attachments/assets/dba6e3bb-e418-4aa2-8191-67947dd55db9" width="24%"/>
    <img src="https://github.com/user-attachments/assets/92d0a3e9-a25f-4fca-88c8-821cf5875f7e" width="24%"/>
    <img src="https://github.com/user-attachments/assets/1a9840ee-9294-4e96-911d-f33e572c3ce9" width="24%"/>
    <img src="https://github.com/user-attachments/assets/e8f79eab-7e5e-4d75-b6d6-156b96df1dce" width="24%"/>
</p>

## ⚙️ Tech Stack & Architecture

This project is built using modern Android development practices and libraries:

* **Language:** 100% [Kotlin](https://kotlinlang.org/)
* **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) for declarative UI.
    * **Theming:** [Material 3](https://m3.material.io/) (Material You) with dynamic color support.
    * **Navigation:** [Navigation Compose (Navigation 2)](https://developer.android.com/develop/ui/compose/navigation) for
      screen transitions.
* **Architecture:** Follows Google's official "Guide to app architecture",
  combining [MVVM](https://developer.android.com/jetpack/guide) (Model-View-ViewModel) with
  principles from Clean Architecture.
    * **UI Layer:** State-driven UI using `ViewModel`, `State`, and `Actions`. ViewModels follow
      a [declarative approach](https://proandroiddev.com/loading-initial-data-in-launchedeffect-vs-viewmodel-f1747c20ce62).
    * **Domain Layer:** (Optional) UseCases encapsulate specific business logic (e.g.,
      `GetFavoriteCoastersUseCase`).
    * **Data Layer:** `Repository` pattern providing a single source of truth.
* **Asynchronicity:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flows](https://developer.android.com/kotlin/flow)
  for managing background tasks and data streams.
* **Dependency Injection:** [Hilt](https://dagger.dev/hilt/) for managing dependencies throughout
  the app.
* **Networking:** [Ktor Client](https://ktor.io/docs/client-create-and-configure.html) for REST API
  communication.
* **Serialization:** [Kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) for
  JSON parsing.
* **Testing:**
    * **Unit Tests:** [JUnit 4](https://junit.org/junit4/) & [Mockk](https://mockk.io/)
    * **Screenshot Tests:** [Paparazzi](https://github.com/cashapp/paparazzi)
    * **UI Tests:** [Compose Test Rules](https://developer.android.com/jetpack/compose/testing)

## 📱 App Features

* **Explore Feed:** View roller coasters, filterable by various criteria (specs, materials, etc.).
* **Favorites:** Mark and view your favorite roller coasters.
* **Details Screen:** See detailed information and images for each coaster.
* **Settings:**
    * Customize theme (Light/Dark/System)
    * Enable/disable dynamic color
    * Adjust color contrast
    * Change language (English, Spanish and Galician)
    * Select measurement system (Metric/Imperial).
* **Modern UI:**
    * Dynamic Theming (Material You).
    * Support for multiple languages.
    * Edge-to-edge display.
    * Predictive back navigation.
* **About Screen:** Information about the project/developer.

## Getting Started

These instructions follow the default `dev` branch. Dependency versions are defined in
[the version catalog](gradle/libs.versions.toml).

### Requirements

* **JDK 17**, selected as the Gradle JDK in Android Studio or through `JAVA_HOME` for command-line builds.
* **Android SDK Platform 36** and Android SDK Platform-Tools.
* **An emulator or Android device running API 26 (Android 8.0) or newer** to run the app.
* Use the checked-in **Gradle wrapper**; a separate Gradle installation is not needed.

### Setup

Clone the default branch and open the project root in Android Studio:

```bash
git clone --branch dev https://github.com/Sottti/RollerCoasters.git
cd RollerCoasters
```

Set the SDK location in the root `local.properties` file. Android Studio normally creates this file;
for command-line setup, create it with your own SDK path:

```properties
sdk.dir=/absolute/path/to/Android/sdk
```

This file is ignored by Git. Sync the project, select the `app` run configuration, and choose a device.

### Build and Run

Build the debug APK:

```bash
./gradlew :app:assembleDebug
```

With an emulator running or a device connected with USB debugging enabled, install the debug app:

```bash
./gradlew :app:installDebug
```

Open Roller Coasters on the device, or use **Run** in Android Studio to build, install, and launch it.

## Running Tests

Run all local unit tests, including Android module tests and the plain Kotlin/JVM module tests:

```bash
./gradlew test
```

For a narrower run, target individual modules:

```bash
./gradlew :domain:roller-coasters:test :presentation:settings:testDebugUnitTest
```

Verify the Paparazzi screenshots against the checked-in baselines without an emulator:

```bash
./gradlew verifyPaparazziDebug
```

When a visual change is intentional, update the baselines and review the resulting image diff:

```bash
./gradlew recordPaparazziDebug
```

Device-based tests require a running emulator or connected device:

```bash
./gradlew connectedDebugAndroidTest
```

The existing [CI workflow](.github/workflows/ci.yml) runs `assembleDebug` and `testDebugUnitTest`.
The `test` command above also covers the plain Kotlin/JVM modules.

## 📁 Project Structure

The app is split into Gradle modules by responsibility:

| Directory | Responsibility |
| --- | --- |
| [`app/`](app/) | Application entry point, startup, manifest, and app packaging. |
| [`presentation/`](presentation/) | Compose screens, ViewModels, navigation, the design system, previews, and screenshot-test support. |
| [`domain/`](domain/) | Models, repository contracts, and use cases. |
| [`data/`](data/) | Repository implementations, local storage, network access, settings, and background synchronization. |
| [`di/`](di/) | Dependency wiring across the app's layers. |
| [`utils/`](utils/) | Shared lifecycle and date/time utilities. |
| [`buildSrc/`](buildSrc/) | Shared Gradle module definitions. |

See [`settings.gradle.kts`](settings.gradle.kts) for the complete module list.

## 🚀 Planned Features or Improvements

* **Search:** Find roller coasters by name.
* **Parks:** Add information about amusement parks.
* **Enhanced Discovery:** More filtering options (manufacturer, park, etc.).
* **Robustness:** Improved error handling and empty state displays.
* **UI/UX:** Add animations; improve support for large screens and foldables.
* **Navigation:** Migrate from Navigation Compose (Navigation 2) to Navigation 3.

## 📜 License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT) - see
the [LICENSE](LICENSE) file for details.
