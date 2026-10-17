# Contributing

Use the [shared contribution and title guidance](https://github.com/Sottti/.github/blob/main/CONTRIBUTING.md)
and the matching [shared issue or pull request template](https://github.com/Sottti/.github/tree/main/.github).
The shared templates remain the source for GitHub submissions.

## Workflow

1. Create a focused branch from `develop`.
2. Keep changes small enough to review.
3. Run the checks relevant to the change using JDK 21 and the checked-in Gradle wrapper.
4. Describe the resulting behavior, verification and any remaining limitations in the shared PR template.

## Local checks

For broad changes, run these commands separately. The test-exclusion property must not be passed to snapshot verification:

```bash
./gradlew checkModuleDependencyBoundaries
./gradlew detekt lintDebug
./gradlew test :koverXmlReport -PexcludeSnapshotTests=true
./gradlew --no-parallel verifyPaparazziDebug
./gradlew assembleDebug
```

The coverage report aggregates Android and Kotlin modules; no minimum coverage threshold is configured.
See [the README](README.md#getting-started) for SDK, Maps-key and device setup.
