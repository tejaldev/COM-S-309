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
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
public class chatPageTest {

    @Rule
    public ActivityScenarioRule<chatpage> activityRule = new ActivityScenarioRule<>(chatpage.class);

    @Test
    public void testConnectButton() {
        // Check if the connect button is displayed
        Espresso.onView(ViewMatchers.withId(R.id.bt1)).check(matches(isDisplayed()));

        // Perform a click on the connect button
        Espresso.onView(ViewMatchers.withId(R.id.bt1)).perform(ViewActions.click());

        // Check if the status message TextView is displayed
        Espresso.onView(ViewMatchers.withId(R.id.statusTextView)).check(matches(isDisplayed()));

        // You may add additional assertions based on the behavior of your app after connecting
    }

    @Test
    public void testSendButton() {
        // Check if the send button is displayed
        Espresso.onView(ViewMatchers.withId(R.id.sendButton)).check(matches(isDisplayed()));

        // Perform a click on the send button
        Espresso.onView(ViewMatchers.withId(R.id.sendButton)).perform(ViewActions.click());

        // Check if the message input EditText is displayed
        Espresso.onView(ViewMatchers.withId(R.id.messageInput)).check(matches(isDisplayed()));

        // You may add additional assertions based on the behavior of your app after sending a message
    }

    @Test
    public void testSendMessage() {
        // Connect and send a message for testing
        Espresso.onView(ViewMatchers.withId(R.id.bt1)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.sendButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.messageInput)).perform(ViewActions.typeText("Hello, this is a test message"));
        Espresso.onView(ViewMatchers.withId(R.id.sendButton)).perform(ViewActions.click());

        // Check if the message is displayed in the chat container
        Espresso.onView(withText("Hello, this is a test message")).check(matches(isDisplayed()));
    }

    @Test
    public void testChatContainerVisibility() {
        // Check if the chat container is initially visible
        Espresso.onView(ViewMatchers.withId(R.id.chatContainer)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        // Connect to the chat
        Espresso.onView(ViewMatchers.withId(R.id.bt1)).perform(ViewActions.click());

        // Check if the chat container is still visible after connecting
        Espresso.onView(ViewMatchers.withId(R.id.chatContainer)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }


    @Test
    public void testUsernameEditText() {
        // Check if the username EditText is displayed
        Espresso.onView(ViewMatchers.withId(R.id.et1)).check(matches(isDisplayed()));

        // Perform a typeText action on the username EditText
        Espresso.onView(ViewMatchers.withId(R.id.et1)).perform(ViewActions.typeText("TestUser"));

        // You may add additional assertions based on the behavior of your app with the username
    }

    @Test
    public void testMessageInputEditText() {
        // Check if the message input EditText is displayed
        Espresso.onView(ViewMatchers.withId(R.id.messageInput)).check(matches(isDisplayed()));

        // Perform a typeText action on the message input EditText
        Espresso.onView(ViewMatchers.withId(R.id.messageInput)).perform(ViewActions.typeText("Test message"));

        // You may add additional assertions based on the behavior of your app with the entered message
    }

    // Add more test methods as needed to cover other functionalities of your chatpage class
}
