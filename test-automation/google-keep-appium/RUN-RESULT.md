# Google Keep Appium run report

**Android UI status: NOT RUN.** No UI-test pass or fail is claimed.

| Check | Result |
|---|---|
| App | Google Keep for Android (\`com.google.android.keep\`) |
| UI flows | Create/delete a note, search for a note, and edit a note |
| GitHub compile check | **PASS** — GitHub Actions run [#1](https://github.com/DebadattaDash/iOS_Assignment/actions/runs/37104463644) completed \`mvn test-compile\` successfully. This confirms the Java test suite compiles; it does not execute the Android UI tests. |
| Device | Oppo CPH2467 is listed as authorized in ADB, but Appium's ADB commands fail in this execution environment while resolving the host Android profile (\`Cannot mkdir '\\\\.android'\`). Keep installation on the phone remains unverified. |
| Local Maven | Not available in this execution environment. |
| Loom video | Not created: no Android UI run was possible, and Loom remains at its sign-in page. |

To execute the UI flows, run \`mvn test\` from this project on a host where ADB can access the authorized device, Maven 3.9+, Appium 2 or 3 with UiAutomator2, and Google Keep is installed and signed in.
