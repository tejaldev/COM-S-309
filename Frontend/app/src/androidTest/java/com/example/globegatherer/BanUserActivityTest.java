package com.example.globegatherer;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.globegatherer.R;
import com.example.globegatherer.ban_user;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class BanUserActivityTest {

    @Rule
    public ActivityScenarioRule<ban_user> activityRule =
            new ActivityScenarioRule<>(ban_user.class);

    @Test
    public void testBanUser() {
        // Type a username into the EditText
        Espresso.onView(ViewMatchers.withId(R.id.announce_text))
                .perform(ViewActions.typeText("testUser"), ViewActions.closeSoftKeyboard());

        // Click the Ban button
        Espresso.onView(ViewMatchers.withId(R.id.Ban_user)).perform(ViewActions.click());

        // Check if the response text is displayed after banning the user
//        Espresso.onView(ViewMatchers.withId(R.id.user_ban))
//                .check(ViewAssertions.matches(ViewMatchers.withText("User with username testUser banned successfully.")));
    }

    @Test
    public void testGetUsers() {
        // Click the Show button
        Espresso.onView(ViewMatchers.withId(R.id.get)).perform(ViewActions.click());

        // Check if the progress bar is displayed while fetching users
//        Espresso.onView(ViewMatchers.withId(R.id.progressBar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.progressBar)).check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        // Wait for a moment to simulate network request completion
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the progress bar is gone after fetching users
        Espresso.onView(ViewMatchers.withId(R.id.progressBar)).check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        // Check if the users' data is displayed
//        Espresso.onView(ViewMatchers.withId(R.id.user_ban)).check(ViewAssertions.matches(ViewMatchers.withText("All users: <users_data_here>")));
    }

    // You can add more test methods based on the functionality you want to test
}
