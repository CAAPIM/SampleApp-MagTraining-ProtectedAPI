package com.brcm.apim.magtraining;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.matchers.JUnitMatchers.containsString;

/* This class is used to test Storage */
@RunWith(AndroidJUnit4.class)
public class ProtectedAPITest {

    /* This method is called before the test, it will initiates Idling resource
      object */
    @Before
    public void before() {
        IdlingRegistry.getInstance().register(CountingIdlingResourceSingleton.countingIdlingResource);
    }

    /* This will launch the MainActivity */
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /*This test is used to test save functionality*/
    @Test
    public void LoginTest() throws InterruptedException {

        onView(withId(R.id.activity_mas_login_edit_text_username))
                .perform(typeText("admin"), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.activity_mas_login_edit_text_password))
                .perform(typeText("7layer"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.activity_mas_login_button_login)).perform(click());
//        Thread.sleep(8000);
        onView(withId(R.id.activity_mas_login_button_login))
                .check(matches(withText("Log in")));
    }

    /*This test is used to test save functionality*/
    @Test
    public void ProtectedEndPointTest() throws InterruptedException {

        onView(withId(R.id.protectedAPIButton)).perform(click());
        onView(withId(R.id.jsonResponseData))
                .check(matches(withText(containsString("1: Red Stapler"))));
    }

    /* This test is used to test the logout functionality */
    @Test
    public void test_UserLogout() {
        onView(withId(R.id.logoutButton)).perform(click());
        onView(withId(R.id.loginButton)).check(matches(isDisplayed()));
    }


}
