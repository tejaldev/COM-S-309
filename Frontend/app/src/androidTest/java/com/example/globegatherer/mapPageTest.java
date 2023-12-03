package com.example.globegatherer;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.util.Checks;
import org.junit.Rule;
import org.junit.Test;

public class mapPageTest {

    // Launch the mapPage activity
    @Rule
    public ActivityScenarioRule<mapPage> activityScenarioRule =
            new ActivityScenarioRule<>(mapPage.class);

    @Test
    public void testLocationInputDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.locationInput))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testSearchButtonDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.searchButton))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
