# Google Keep Android UI automation

Appium + Java + JUnit 5 UI suite for the Google Keep Android app.

## Coverage

- Create and delete a text note
- Search for a note
- Edit a note

## Requirements

- JDK 17 or newer
- Maven 3.9 or newer
- Android SDK platform tools and a connected Android device or emulator
- Appium 2 or 3 with the UiAutomator2 driver
- Google Keep installed and signed in on the device

## Run

Start Appium at http://127.0.0.1:4723/, connect the device, then run mvn test from this directory. Set ANDROID_DEVICE_NAME if Appium does not detect the device name automatically; set APPIUM_URL to use another Appium endpoint.

See RUN-RESULT.md for the latest execution status.
