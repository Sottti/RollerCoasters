# Roller Coasters

![API](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/Sottti/RollerCoasters/refs/heads/dev/gradle/libs.versions.toml&query=$.versions.minSdk&label=API&color=brightgreen&suffix=%2B&logo=android&logoColor=white)
![Compose BOM](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/Sottti/RollerCoasters/refs/heads/dev/gradle/libs.versions.toml&query=$.versions.compose-bom&label=Compose%20BOM&color=007ACC&logo=jetpackcompose&logoColor=white)
![Navigation](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/Sottti/RollerCoasters/refs/heads/dev/gradle/libs.versions.toml&query=$.versions.compose-navigation3&label=Navigation&color=4CAF50&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/Sottti/RollerCoasters/refs/heads/dev/gradle/libs.versions.toml&query=$.versions.kotlin&label=Kotlin&color=7F52FF&logo=kotlin&logoColor=white)
![GitHub last commit](https://img.shields.io/github/last-commit/Sottti/RollerCoasters?logo=github&logoColor=white)
![GitHub repo size](https://img.shields.io/github/repo-size/Sottti/RollerCoasters)
[![License](https://img.shields.io/badge/License-MIT-blue?style=flat&logo=opensourceinitiative&logoColor=white)](https://opensource.org/licenses/MIT)

## Overview

Roller Coasters is a personal playground where I experiment with modern Android development
practices, libraries, and tools. It is a space to try new APIs, patterns, and approaches—especially
around Jetpack Compose—without the constraints of production code.

The project is under active development and the codebase is not yet stable or nearly finished.

## ⚙️ Tech Stack & Architecture

This template is built using modern Android development practices and libraries:

* **Language:** 100% [Kotlin](https://kotlinlang.org/)
* **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) for declarative UI.
    * **Theming:** [Material 3](https://m3.material.io/) (Material You) with dynamic color support.
    * **Navigation:** [Compose Navigation 3](https://developer.android.com/guide/navigation/navigation-3) for screen transitions.
* **Architecture:** Follows Google's official "Guide to app architecture", combining [MVVM](https://developer.android.com/jetpack/guide) (Model-View-ViewModel) with principles from Clean Architecture.
    * **UI Layer:** State-driven UI using `ViewModel`, `State`, and `Actions`. ViewModels follow a [declarative approach](https://proandroiddev.com/loading-initial-data-in-launchedeffect-vs-viewmodel-f1747c20ce62).
    * **Domain Layer:** (Optional) UseCases encapsulate specific business logic (e.g., `GetFavoriteCoastersUseCase`).
    * **Data Layer:** `Repository` pattern providing a single source of truth.
* **Asynchronicity:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flows](https://developer.android.com/kotlin/flow) for managing background tasks and data streams.
* **Dependency Injection:** [Hilt](https://dagger.dev/hilt/) for managing dependencies throughout the app.
* **Networking:** [Ktor Client](https://ktor.io/docs/client-overview.html) for REST API communication.
* **Serialization:** [Kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) for JSON parsing.
* **Testing:**
    * **Unit Tests:** [JUnit 4](https://junit.org/junit4/) & [Mockk](https://mockk.io/)
    * **Screenshot Tests:** [Paparazzi](https://github.com/cashapp/paparazzi)
    * **UI Tests:** [Compose Test Rules](https://developer.android.com/jetpack/compose/testing)

## 📱 App Features

* **Explore Feed:** View roller coasters, filterable by various criteria (specs, materials, etc.).
* **Favorites:** Mark and view your favorite roller coasters.
* **Details Screen:** See detailed information and images for each coaster.
* **Settings:** Customize theme (Light/Dark/System), enable/disable dynamic color, adjust color contrast, change language, and select measurement system (Metric/Imperial).
* **Modern UI:**
    * Dynamic Theming (Material You).
    * Support for multiple languages.
    * Edge-to-edge display.
    * Predictive back navigation.
* **About Screen:** Information about the project/developer.

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

## 📁 Project Structure

This project follows a standard multi-module setup, which is highly recommended for separation of
concerns and build speed.

## 🚀 Planned Features or Improvements

* **Search:** Find roller coasters by name.
* **Parks:** Add information about amusement parks.
* **Enhanced Discovery:** More filtering options (manufacturer, park, etc.).
* **Robustness:** Improved error handling and empty state displays.
* **UI/UX:** Add animations; improve support for large screens and foldables.
* **Navigation:** Migrate fully to Jetpack Compose Navigation 3 (if not already done).

## 📜 License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT) - see the [LICENSE](LICENSE) file for details.
