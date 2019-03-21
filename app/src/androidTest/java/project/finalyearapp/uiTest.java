package project.finalyearapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import project.finalyearapp.Common.Common;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

//@RunWith(AndroidJUnit4.class)
@LargeTest
public class uiTest {
    public static final String FIRST_TO_BE_TYPED = "expresso";
    public static final String LAST_TO_BE_TYPED = "test";
    public static final String newEMAIL_TO_BE_TYPED = "autotest@test.com";
    public static final String PASSWORD_TO_BE_TYPED = "password";
    public static final String COMPANY_TO_BE_TYPED = "company";
    public static final String ADDRESS_TO_BE_TYPED = "address";
    public static final String BANK_TO_BE_TYPED = "bank";
    public static final String custEMAIL_TO_BE_TYPED = "custB@test.com";
    public static final String shopAEMAIL_TO_BE_TYPED = "shopA@test.com";
    public static final String shopBEMAIL_TO_BE_TYPED = "shopB@test.com";
    public static final String shopPASSWORD_TO_BE_TYPED = "1234";

    private static final int ITEM_TO_CLICK = 0;


    @Rule public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);


    @Before
    public void initialiseInit() {
        Intents.init();
    }

    @Test
    public void register() {
        // Change to Register page
        onView(withId(R.id.btnRegister)).perform(click());
        // Type text and then press the button.
        onView(withId(R.id.edtFirstName)).perform(typeText(FIRST_TO_BE_TYPED),
                closeSoftKeyboard());
        onView(withId(R.id.edtLastName)).perform(typeText(LAST_TO_BE_TYPED),
                closeSoftKeyboard());
        onView(withId(R.id.edtEmail)).perform(typeText(newEMAIL_TO_BE_TYPED),
                closeSoftKeyboard());
        onView(withId(R.id.edtPassword)).perform(typeText(PASSWORD_TO_BE_TYPED),
                closeSoftKeyboard());
        onView(withId(R.id.radio_shop)).perform(click());
        onView(withId(R.id.edtCompanyName)).perform(typeText(COMPANY_TO_BE_TYPED),
                closeSoftKeyboard());
        onView(withId(R.id.edtAddress)).perform(typeText(ADDRESS_TO_BE_TYPED),
                closeSoftKeyboard());
        onView(withId(R.id.edtBank)).perform(typeText(BANK_TO_BE_TYPED),
                closeSoftKeyboard());
        onView(withId(R.id.btnRegister)).perform(click());

        // Check if creation worked/on welcome page
        intended(hasComponent(Welcome.class.getName()));
    }

    @Test
    public void login() {
        // Change to LogIn page
        onView(withId(R.id.btnLogin)).perform(click());
        // Type text and then press the button.
        onView(withId(R.id.edtEmail)).perform(typeText(newEMAIL_TO_BE_TYPED),
                closeSoftKeyboard());
        onView(withId(R.id.edtPassword)).perform(typeText(PASSWORD_TO_BE_TYPED),
                closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        // Check if log in worked/on welcome page
        intended(hasComponent(Welcome.class.getName()));
    }

    /*
    @Test
    public void shopApprove() {
        // Change to LogIn page
        onView(withId(R.id.btnLogin)).perform(click());
        // Type text and then press the button.
        onView(withId(R.id.edtEmail)).perform(typeText(shopAEMAIL_TO_BE_TYPED),
                closeSoftKeyboard());
        onView(withId(R.id.edtPassword)).perform(typeText(shopPASSWORD_TO_BE_TYPED),
                closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        // Move from welcome page to Transaction Request Page
        onView(withId(R.id.btnMvRequest)).perform(click());

        // Click On transaction and click approve
        onView(ViewMatchers.withId(R.id.recycler_menu)).perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_TO_CLICK, click()));
        onView(withId(R.id.approve)).perform(click());

        onView(withId(R.id.recycler_menu)
                .atPositionOnView(ITEM_TO_CLICK, R.id.recycler_menu))
                .check(matches(isDisplayed()));
    }*/

    @After
    public void releaseInit() {
        Intents.release();
    }
}

