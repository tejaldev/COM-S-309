package com.example.globegatherer;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.example.globegatherer.R;
import com.example.globegatherer.ban_user;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BanUserActivityTest {

    @Rule
    public ActivityScenarioRule<ban_user> activityRule =
            new ActivityScenarioRule<>(ban_user.class);

    @Test
    public void testBanUser() {
        // Type a username in the EditText
        Espresso.onView(ViewMatchers.withId(R.id.announce_text))
                .perform(ViewActions.typeText("testUsername"), ViewActions.closeSoftKeyboard());

        // Click the "View" button
        Espresso.onView(ViewMatchers.withId(R.id.get))
                .perform(ViewActions.click());

        // Click the "Ban" button
        Espresso.onView(ViewMatchers.withId(R.id.Ban_user))
                .perform(ViewActions.click());

        // Check if the ban result TextView is updated
        Espresso.onView(ViewMatchers.withId(R.id.user_ban))
                .check(ViewAssertions.matches(ViewMatchers.withText("User with ID testUsername deleted successfully.")));
    }

    @Test
    public void testExitButton() {
        // Wait for 2 seconds to ensure the UI is ready
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click the "Exit" button
        Espresso.onView(ViewMatchers.withId(R.id.back))
                .perform(ViewActions.click());

        // Check if the expected activity is launched after clicking the exit button
        Espresso.onView(ViewMatchers.withId(R.id.card_view1))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}



//package com.example.globegatherer;
//
//import android.util.Log;
//
//import androidx.test.espresso.Espresso;
//import androidx.test.espresso.action.ViewActions;
//import androidx.test.espresso.assertion.ViewAssertions;
//import androidx.test.espresso.matcher.ViewMatchers;
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.filters.LargeTest;
//import androidx.test.runner.AndroidJUnit4;
//
//import com.example.globegatherer.R;
//import com.example.globegatherer.ban_user;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//@RunWith(AndroidJUnit4.class)
//@LargeTest
//public class BanUserActivityTest {
//
//    @Rule
//    public ActivityScenarioRule<ban_user> activityRule =
//            new ActivityScenarioRule<>(ban_user.class);
//
//    @Test
//    public void testBanUser() {
//        // Type a username in the EditText
//        Espresso.onView(ViewMatchers.withId(R.id.announce_text))
//                .perform(ViewActions.typeText("testUsername"), ViewActions.closeSoftKeyboard());
//
//        // Click the "View" button
//        Espresso.onView(ViewMatchers.withId(R.id.get))
//                .perform(ViewActions.click());
//
//        // Ensure that the user details are displayed after clicking "View"
//        Espresso.onView(ViewMatchers.withId(R.id.get))
//                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
//
//        // Click the "Ban" button
//        Espresso.onView(ViewMatchers.withId(R.id.Ban_user))
//                .perform(ViewActions.click());
//
//        // Check if the ban result TextView is updated
//        Espresso.onView(ViewMatchers.withId(R.id.user_ban))
//                .check(ViewAssertions.matches(ViewMatchers.withText("User with ID testUsername deleted successfully.")));
//
//        // Ensure that the ban result TextView is displayed
//        Espresso.onView(ViewMatchers.withId(R.id.user_ban))
//                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
//    }
//
//    @Test
//    public void testExitButton() {
//        // Wait for 2 seconds to ensure the UI is ready
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Click the "Exit" button
//        Espresso.onView(ViewMatchers.withId(R.id.back))
//                .perform(ViewActions.click());
//
//        // Check if the expected activity is launched after clicking the exit button
//        Espresso.onView(ViewMatchers.withId(R.id.card_view1))
//                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
//    }
//
////    @Test
////    public void testBackNavigation() throws InterruptedException {
////        try {
////            // Type a username in the EditText
////            Espresso.onView(ViewMatchers.withId(R.id.announce_text))
////                    .perform(ViewActions.typeText("testUsername"), ViewActions.closeSoftKeyboard());
////
////            // Click the "View" button
////            Espresso.onView(ViewMatchers.withId(R.id.get))
////                    .perform(ViewActions.click());
////
////            // Ensure that the user details are displayed after clicking "View"
////            Espresso.onView(ViewMatchers.withId(R.id.user_ban))
////                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
////
////            // Click the system back button to navigate back
////            Espresso.pressBackUnconditionally();
////
////            // Log a message to indicate the pressBackUnconditionally is executed
////            Log.d("TestBackNavigation", "pressBackUnconditionally executed");
////
////            // Wait for a short duration to allow the activity to transition
////            Thread.sleep(1000);
////
////            // Check if the expected activity is launched after navigating back
////            Espresso.onView(ViewMatchers.withId(R.id.card_view1))
////                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
////        } catch (Exception e) {
////            // Log any exception that might occur
////            Log.e("TestBackNavigation", "Exception: " + e.getMessage(), e);
////            throw e; // rethrow the exception to fail the test
////        }
////    }
//
//}
