package com.example.globegatherer;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AnnouncementsAdminTest {

    @Rule
    public ActivityScenarioRule<Announcements_admin> activityScenarioRule =
            new ActivityScenarioRule<>(Announcements_admin.class);

    @Test
    public void testConnectButton() {
        // Wait for the "Connect" button to be displayed (adjust the wait time as needed)
        try {
            Thread.sleep(2000); // 2 seconds wait time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Perform the click action on the "Connect" button
        Espresso.onView(ViewMatchers.withId(R.id.Connect))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click());

        // You may want to add additional assertions or checks after clicking the button
    }

    @Test
    public void testExitButton() {
        // Wait for the "Exit" button to be displayed (adjust the wait time as needed)
        try {
            Thread.sleep(2000); // 2 seconds wait time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Perform the click action on the "Exit" button
        Espresso.onView(ViewMatchers.withId(R.id.back))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click());
    }

    // Add more tests as needed
}
