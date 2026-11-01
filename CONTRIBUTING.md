# Contributing

Use the [shared contribution and title guidance](https://github.com/Sottti/.github/blob/develop/CONTRIBUTING.md)
and the matching [shared issue or pull request template](https://github.com/Sottti/.github/tree/develop/.github).
The shared templates remain the source for GitHub submissions.

## Workflow

1. Create a focused branch from `develop`.
2. Keep changes small enough to review.
3. Run the checks relevant to the change using JDK 21 and the checked-in Gradle wrapper. Also install
   JDK 17 for compilation, as described in the [setup requirements](README.md#requirements).
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

## PR and manual verification policy

Android CI runs on every pull request, including drafts and updates, and allows
explicit manual dispatch from `develop`. There is no CI cron schedule and no
push or post-merge run. Superseded runs are cancelled per PR; different PRs do
not cancel one another.

```bash
gh workflow run ci.yml --repo Sottti/RollerCoasters --ref develop
```

This is an owner-approved PR-CI exception for this public repository. Use
`pull_request`, not privileged `pull_request_target`, comment commands or indirect
caller chains. Inspect the actual `Build` and `JVM Tests` check results. Do not
add required CI status gates or alter unrelated protection. Local verification,
independent review and completed conversation resolution still apply.

Build validates the wrapper, module boundaries, detekt/lint and assembly. JVM
Tests depends on Build and runs JVM/Kover coverage followed by Paparazzi without
`excludeSnapshotTests`. Preserve both jobs, failure behavior and report uploads,
along with the existing JDKs, caches, Maps-key injection and coverage settings.
Fork and Dependabot PRs do not receive repository secrets; CI writes an empty
Maps key in that context, which is sufficient to compile and run these checks.

Dependabot version updates remain weekly on Friday at 03:17 Europe/Madrid,
with automatic rebasing disabled. This dependency-update cadence is separate
from CI and is unchanged. Keep vulnerability alerts, the dependency graph and
existing security rules. Automatic Dependabot security-fix PR generation remains
disabled; request security remediation manually.

Use the owner's existing GitHub Actions failure-email notifications. A failed
job, skipped downstream job and runner/billing admission failure are different
outcomes. Inspect run details and artifacts, fix the cause, then explicitly
dispatch recovery if needed. Do not add automatic retry loops or paid services.
Local and manual results apply to their tested revision and do not replace the
actual PR checks.

Keep [shared defaults](https://github.com/Sottti/.github/blob/develop/branch-protection/README.md)
aligned with this exception; other repositories retain their approved cadence.
