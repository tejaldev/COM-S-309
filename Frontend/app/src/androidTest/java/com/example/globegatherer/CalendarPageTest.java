package com.example.globegatherer;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;

public class CalendarPageTest {

    @Rule
    public ActivityScenarioRule<Calendar_Page> activityRule =
            new ActivityScenarioRule<>(Calendar_Page.class);

    @Test
    public void testCalendarPage() {
        // Set a destination in the EditText
        String testDestination = "Test Destination";
        Espresso.onView(ViewMatchers.withId(R.id.destinationEditText))
                .perform(ViewActions.replaceText(testDestination), ViewActions.closeSoftKeyboard());

        // Click on the "Select Start Date" button
        Espresso.onView(ViewMatchers.withId(R.id.startDateButton))
                .perform(ViewActions.click());

        // Perform actions to set the start date in the CalendarView
        // (You might need to customize this based on your CalendarView implementation)
        // Example: Select a date in the date picker

        // Click on the "Select End Date" button
        Espresso.onView(ViewMatchers.withId(R.id.endDateButton))
                .perform(ViewActions.click());

        // Perform actions to set the end date in the CalendarView
        // (You might need to customize this based on your CalendarView implementation)
        // Example: Select a date in the date picker

        // Click on the "Save" button
        Espresso.onView(ViewMatchers.withId(R.id.calendarButton))
                .perform(ViewActions.click());

        // Assuming your backend responds with a success message
        // Uncomment the following lines if you want to check for the success message
//        Espresso.onView(ViewMatchers.withId(R.id.Des_Response))
//                .check(ViewAssertions.matches(ViewMatchers.withText("Your success message")));

        // Add assertions to verify the UI elements in the Calendar_Page activity
        // For example, check if a success message or a new screen is displayed
    }
}