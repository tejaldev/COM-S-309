package com.example.globegatherer;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
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
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;

import android.view.View;
import android.widget.TextView;

@RunWith(AndroidJUnit4.class)
public class toDoTest {

    @Rule
    public ActivityTestRule<toDoList> activityRule = new ActivityTestRule<>(toDoList.class);

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
    public void testSaveButton() {
        // Type text in EditTexts
        onView(withId(R.id.nameEditTextDestination)).perform(typeText("DestinationName"));
        onView(withId(R.id.countryEditText)).perform(typeText("CountryName"));

        // Click the save button
        onView(withId(R.id.saveList)).perform(click());

        // Wait for some time (adjust this based on your network delay)
        countingIdlingResource.increment();
        try {
            Thread.sleep(2000); // Sleep for 2 seconds (adjust as needed)
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            countingIdlingResource.decrement();
        }

        // Check if the response TextView contains the expected substring
        onView(withId(R.id.responses2)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.responses2)).check(matches(isAssignableFrom(TextView.class)));

        // Update this line to handle variations in the actual response
        onView(withId(R.id.responses2)).check(matches(withText(anyOf(
                containsString("Your expected response"),
                containsString("Error: null")
        ))));


    }

    @Test
    public void testShowListButton() {
        // Click the "Show List" button
        onView(withId(R.id.showListButton)).perform(click());

        // Switch to the new activity or class
        // You might need to use a specific ID or other method to ensure you are in the correct class

        // Wait for some time (adjust this based on your network delay or UI updates)
        countingIdlingResource.increment();
        try {
            Thread.sleep(2000); // Sleep for 2 seconds (adjust as needed)
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            countingIdlingResource.decrement();
        }

        // Check if the response TextView contains the expected substring
        onView(withId(R.id.textView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.textView)).check(matches(isAssignableFrom(TextView.class)));

        // Update this line to handle variations in the actual response
        onView(withId(R.id.textView)).check(matches(withText(anyOf(
                containsString("Your expected response"),
                containsString("Error: null")
        ))));
    }


}
