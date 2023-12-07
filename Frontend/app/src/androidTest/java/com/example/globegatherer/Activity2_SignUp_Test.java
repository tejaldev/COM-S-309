package com.example.globegatherer;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import com.example.globegatherer.Activity2_login_next;

import org.junit.Rule;
import org.junit.Test;

@LargeTest
public class Activity2_SignUp_Test {

    @Rule
    public ActivityScenarioRule<Activity2_login_next> activityRule =
            new ActivityScenarioRule<>(Activity2_login_next.class);

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule2 =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void testSignUpButton() throws InterruptedException {
        // Type data into the input fields and click on the SignUp button
        Espresso.onView(ViewMatchers.withId(R.id.SignUpName))
                .perform(ViewActions.typeText("John Doe"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.SignUpUsername))
                .perform(ViewActions.typeText("john_doe"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.SignUpPassword))
                .perform(ViewActions.typeText("password123"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.SignUpEmail))
                .perform(ViewActions.typeText("john.doe@example.com"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.SignUpPhoneNo))
                .perform(ViewActions.typeText("1234567890"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.SignUpButton)).perform(ViewActions.click());


        // Wait for 5 seconds for the view to appear
        Thread.sleep(5000);


        // Assuming your backend opens the main activity after successful sign-up
        // Verify that the main activity is launched
        Espresso.onView(ViewMatchers.withId(R.id.loginButton))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Add appropriate synchronization methods instead of Thread.sleep()
        // For example, you can use Espresso's IdlingResource or waitForIdleSync().
        // This ensures that the test waits until the UI is idle before proceeding.
        // IdlingResource idlingResource = /* create IdlingResource */;
        // Espresso.registerIdlingResources(idlingResource);
        // Espresso.unregisterIdlingResources(idlingResource);

        // Add assertions to further verify the state of the main activity if needed
    }
}
