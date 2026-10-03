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
        // ADB can take longer than its 20-second default to install UiAutomator2
        // on a physical device, especially on the first run or over a slow USB link.
        options.setCapability("appium:uiautomator2ServerInstallTimeout", 120_000);
        options.setCapability("appium:adbExecTimeout", 120_000);
        String udid = System.getenv("ANDROID_SERIAL");
        if (udid != null && !udid.isBlank()) {
            options.setUdid(udid);
        }
        driver = new AndroidDriver(URI.create(endpoint).toURL(), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.activateApp("com.google.android.keep");
        wait.until(d -> "com.google.android.keep".equals(driver.getCurrentPackage()));
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
        throw new AssertionError("Could not find any of: " + String.join(", ", labels)
                + "\n" + screenSummary());
    }

    protected void createNote(String title, String body) {
        var createButtons = driver.findElements(AppiumBy.id(
                "com.google.android.keep:id/speed_dial_create_close_button"));
        if (!createButtons.isEmpty()) {
            createButtons.get(0).click();
        } else {
            tapAny("Create a note", "New note", "Create new note", "New text note");
        }
        var textNoteButtons = driver.findElements(By.xpath(
                "//*[@clickable='true' and contains(@resource-id,'new_note_button')]"));
        if (!textNoteButtons.isEmpty()) {
            textNoteButtons.get(0).click();
        } else {
            tapAny("New text note", "Text");
        }
        visible(By.xpath("//*[@text='Title' or @hint='Title']")).sendKeys(title);
        visible(By.xpath("//*[@text='Note' or @hint='Note']")).sendKeys(body);
        driver.navigate().back(); // First back hides the keyboard.
        driver.navigate().back(); // Second back leaves the editor and saves the note.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,\"" + title + "\")]")));
    }

    protected void deleteOpenNote() {
        tapAny("Action", "More", "More options");
        tapAny("Delete", "Delete note");
    }

    private String screenSummary() {
        StringBuilder summary = new StringBuilder("Current app: ");
        try {
            summary.append(driver.getCurrentPackage()).append(" / ").append(driver.currentActivity());
            summary.append("\nClickable controls:");
            var controls = driver.findElements(By.xpath("//*[@clickable='true']"));
            int count = 0;
            for (WebElement control : controls) {
                if (count++ == 30) break;
                String text = control.getText();
                String description = control.getAttribute("content-desc");
                String id = control.getAttribute("resource-id");
                if ((text != null && !text.isBlank()) || (description != null && !description.isBlank())) {
                    summary.append("\n- text=").append(text).append(", desc=").append(description)
                            .append(", id=").append(id);
                }
            }
        } catch (Exception e) {
            summary.append(" (unable to inspect current screen: ").append(e.getMessage()).append(")");
        }
        return summary.toString();
    }
}






