package com.example.globegatherer;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class HomePageTest {

    @Before
    public void setUp() {
        // Launch the home page activity
        ActivityScenario.launch(homePage.class);
    }

    @Test
    public void testHomePageElements() {
        // Check if the profile button is displayed and click it
        onView(withId(R.id.profile_button)).check(matches(isDisplayed())).perform(ViewActions.click());

//

        // Add more test methods for specific interactions as needed

    }
}
