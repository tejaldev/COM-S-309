package com.example.globegatherer;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import com.example.globegatherer.MainActivity;
import com.example.globegatherer.R;

import org.junit.Rule;
import org.junit.Test;

@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testLoginButtonSuccess() {
        // Type a username and password, then click on the login button
        Espresso.onView(ViewMatchers.withId(R.id.username))
                .perform(ViewActions.typeText("admin"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.password))
                .perform(ViewActions.typeText("admin@123"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.loginButton)).perform(ViewActions.click());

        // Add assertions based on the expected behavior after clicking the login button
        // For example, you can check if a certain activity is opened, or if a message is displayed.
        // Use Espresso.onView() and ViewMatchers to locate UI elements for assertions.
        Espresso.onView(ViewMatchers.withId(R.id.Announcement))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testLoginButtonFailure() {
        // Type a wrong username and password, then click on the login button
        Espresso.onView(ViewMatchers.withId(R.id.username))
                .perform(ViewActions.typeText("wrongUsername"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.password))
                .perform(ViewActions.typeText("wrongPassword"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.loginButton)).perform(ViewActions.click());

        // Add assertions based on the expected behavior after clicking the login button
        // For example, you can check if a certain message is displayed.
        // Use Espresso.onView() and ViewMatchers to locate UI elements for assertions.
//        Espresso.onView(ViewMatchers.withId(R.id.message))
//                .check(ViewAssertions.matches(ViewMatchers.withText("Invalid credentials")));
    }

    @Test
    public void testSignUpButton() {
        // Click on the SignUp button
        Espresso.onView(ViewMatchers.withId(R.id.signupbutton)).perform(ViewActions.click());

        // Add assertions based on the expected behavior after clicking the SignUp button
        // For example, you can check if the correct activity is opened.
        // Use Espresso.onView() and ViewMatchers to locate UI elements for assertions.
        Espresso.onView(ViewMatchers.withId(R.id.SignUpName))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
