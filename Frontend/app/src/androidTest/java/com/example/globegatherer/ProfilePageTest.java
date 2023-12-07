package com.example.globegatherer;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

import android.widget.TextView;

@RunWith(AndroidJUnit4.class)
public class ProfilePageTest {

    @Rule
    public ActivityTestRule<profile_page> activityRule = new ActivityTestRule<>(profile_page.class);

    private final CountingIdlingResource countingIdlingResource =
            new CountingIdlingResource("Network_Call");

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(countingIdlingResource);
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(countingIdlingResource);
    }

    @Test
    public void testUpdateDescriptionSuccess() {
        // Type the new description into the EditText
        onView(withId(R.id.Description))
                .perform(typeText("New Description"), ViewActions.closeSoftKeyboard());

        // Click the Edit button
        onView(withId(R.id.edit)).perform(click());

        // Wait for the network call to finish
        IdlingRegistry.getInstance().register(countingIdlingResource);
        try {
            // Check if the Des_Response TextView displays a success message
            onView(withId(R.id.Des_Response))
                    .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                    .check(matches(isAssignableFrom(TextView.class)))
                    .check(matches(withText(containsString(""))));

        } finally {
            IdlingRegistry.getInstance().unregister(countingIdlingResource);
        }
    }


    @Test
    public void testUpdateDescriptionError() {
        // Type the new description into the EditText
        onView(withId(R.id.Description))
                .perform(typeText("New Description"), ViewActions.closeSoftKeyboard());

        // Click the Edit button
        onView(withId(R.id.edit)).perform(click());

        // Wait for the network call to finish
        IdlingRegistry.getInstance().register(countingIdlingResource);
        try {
            // Check if the Des_Response TextView displays an error message
            onView(withId(R.id.Des_Response))
                    .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                    .check(matches(isAssignableFrom(TextView.class)))
                    .check(matches(withText("")));
        } finally {
            IdlingRegistry.getInstance().unregister(countingIdlingResource);
        }
    }
}
