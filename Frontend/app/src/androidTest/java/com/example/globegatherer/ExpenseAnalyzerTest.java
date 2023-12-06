package com.example.globegatherer;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.click;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

@RunWith(AndroidJUnit4ClassRunner.class)
public class ExpenseAnalyzerTest {

    @Rule
    public ActivityScenarioRule<Expense_analyzer> activityRule = new ActivityScenarioRule<>(Expense_analyzer.class);

    @Test
    public void testSaveButton() {
        // Type text into the destination edit text
        onView(withId(R.id.nameDestination)).perform(typeText("Test Destination"), ViewActions.closeSoftKeyboard());

        // Select an item in the spinner
        onView(withId(R.id.costItemsSpinner)).perform(ViewActions.click());
        onView(withText("Food")).perform(ViewActions.click());
//        onData(allOf(is(instanceOf(String.class)), is("Food"))).perform(click());


        // Type text into the estimated cost edit text
        onView(withId(R.id.estimatedCost)).perform(typeText("100"), ViewActions.closeSoftKeyboard());

        // Click on the save expenses button
        onView(withId(R.id.saveExpenses)).perform(click());

        // Check if the response TextView is displayed and contains the expected text
        onView(withId(R.id.responses2)).check(matches(isDisplayed()));
        onView(withId(R.id.responses2)).check(matches(withText(startsWith("The response is: ")))); // You might need to adjust this based on the actual response you expect
    }

    @Test
    public void testShowExpenseButton() {
        // Click on the show expense button
        onView(withId(R.id.show_expense)).perform(click());

        //check with backend on what will be returned.
        // Check if the showing lists TextView is displayed and contains the expected text
        onView(withId(R.id.showingLists)).check(matches(isDisplayed()));
        onView(withId(R.id.showingLists)).check(matches(withText(startsWith("Expenses:")))); // You might need to adjust this based on the actual response you expect
    }
}

