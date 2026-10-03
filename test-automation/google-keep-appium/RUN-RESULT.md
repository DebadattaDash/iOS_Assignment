# Google Keep Appium run report

**Android UI status: PARTIAL PASS — 2 of 3 UI tests passed in isolated runs.**

| Check | Result |
|---|---|
| App | Google Keep for Android (`com.google.android.keep`) from Google Play |
| Suite | Java + Appium + JUnit 5: create/delete a text note, search for a note, and edit a note |
| Device | OnePlus CPH2467, Android 15 |
| `createAndDeleteTextNote` | **PASS** — 1 test, 0 failures, 0 errors, 0 skipped; 44.56 seconds |
| `searchForNote` | **PASS** — 1 test, 0 failures, 0 errors, 0 skipped; 34.59 seconds |
| `editExistingNote` | **FAIL** — updated title was not found in the note list after returning from the editor |
| Combined invocation | Appium socket hang-up interrupted the second session; the two passing checks were rerun separately |
| GitHub Actions | Existing workflow is compile-only; it does not run Android UI tests |
| Loom recording | Not uploaded |

See [RUN-RESULT-2-UI-TESTS.md](RUN-RESULT-2-UI-TESTS.md) for the isolated run details. Run each UI test on a connected Android device with Appium running.