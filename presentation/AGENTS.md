# Instructions
- Presentation modules host UI and presentation logic.
- Compose components should be stateless when possible and expose state via parameters.
- Provide previews for composables where possible.
- Add unit tests for view models.
- Composables are tested via screenshot tests with Paparazzi.

# Programmatic checks
- ./gradlew :presentation:<module>:test