package com.example.globegatherer;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

import android.widget.TextView;
@RunWith(AndroidJUnit4.class)
public class travelHistoryTest {

    @Rule
    public ActivityTestRule<travelHistory> activityRule = new ActivityTestRule<>(travelHistory.class);

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
    public void testAddPlaceButton() {
        // Type text in the destination EditText
        onView(withId(R.id.destination)).perform(replaceText("New York, NY, USA"));

        // Click the "Add" button
        onView(withId(R.id.addPlace)).perform(click());

        // Wait for the network call to finish
        IdlingRegistry.getInstance().register(countingIdlingResource);
        try {
            // Check if the response TextView contains the expected error message
            onView(withId(R.id.responses2))
                    .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                    .check(matches(isAssignableFrom(TextView.class)))
                    .check(matches(withText(containsString("Error: org.json.JSONException"))));

        } finally {
            IdlingRegistry.getInstance().unregister(countingIdlingResource);
        }
    }

    @Test
    public void testShowPlacesButton() {
        // Click the "Show Places" button
        onView(withId(R.id.showPlaces)).perform(click());

        // Wait for the network call to finish
        IdlingRegistry.getInstance().register(countingIdlingResource);
        try {
            // Perform assertions without sleeping
            onView(withId(R.id.showingPlaces))
                    .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
                    .check(matches(isAssignableFrom(TextView.class)))
                    .check(matches(withText(containsString("Error: null"))));
        } finally {
            IdlingRegistry.getInstance().unregister(countingIdlingResource);
        }
    }
}
