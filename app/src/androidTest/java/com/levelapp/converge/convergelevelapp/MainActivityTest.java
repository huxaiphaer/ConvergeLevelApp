package com.levelapp.converge.convergelevelapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.support.annotation.UiThread;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

            Espresso.pressBack();

            sleep();


        } catch (RuntimeException r) {

        }


    }


    @Test
    public void screenOrientation() {

        activity.
                getActivity().
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        sleep();

        Espresso.onView(withId(R.id.rv))
                .perform(RecyclerViewActions.scrollToPosition(11));

        activity
                .getActivity()
                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        sleep();

    }


    @Test
    @UiThread
    public void testOnSavedInstanceState() throws Throwable {
        activity.launchActivity(new Intent());

        final Activity act = activity.getActivity();

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                act.finish();
                act.recreate();
            }
        });
    }


    public void wifiEnabling(boolean value) {
        WifiManager wifi = (WifiManager) activity.getActivity().getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(value);


    }

    public void setMobileData(Context context, boolean enabled) throws ClassNotFoundException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final Class conmanClass = Class.forName(conman.getClass().getName());
        final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
        iConnectivityManagerField.setAccessible(true);
        final Object iConnectivityManager = iConnectivityManagerField.get(conman);
        final Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());
        final Method setMobileDataEnabledMethod = iConnectivityManagerClass
                .getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
        setMobileDataEnabledMethod.setAccessible(true);
        setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
    }


    private void sleep() {

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
