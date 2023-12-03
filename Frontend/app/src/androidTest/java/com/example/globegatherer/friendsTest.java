package com.example.globegatherer;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import android.view.View;
import android.view.ViewTreeObserver;

@LargeTest
public class friendsTest {

    @Rule
    public ActivityScenarioRule<friends> activityRule = new ActivityScenarioRule<>(friends.class);

    @Test
    public void testFriendsContainerVisibility() {
        // Perform the click action on the specific child view
        onView(withId(R.id.showFriendButton)).perform(click());

        // Use a ViewTreeObserver to wait for the layout to be laid out
        onView(withId(R.id.friendsContainer)).check(new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException noViewFoundException) {
                final int[] location = new int[2];
                view.getLocationOnScreen(location);

                int width = view.getWidth();
                int height = view.getHeight();

                // Check if the view is visible on the screen
                boolean isViewVisible = width > 0 && height > 0;

                // Check if the view is within the screen bounds
                boolean isWithinScreenBounds = location[0] >= 0 && location[1] >= 0 &&
                        location[0] + width <= view.getRootView().getWidth() &&
                        location[1] + height <= view.getRootView().getHeight();

                assertThat("view has effective visibility <VISIBLE>", isViewVisible, is(true));
                assertThat("view is within the screen bounds", isWithinScreenBounds, is(true));
            }
        });

    }


//    @Test
//    public void testFriendsContainerVisibility() {
//        // Perform the click action on the specific child view
//        onView(withId(R.id.showFriendButton)).perform(click());
//
//        // Check if friendsContainer is displayed after the click
//        onView(withId(R.id.friendsContainer)).check(matches(isDisplayed()));
//    }

    @Test
    public void testFriendItemClick() {
        // Assuming that the friend items are displayed within a LinearLayout
        // Click on the first item in the LinearLayout
        onView(withId(R.id.friendsContainer))
                .perform(friends_ChildViewActions.clickChildViewWithId(R.id.friendName, 0));
    }
}
