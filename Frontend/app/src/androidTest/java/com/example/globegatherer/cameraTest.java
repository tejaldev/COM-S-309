package com.example.globegatherer;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@LargeTest
public class cameraTest {

    @Rule
    public ActivityScenarioRule<camera> activityRule = new ActivityScenarioRule<>(camera.class);

    @Test
    public void testCaptureButton() {
        // Check if the capture button is displayed
        Espresso.onView(ViewMatchers.withId(R.id.captureButton)).check(matches(isDisplayed()));

        // Perform a click on the capture button
        Espresso.onView(ViewMatchers.withId(R.id.captureButton)).perform(ViewActions.click());

        // You may add additional assertions based on the behavior of your app after capturing a photo
    }

    @Test
    public void testUploadButton() {
        // Check if the upload button is displayed
        Espresso.onView(ViewMatchers.withId(R.id.uploadButton)).check(matches(isDisplayed()));

        // Perform a click on the upload button
        Espresso.onView(ViewMatchers.withId(R.id.uploadButton)).perform(ViewActions.click());

        // You may add additional assertions based on the behavior of your app after uploading an image
    }

    // Add more test methods as needed to cover other functionalities of your camera class
}
