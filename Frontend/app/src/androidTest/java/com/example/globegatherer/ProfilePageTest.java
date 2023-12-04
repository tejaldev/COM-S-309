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
public class ProfilePageTest {

    @Rule
    public ActivityScenarioRule<profile_page> activityRule =
            new ActivityScenarioRule<>(profile_page.class);

    @Test
    public void testProfilePage() throws InterruptedException {
        // Assuming your backend responds with a success message
        // Uncomment the following lines if you want to check for the success message
//        Espresso.onView(ViewMatchers.withId(R.id.Des_Response))
//                .check(ViewAssertions.matches(ViewMatchers.withText("Your success message")));

        // Test entering and submitting a description
        String testDescription = "This is a test description.";
        Espresso.onView(ViewMatchers.withId(R.id.Description))
                .perform(ViewActions.replaceText(testDescription), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.edit))
                .perform(ViewActions.click());

        // Assuming your backend responds with a success message
//        Espresso.onView(ViewMatchers.withId(R.id.Des_Response))
//                .check(ViewAssertions.matches(ViewMatchers.withText("Your success message")));

        // Add assertions to verify the UI elements in the profile_page activity
        Espresso.onView(ViewMatchers.withId(R.id.Profile_userame))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Test clicking on the "Logout" button
        Espresso.onView(ViewMatchers.withId(R.id.logout))
                .perform(ViewActions.click());

        // Sleep for 5 seconds to wait for MainActivity to launch
        Thread.sleep(5000);

// Add assertions to verify that the MainActivity is launched after clicking Logout
        Espresso.onView(ViewMatchers.withId(R.id.logout))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Test clicking on the "Ratings" button
        Espresso.onView(ViewMatchers.withId(R.id.ratings))
                .perform(ViewActions.click());

        // Add assertions to verify that the Ratings activity is launched after clicking Ratings
        Espresso.onView(ViewMatchers.withId(R.id.ratingbar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
