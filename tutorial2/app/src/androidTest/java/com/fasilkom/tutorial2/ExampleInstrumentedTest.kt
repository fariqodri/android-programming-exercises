package com.fasilkom.tutorial2

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.runner.AndroidJUnit4
import android.support.test.rule.ActivityTestRule
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText


import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.fasilkom.tutorial2", appContext.packageName)
    }

    @Test
    fun testBiodataShown() {
        onView(withId(R.id.name_input)).perform(typeText("Fari Qodri Andana"), closeSoftKeyboard())
        onView(withId(R.id.npm_input)).perform(typeText("1606875964"), closeSoftKeyboard())
        onView(withId(R.id.email_input)).perform(typeText("qori@example.com"), closeSoftKeyboard())
        onView(withId(R.id.submit_button)).perform(click())

        onView(withId(R.id.name_display)).check(matches(withText("Fari Qodri Andana")))
        onView(withId(R.id.npm_display)).check(matches(withText("1606875964")))
        onView(withId(R.id.email_display)).check(matches(withText("qori@example.com")))
    }
}
