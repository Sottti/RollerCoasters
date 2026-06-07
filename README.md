# Roller Coasters

[![Android CI](https://github.com/Sottti/RollerCoasters/actions/workflows/ci.yml/badge.svg)](https://github.com/Sottti/RollerCoasters/actions/workflows/ci.yml)
![API](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/Sottti/RollerCoasters/refs/heads/dev/gradle/libs.versions.toml&query=$.versions.minSdk&label=API&color=brightgreen&suffix=%2B&logo=android&logoColor=white)
![Compose BOM](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/Sottti/RollerCoasters/refs/heads/dev/gradle/libs.versions.toml&query=$.versions.compose-bom&label=Compose%20BOM&color=007ACC&logo=jetpackcompose&logoColor=white)
![Navigation](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/Sottti/RollerCoasters/refs/heads/dev/gradle/libs.versions.toml&query=$.versions.compose-navigation3&label=Navigation&color=4CAF50&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/dynamic/toml?url=https://raw.githubusercontent.com/Sottti/RollerCoasters/refs/heads/dev/gradle/libs.versions.toml&query=$.versions.kotlin&label=Kotlin&color=7F52FF&logo=kotlin&logoColor=white)
![GitHub last commit](https://img.shields.io/github/last-commit/Sottti/RollerCoasters?logo=github&logoColor=white)
![GitHub repo size](https://img.shields.io/github/repo-size/Sottti/RollerCoasters)
[![License](https://img.shields.io/badge/License-MIT-blue?style=flat&logo=opensourceinitiative&logoColor=white)](LICENSE)

Modern Kotlin Android playground for exploring roller coaster discovery with Jetpack Compose, Hilt,
Ktor, Room/Paging, Google Maps, Paparazzi, and a modular MVVM/Clean Architecture setup.

<p align="center">
   <img src="https://github.com/user-attachments/assets/1d66db52-e2d5-4a5b-af38-9dfd4201cff2" alt="Explore screen in light theme" width="24%"/>
   <img src="https://github.com/user-attachments/assets/723cf128-155c-4ed2-9dae-f694d5bf8d33" alt="Roller coaster details screen in light theme" width="24%"/>
   <img src="https://github.com/user-attachments/assets/b155a20a-e8d2-4047-8ff2-b804b0abf548" alt="Settings screen in light theme" width="24%"/>
   <img src="https://github.com/user-attachments/assets/dba6e3bb-e418-4aa2-8191-67947dd55db9" alt="Explore screen in dark theme" width="24%"/>
</p>

## Disclaimer

This repository is a personal playground for trying modern Android APIs, libraries, architecture
patterns, and tooling without the constraints of production code. It is intentionally under active
development, so implementation details and module boundaries may change as the experiment evolves.

## Overview

Roller Coasters is an Android app for browsing, filtering, saving, and inspecting roller coaster
data. The app uses a layered multi-module setup to explore how a real product codebase can keep UI,
domain logic, data access, dependency injection, and shared tooling separated while still feeling
fast to iterate on.

## Features

This app uses modern libraries and tools from the Android ecosystem:

* **Tech Stack:** 100% [Kotlin](https://kotlinlang.org/)
* **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) for declarative UI.
    * **Theming:** [Material 3](https://m3.material.io/) with dynamic color and contrast support.
    * **Navigation:** [Compose Navigation 3](https://developer.android.com/guide/navigation/navigation-3)
      for screen transitions.
* **Architecture:** Follows Google's official "Guide to app architecture".
    * [MVVM](https://developer.android.com/jetpack/guide) (Model-View-ViewModel).
    * **UI Layer:** State-driven UI with `ViewModel`, `State`, and `Actions`.
    * **Domain Layer:** Use cases and canonical models for app behavior.
    * **Data Layer:** Repository pattern backed by local and remote data sources.
* **Asynchronous work:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
  & [Flows](https://developer.android.com/kotlin/flow) for background work and reactive streams.
* **Dependency Injection:** [Hilt](https://dagger.dev/hilt/) for managing dependencies.
* **Networking:** [Ktor Client](https://ktor.io/docs/client-overview.html) for REST API
  communication.
* **Persistence:** [Room](https://developer.android.com/training/data-storage/room) and
  [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) for local data.
* **Maps:** [Maps Compose](https://github.com/googlemaps/android-maps-compose) for coaster location
  UI.
* **Serialization:** [Kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) for
  JSON parsing.
* **Testing and quality:**
    * **Unit Tests:** [JUnit 4](https://junit.org/junit4/) & [Mockk](https://mockk.io/)
    * **Screenshot Tests:** [Paparazzi](https://github.com/cashapp/paparazzi)
    * **Static Analysis:** Detekt, Compose rules, Android Lint, and Kover coverage checks.

### Running the test suites

Execute the following Gradle tasks from the project root to run the automated checks locally:

> Run Gradle with JDK 21 or newer.

| Suite                    | Command                                                    | Description                                      |
|--------------------------|------------------------------------------------------------|--------------------------------------------------|
| Module boundaries        | `./gradlew checkModuleDependencyBoundaries`                | Validates high-level Gradle module dependencies. |
| Static analysis          | `./gradlew detekt lintDebug`                               | Runs Detekt and Android Lint.                    |
| Local/unit tests         | `./gradlew test -PexcludeSnapshotTests=true`               | Executes JVM tests while excluding snapshots.    |
| Screenshot tests         | `./gradlew --no-parallel verifyPaparazziDebug`             | Verifies committed Paparazzi goldens.            |
| Coverage                 | `./gradlew koverXmlReport koverVerify -PexcludeSnapshotTests=true` | Generates and verifies Kover coverage.   |
| Debug build              | `./gradlew assembleDebug`                                  | Builds the debug APK.                            |

> Map previews and runtime map features expect `MAPS_API_KEY` in `local.properties` or CI secrets.

### Additional Highlights

* **Roller coaster discovery:** Explore, search, and detail screens are split into independent
  presentation modules backed by shared domain and data contracts.
* **Design-system playground:** Reusable Compose components, typography, colors, shapes, cards,
  dialogs, maps, images, and preview fixtures live under `presentation:design-system`.
* **Localized settings:** Theme, dynamic color, contrast, language, and measurement-system settings
  are modeled through dedicated domain/data modules.
* **Snapshot tooling:** Paparazzi helpers provide consistent device, theme, and preview parameters
  across screen and component screenshots.
* **Edge-to-edge ready:** Compose screens are built around modern Android system-bar and inset
  behavior.
* **Dependency hygiene:** Version catalog, Dependabot, Gradle Versions Plugin, Detekt, Lint, Kover,
  and CI checks keep the playground current while still catching regressions.

### Incoming Features

* **Parks:** Add richer amusement-park data and park-oriented discovery.
* **Enhanced discovery:** Expand filtering by manufacturer, location, ride type, and park.
* **Robustness:** Continue improving error handling, empty states, and offline behavior.
* **Large screens:** Explore adaptive layouts for tablets, foldables, and landscape devices.
* **Motion:** Add purposeful transitions and animations where they clarify navigation or state
  changes.

## Architecture (MVVM + Clean)

Roller Coasters uses a state-driven MVVM (Model-View-ViewModel) architecture combined with
principles from Clean Architecture.

* **UI (Compose):** Observes `State` from the `ViewModel` and sends `Actions` back to it.
* **ViewModel:** Handles presentation logic, consumes actions, calls use cases or repositories, and
  exposes a single state stream for the UI.
* **UseCases (Domain Layer):** Encapsulate focused pieces of app behavior and keep feature logic
  reusable.
* **Repository (Data Layer):** Acts as the single source of truth and hides local/remote data
  details from the UI and domain layers.

## Project Structure

The repository is laid out as a layered, multi-module Gradle project. Each directory below maps to a
distinct slice of the architecture so features can evolve independently while keeping dependencies
explicit.

```text
├── app/                     # Android entry point and app-level configuration
├── data/                    # Repository implementations, remote clients, sync, and persistence
├── domain/                  # Business rules, app models, use cases, fixtures, and locale logic
├── di/                      # Centralized Hilt bindings that wire modules together
├── presentation/            # Compose UI, app shell, navigation, previews, and design system
├── utils/                   # Cross-cutting helpers for lifecycle, dates, and time
├── buildSrc/                # Gradle convention helpers and module constants
└── gradle/, *.gradle.kts    # Build logic, settings, and version configuration
```

### Module Overview

Every Gradle module has a single responsibility. Use the list below to find the code you need:

* **Application shell**
    * `app`: Hosts the `Application`, splash activity, and app-level configuration.
    * `presentation:app-shell`: Owns the bottom-bar shell and top-level screen composition.
* **Presentation layer**
    * `presentation:explore`, `presentation:favourites`, `presentation:search`,
      `presentation:roller-coaster-details`, `presentation:settings`, and `presentation:about-me`:
      Feature-specific Compose screens.
    * `presentation:design-system:*`: Shared Compose foundations, reusable components, previews,
      maps, images, and component-specific snapshot tests.
    * `presentation:navigation` and `presentation:navigation-external`: Navigation contracts and
      app-facing navigation integration.
    * `presentation:paparazzi`, `presentation:previews`, `presentation:fixtures`,
      `presentation:format`, `presentation:string-provider`, `presentation:top-bars`, and
      `presentation:utils`: Shared UI tooling, resources, and formatting helpers.
* **Domain layer**
    * `domain:model`, `domain:roller-coasters`, `domain:settings`, `domain:system-features`,
      `domain:locales`, and `domain:fixtures`: Canonical models, use cases, and test fixtures.
* **Data layer**
    * `data:roller-coasters`: Room cache, remote API mapping, sync scheduling, and repository
      implementation for coaster data.
    * `data:network`: Ktor client configuration and API result handling.
    * `data:settings`: Persistence for user preferences and configuration toggles.
    * `data:system-features`: Abstractions over device and system capabilities.
* **Dependency injection**
    * `di`: Shared Hilt modules and component wiring consumed across the app.
* **Shared utilities**
    * `utils:lifecycle` and `utils:time-dates`: Lifecycle-aware coroutine helpers and date/time
      utilities.

## Screenshots

### Light Theme

<p align="center">
   <img src="https://github.com/user-attachments/assets/1d66db52-e2d5-4a5b-af38-9dfd4201cff2" alt="Explore screen in light theme" width="24%"/>
   <img src="https://github.com/user-attachments/assets/723cf128-155c-4ed2-9dae-f694d5bf8d33" alt="Roller coaster details screen in light theme" width="24%"/>
   <img src="https://github.com/user-attachments/assets/b155a20a-e8d2-4047-8ff2-b804b0abf548" alt="Settings screen in light theme" width="24%"/>
   <img src="https://github.com/user-attachments/assets/cc648cd7-0ec9-4bf4-8e05-9ae1818cf3aa" alt="About screen in light theme" width="24%"/>
</p>

### Dark Theme

<p align="center">
   <img src="https://github.com/user-attachments/assets/dba6e3bb-e418-4aa2-8191-67947dd55db9" alt="Explore screen in dark theme" width="24%"/>
   <img src="https://github.com/user-attachments/assets/92d0a3e9-a25f-4fca-88c8-821cf5875f7e" alt="Roller coaster details screen in dark theme" width="24%"/>
   <img src="https://github.com/user-attachments/assets/1a9840ee-9294-4e96-911d-f33e572c3ce9" alt="Settings screen in dark theme" width="24%"/>
   <img src="https://github.com/user-attachments/assets/e8f79eab-7e5e-4d75-b6d6-156b96df1dce" alt="About screen in dark theme" width="24%"/>
</p>

## Contributing

This is a personal playground, but focused improvements, bug reports, and ideas are welcome.

See [CONTRIBUTING.md](CONTRIBUTING.md) for the contribution workflow and pull request expectations.

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT) - see
the [LICENSE](LICENSE) file for details.

## Contact

If you feel like saying hi, have any comments, suggestions or
questions, [open an issue](https://github.com/Sottti/RollerCoasters/issues?q=sort%3Aupdated-desc+is%3Aissue+is%3Aopen)
or say [hi on X](https://x.com/Sotttti).
