package com.example.myapplication

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
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
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        openAbout()
        Espresso.onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testBackToFragment1From2() {
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testBackToFragment2From3() {
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragment3))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test(expected = NoActivityResumedException::class)
    fun testBackStack() {
        launchActivity<MainActivity>()
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
    }

    @Test(expected = NoActivityResumedException::class)
    fun testBackStack2() {
        launchActivity<MainActivity>()
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToFirst)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
    }

    @Test(expected = NoActivityResumedException::class)
    fun testBackStack3() {
        launchActivity<MainActivity>()
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        openAbout()
        Espresso.onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment3))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
    }

    @Test(expected = NoActivityResumedException::class)
    fun testBackStack4() {
        launchActivity<MainActivity>()
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.bnToFirst)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
    }

    @Test
    fun testAboutReturnToFragment2() {
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        openAbout()
        Espresso.onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testAboutReturnToFragment3() {
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        openAbout()
        Espresso.onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment3))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testChangeOrientation() {
        val activityScenario = launchActivity<MainActivity>()

        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        activityScenario.recreate()
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        activityScenario.recreate()
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.fragment3))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        activityScenario.recreate()
        Espresso.onView(ViewMatchers.withId(R.id.fragment3))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        openAbout()
        Espresso.onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        activityRule.scenario.onActivity { activity ->
            activity.recreate()
        }
        Espresso.onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}