package com.example.keep;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GoogleKeepFlowsTest extends KeepTestBase {
    @Test
    void createAndDeleteTextNote() {
        String title = uniqueTitle();
        createNote(title, "Created by the Google Keep Appium suite");
        driver.findElement(By.xpath("//*[contains(@text,\"" + title + "\")]" )).click();
        deleteOpenNote();
        wait.until(d -> d.findElements(By.xpath("//*[contains(@text,\"" + title + "\")]" )).isEmpty());
    }

    @Test
    void searchForNote() {
        String title = uniqueTitle();
        createNote(title, "Search flow body");
        tapAny("Search", "Search notes", "Search Keep");
        var search = wait.until(d -> d.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.widget.EditText\")")));
        search.sendKeys(title);
        assertTrue(visible(By.xpath("//*[contains(@text,\"" + title + "\")]")).isDisplayed());
        driver.navigate().back();
        driver.findElement(By.xpath("//*[contains(@text,\"" + title + "\")]" )).click();
        deleteOpenNote();
    }

    @Test
    void editExistingNote() {
        String title = uniqueTitle();
        createNote(title, "Original body");
        driver.findElement(By.xpath("//*[contains(@text,\"" + title + "\")]" )).click();
        var editor = visible(By.xpath("//*[contains(@text,'Original body') or @hint='Note']"));
        editor.click();
        editor.clear();
        editor.sendKeys("Updated body");
        driver.navigate().back();
        driver.findElement(By.xpath("//*[contains(@text,\"" + title + "\")]" )).click();
        assertTrue(visible(By.xpath("//*[contains(@text,'Updated body')]")).isDisplayed());
        deleteOpenNote();
    }
}


