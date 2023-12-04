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

        // Wait for the friendsContainer to be displayed
        onView(withId(R.id.friendsContainer)).check(matches(isDisplayed()));
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
