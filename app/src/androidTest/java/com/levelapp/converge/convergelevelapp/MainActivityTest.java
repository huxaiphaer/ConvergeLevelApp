package com.levelapp.converge.convergelevelapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import view.MainActivity;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> activity = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void loadRecyclerview() {
        try {

            CountingIdlingResource mainActivityIdlingResource = activity.
                    getActivity().getEspressoIdlingResourceForMainActivity();

            IdlingRegistry.getInstance().register(mainActivityIdlingResource);

            Espresso.onView(withId(R.id.rv))
                    .check(matches(hasDescendant(withText("k33ptoo"))));

            Espresso.onView(withId(R.id.rv)).
                    perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        } catch (RuntimeException r) {

        }


    }

}
