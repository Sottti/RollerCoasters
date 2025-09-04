# Instructions
- Data layer modules provide data sources and should avoid Android dependencies.
- Use interfaces to abstract data access for easier testing.
- Include unit tests for repositories and mappers.

# Programmatic checks
- ./gradlew :data:<module>:test
