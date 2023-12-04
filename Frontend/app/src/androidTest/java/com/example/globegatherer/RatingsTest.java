package com.example.globegatherer;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.CoordinatesProvider;
import androidx.test.espresso.action.GeneralClickAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Tap;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;

public class RatingsTest {

    @Rule
    public ActivityScenarioRule<Ratings> activityRule =
            new ActivityScenarioRule<>(Ratings.class);

    @Test
    public void testRatingSubmission() {
        // Set the rating on the RatingBar
        float ratingValue = 4.0f; // Set the desired rating value
        Espresso.onView(ViewMatchers.withId(R.id.ratingbar))
                .perform(clickOnRatingBarPosition(ratingValue));

        // Type a comment in the EditText
        String comment = "This is a test comment.";
        Espresso.onView(ViewMatchers.withId(R.id.comments))
                .perform(ViewActions.replaceText(comment), ViewActions.closeSoftKeyboard());

        // Click on the "Rate Us" button
        Espresso.onView(ViewMatchers.withId(R.id.rateUsButton))
                .perform(ViewActions.click());

        // Assuming your backend responds with a success message
        // Uncomment the following lines if you want to check for the success message
//        Espresso.onView(ViewMatchers.withId(R.id.Des_Response))
//                .check(ViewAssertions.matches(ViewMatchers.withText("Your success message")));

        // Add assertions to verify the UI elements in the Ratings activity
        // For example, check if a success message or a new screen is displayed
    }

    private static ViewAction clickOnRatingBarPosition(final float position) {
        return new GeneralClickAction(
                Tap.SINGLE,
                new CoordinatesProvider() {
                    @Override
                    public float[] calculateCoordinates(View view) {
                        float[] coordinates = new float[2];
                        float width = (float) view.getWidth();
                        float height = (float) view.getHeight();
                        float percentX = position / 5.0f; // Assuming your RatingBar has a max rating of 5
                        coordinates[0] = width * percentX;
                        coordinates[1] = height / 2.0f;
                        return coordinates;
                    }
                },
                Press.FINGER);
    }
}
