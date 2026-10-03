# Google Keep Appium run report

**Status: NOT RUN.** No test pass/fail result is claimed.

| Item | Result |
|---|---|
| App | Google Keep for Android (`com.google.android.keep`) |
| Suite | 3 UI flows: create/delete note, search note, edit note |
| Device | Oppo CPH2467 is connected and authorized in ADB. ADB commands needed by Appium currently fail in this execution environment while resolving the host `.android` directory (`Cannot mkdir '\\.android'`). Keep's installation on the device is therefore unverified. |
| Maven | Not installed; download attempts were blocked by the environment's Windows network credential handling. |
| Appium | Appium and UiAutomator2 driver were installed locally in the scratch workspace, but device automation could not start while ADB is failing. |
| Execution | Not started; no test cases ran. |
| Loom recording | Not created; no app run to record. Loom remains on its sign-in page in the browser. |
| GitHub | Destination repository: https://github.com/DebadattaDash/iOS_Assignment. The suite is being added in a subfolder; publication is pending a successful authenticated push. |

To run locally, install Maven 3.9+, ensure Android SDK/ADB is reachable, install Appium with the UiAutomator2 driver, install Keep and sign in, then run `mvn test` from this project folder.
