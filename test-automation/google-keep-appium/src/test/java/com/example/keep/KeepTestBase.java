package com.example.keep;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.time.Duration;
import java.util.UUID;

abstract class KeepTestBase {
    protected AndroidDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    void startKeep() throws Exception {
        String endpoint = System.getenv().getOrDefault("APPIUM_URL", "http://127.0.0.1:4723/");
        String device = System.getenv().getOrDefault("ANDROID_DEVICE_NAME", "Android Emulator");
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName(device)
                .setAppPackage("com.google.android.keep")
                .setAppActivity(".activities.BrowseActivity")
                .setNoReset(true)
                .setNewCommandTimeout(Duration.ofSeconds(120));
        driver = new AndroidDriver(URI.create(endpoint).toURL(), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterEach
    void stopKeep() {
        if (driver != null) driver.quit();
    }

    protected String uniqueTitle() {
        return "Appium check " + UUID.randomUUID().toString().substring(0, 8);
    }

    protected WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void tapAny(String... labels) {
        for (String label : labels) {
            var matches = driver.findElements(AppiumBy.accessibilityId(label));
            if (!matches.isEmpty()) { matches.get(0).click(); return; }
            matches = driver.findElements(By.xpath("//*[@text='" + label + "' or @content-desc='" + label + "']"));
            if (!matches.isEmpty()) { matches.get(0).click(); return; }
        }
        throw new AssertionError("Could not find any of: " + String.join(", ", labels));
    }

    protected void createNote(String title, String body) {
        tapAny("New note", "Create new note", "New text note");
        visible(By.xpath("//*[@text='Title' or @hint='Title']")).sendKeys(title);
        visible(By.xpath("//*[@text='Note' or @hint='Note']")).sendKeys(body);
        driver.navigate().back(); // First back hides the keyboard.
        driver.navigate().back(); // Second back leaves the editor and saves the note.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,\"" + title + "\")]")));
    }

    protected void deleteOpenNote() {
        tapAny("More", "More options");
        tapAny("Delete", "Delete note");
    }
}
