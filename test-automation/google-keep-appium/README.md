# Google Keep Android UI automation

Appium + Java + JUnit 5 tests for the Google Keep Android app listed on Google Play: https://play.google.com/store/apps/details?id=com.google.android.keep

## Coverage

- Create and verify a text note
- Search for the note
- Edit the note
- Delete the note

The tests use a uniquely generated note title and remove the note during cleanup. They require a signed-in Google account in the emulator because Keep synchronizes notes.

## Requirements

- JDK 17+
- Maven 3.9+
- Android SDK platform tools and an Android emulator or device
- Appium 2 or 3 with the UiAutomator2 driver
- Google Keep installed on the device and signed in

## Run

Start Appium (`appium`) and connect the device (`adb devices`). Set `ANDROID_DEVICE_NAME` to the device name from `adb devices -l` if Appium does not detect it automatically.

```sh
mvn test
```

The suite expects the app package `com.google.android.keep` and starts its launcher activity. The default Appium endpoint is `http://127.0.0.1:4723/`; override with `APPIUM_URL`.

## Results

The create-and-delete flow passed on a physical Android device. The search and edit flows are implemented but have not yet been confirmed in passing device runs. See [RUN-RESULT.md](RUN-RESULT.md) for the exact status.