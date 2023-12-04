package com.example.globegatherer;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import com.example.globegatherer.Activity2_login_next;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

@LargeTest
public class Activity2_SignUp_Test {

    @Rule
    public ActivityScenarioRule<Activity2_login_next> activityRule =
            new ActivityScenarioRule<>(Activity2_login_next.class);

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

        // Assuming your backend responds with a success message
//        Espresso.onView(ViewMatchers.withId(R.id.Response))
//                .check(ViewAssertions.matches(ViewMatchers.withText("{\"message\":\"success\"}")));

        Thread.sleep(5000);

        Espresso.onView(ViewMatchers.withId(R.id.SignUpButton))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        // Add assertions to verify the user's existence in the backend database
        // You may want to make a request to the backend to check the user's data
        // and assert that it matches the data you used for registration.
        // Note: Be careful about asserting against live data to avoid privacy and security issues.
    }
}
