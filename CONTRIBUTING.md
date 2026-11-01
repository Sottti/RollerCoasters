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

## Weekly verification policy

Android CI and both Dependabot ecosystems run once weekly, Friday at 03:17 in
`Europe/Madrid`. The [Actions schedule timezone](https://docs.github.com/en/actions/reference/workflows-and-actions/workflow-syntax#onschedule)
preserves local time across DST. Scheduled Actions use the latest `develop` revision and GitHub may delay admission.
Android CI also supports explicit manual dispatch:

```bash
gh workflow run ci.yml --repo Sottti/RollerCoasters --ref develop
```

PR creation, updates, pushes and merges must not automatically start Actions or
CI, including managed workflows or indirect callers. Do not add PR, push,
comment, merge-group, workflow-run or automatic reusable-workflow callers.
Do not require `Build` or `JVM Tests` statuses on PRs, add dummy successful
checks, or restore the retired per-PR check policy. PR review, completed
conversation resolution, force-push/deletion safeguards and all local checks
above still apply. A weekly result does not verify an individual PR; regressions
can be discovered after merge.

Both retained jobs run for schedule and manual events without PR payload
conditions. Build validates the wrapper, module boundaries, detekt/lint and
assembly; JVM Tests depends on Build and runs JVM/Kover coverage followed by
Paparazzi without `excludeSnapshotTests`. Existing reports upload on failure.
Keep JDKs, caches, Maps secret injection and coverage settings intact.

Dependabot automatic rebasing is disabled. PRs opened before the change can
continue rebasing for 30 days, so rollout must reconcile those existing PRs.
Do not silently reopen stale branches with automatic workflow definitions.

Use the owner's existing GitHub Actions failure-email notifications; verify the
recipient and Actions email/failures-only preferences in GitHub notification
settings. A failed job, a skipped downstream job and a billing/runner admission
failure are different outcomes. Inspect run details and artifacts, fix the
cause, then explicitly dispatch recovery if needed. Do not add automatic retry
loops, paid notification services, or change billing/subscriptions.

Rollout evidence and outstanding runtime proof are tracked in
[issue #170](https://github.com/Sottti/RollerCoasters/issues/170). Manual success
is not scheduled proof. Keep the first genuine Friday outcome pending until
observed. Shared defaults and restoration instructions must agree with
[Sottti/.github #13](https://github.com/Sottti/.github/issues/13) before declaring
rollout complete.
