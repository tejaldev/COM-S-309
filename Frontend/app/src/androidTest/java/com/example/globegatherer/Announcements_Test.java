package com.example.globegatherer;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import org.junit.Rule;
import org.junit.Test;

@LargeTest
public class Announcements_Test {

    @Rule
    public ActivityScenarioRule<Announcements> activityRule =
            new ActivityScenarioRule<>(Announcements.class);

    @Test
    public void testUIElements() {
        // Check if the Connect button is displayed
        Espresso.onView(ViewMatchers.withId(R.id.Connect))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the Exit button is displayed
        Espresso.onView(ViewMatchers.withId(R.id.back))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the Send button is displayed
        Espresso.onView(ViewMatchers.withId(R.id.send_button))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the Announce TextView is displayed
        Espresso.onView(ViewMatchers.withId(R.id.announce))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the Announcement EditText is displayed
        Espresso.onView(ViewMatchers.withId(R.id.announce_text))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testConnectButton() {
        // Click on the Connect button
        Espresso.onView(ViewMatchers.withId(R.id.Connect)).perform(ViewActions.click());

        // Assuming your logic changes some UI state after connecting, check for that state
        // Example: Check if the Announce TextView shows a connection message
//        Espresso.onView(ViewMatchers.withId(R.id.announce))
//                .check(ViewAssertions.matches(ViewMatchers.withText("Connection established")));
    }

    @Test
    public void testExitButton() {
        // Click on the Exit button
        Espresso.onView(ViewMatchers.withId(R.id.back)).perform(ViewActions.click());

        // Assuming your logic navigates to another activity, check if the new activity is displayed
        // Example: Check if a specific view from the new activity is displayed
        Espresso.onView(ViewMatchers.withText("Admin"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testSendMessage() {
        // Type a message in the Announcement EditText
        Espresso.onView(ViewMatchers.withId(R.id.announce_text))
                .perform(ViewActions.typeText("Hello, this is a test message"), ViewActions.closeSoftKeyboard());

        // Click on the Send button
        Espresso.onView(ViewMatchers.withId(R.id.send_button)).perform(ViewActions.click());

        // Assuming your logic updates the UI after sending a message, check for that state
        // Example: Check if the Announce TextView shows the sent message
        Espresso.onView(ViewMatchers.withId(R.id.announce_text))
                .check(ViewAssertions.matches(ViewMatchers.withText("Hello, this is a test message")));
    }
}
