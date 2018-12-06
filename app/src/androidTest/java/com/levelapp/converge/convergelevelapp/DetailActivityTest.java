package com.levelapp.converge.convergelevelapp;


import android.support.test.espresso.Espresso;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import view.DetailsActivity;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;


@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    @Rule
    public ActivityTestRule<DetailsActivity> activity = new ActivityTestRule<>(DetailsActivity.class);


    @Test
    public void loadDeveloperName() {

        try {
            Espresso.onView(withId(R.id.username_details_txt))
                    .check(matches(not(isDisplayed())));
            Espresso.onView(withId(R.id.action_share))
                    .perform(click());
        } catch (RuntimeException e) {

        }

    }


}
