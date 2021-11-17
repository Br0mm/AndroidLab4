package com.example.myapplication

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoActivityResumedException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testAbout() {
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        openAbout()
        onView(ViewMatchers.withId(R.id.activity_about)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testBackToFragment1From2() {
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testBackToFragment2From3() {
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test(expected = NoActivityResumedException::class)
    fun testBackStack() {
        launchActivity<MainActivity>()
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        pressBack()
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
    }

    @Test(expected = NoActivityResumedException::class)
    fun testBackStack2() {
        launchActivity<MainActivity>()
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToFirst)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
    }

    @Test(expected = NoActivityResumedException::class)
    fun testBackStack3() {
        launchActivity<MainActivity>()
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        openAbout()
        onView(ViewMatchers.withId(R.id.activity_about)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
    }

    @Test(expected = NoActivityResumedException::class)
    fun testBackStack4() {
        launchActivity<MainActivity>()
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(R.id.bnToFirst)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
    }

    @Test(expected = NoActivityResumedException::class)
    fun testAboutReturnToFragment2() {
        launchActivity<MainActivity>()
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        openAbout()
        onView(ViewMatchers.withId(R.id.activity_about)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
    }

    @Test(expected = NoActivityResumedException::class)
    fun testAboutReturnToFragment3() {
        launchActivity<MainActivity>()
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        openAbout()
        onView(ViewMatchers.withId(R.id.activity_about)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
    }

    @Test
    fun testChangeOrientation() {
        val activityScenario = launchActivity<MainActivity>()

        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        activityScenario.recreate()
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        activityScenario.recreate()
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        activityScenario.recreate()
        onView(ViewMatchers.withId(R.id.fragment3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        openAbout()
        onView(ViewMatchers.withId(R.id.activity_about)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        activityRule.scenario.onActivity { activity ->
            activity.recreate()
        }
        onView(ViewMatchers.withId(R.id.activity_about)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test(expected = NoActivityResumedException::class)
    fun navigateUpFromFragment2() {
        launchActivity<MainActivity>()
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .check(ViewAssertions.doesNotExist())
        pressBack()
    }

    @Test
    fun navigateUpFromFragment3() {
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun navigateUpFromFragmentAbout() {
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        openAbout()
        onView(ViewMatchers.withId(R.id.activity_about)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun backstackWithNavigateUp() {
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        openAbout()
        onView(ViewMatchers.withId(R.id.activity_about)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment3)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .check(ViewAssertions.doesNotExist())
    }

    @Test(expected = NoActivityResumedException::class)
    fun backstackWithNavigateUp2() {
        launchActivity<MainActivity>()
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToFirst)).perform(ViewActions.click())
        onView(ViewMatchers.withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .check(ViewAssertions.doesNotExist())
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToFirst)).perform(ViewActions.click())
        onView(ViewMatchers.withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .check(ViewAssertions.doesNotExist())
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        pressBack()
    }
}