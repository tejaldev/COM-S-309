package com.example.globegatherer;

import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

public class friends_ChildViewActions {
    public static ViewAction clickChildViewWithId(final int childId, final int position) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(View.class);
            }

//            @Override
//            public void perform(UiController uiController, View view) {
//
//            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View childView = view.findViewById(childId);

                if (childView != null) {
                    // Ensure the child view is not null before performing click
                    childView.performClick();
                }
            }
        };
    }
}
