package com.example.keep;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GoogleKeepFlowsTest extends KeepTestBase {
    @Test
    void createAndDeleteTextNote() throws InterruptedException {
        String title = uniqueTitle();
        createNote(title);
        driver.findElement(By.xpath("//*[contains(@text,\"" + title + "\")]" )).click();
        deleteOpenNote();
        wait.until(d -> d.findElements(By.xpath("//*[contains(@text,\"" + title + "\")]" )).isEmpty());
    }

    @Test
    void searchForNote() throws InterruptedException {
        String title = uniqueTitle();
        createNote(title);
        tapAny("Search", "Search notes", "Search Keep");
        var search = wait.until(d -> d.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.widget.EditText\")")));
        search.sendKeys(title);
        assertTrue(visible(By.xpath("//*[contains(@text,\"" + title + "\")]" )).isDisplayed());
        driver.hideKeyboard();
        var closeSearch = driver.findElements(AppiumBy.id("com.google.android.keep:id/search_actionbar_back_button"));
        if (!closeSearch.isEmpty()) closeSearch.get(0).click();
        visible(noteCard(title)).click();
        visible(AppiumBy.id("com.google.android.keep:id/edit_note_text"));
        deleteOpenNote();
    }

    @Test
    void editExistingNote() throws InterruptedException {
        String title = uniqueTitle();
        String updatedTitle = title + " edited";
        createNote(title);
        visible(noteCard(title)).click();
        var titleField = visible(AppiumBy.id("com.google.android.keep:id/editable_title"));
        titleField.clear();
        titleField.sendKeys(updatedTitle);
        wait.until(d -> updatedTitle.equals(d.findElement(AppiumBy.id("com.google.android.keep:id/editable_title")).getText()));
        Thread.sleep(1000);
        driver.navigate().back(); // Hide the keyboard.
        driver.navigate().back(); // Save and leave the editor.
        visible(noteCard(updatedTitle)).click();
        var savedTitle = visible(AppiumBy.id("com.google.android.keep:id/editable_title"));
        assertTrue(savedTitle.getText().contains(updatedTitle), "Saved title was: " + savedTitle.getText());
        deleteOpenNote();
    }
}
