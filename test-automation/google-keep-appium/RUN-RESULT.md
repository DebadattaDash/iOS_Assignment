# Google Keep Appium run report

**Android UI status: PARTIAL PASS — 1 test passed; 2 tests were not included in the recorded run.**

| Check | Result |
|---|---|
| App | Google Keep for Android (`com.google.android.keep`) from Google Play |
| Suite | Java + Appium + JUnit 5; create/delete a text note, search for a note, and edit a note |
| Device | Oppo CPH2467, Android 15 |
| Passing UI test | `createAndDeleteTextNote`: **PASS** — 1 test, 0 failures, 0 errors, 0 skipped; Surefire reports 22.007 seconds. |
| Other UI tests | `searchForNote` and `editExistingNote` are included in the suite but were not run in this passing single-test command. |
| GitHub Actions | **PASS** — compile-only workflow run [#3](https://github.com/DebadattaDash/iOS_Assignment/actions/runs/37104721602). It compiles the suite but does not run Android UI tests. |
| Loom recording | Not yet uploaded. |

Run all three UI tests with `mvn test` while Appium is running and `adb devices -l` shows the phone as `device`. The latest local Surefire result is under `target/surefire-reports`.

