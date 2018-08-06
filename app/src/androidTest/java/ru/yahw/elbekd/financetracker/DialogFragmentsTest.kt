package ru.yahw.elbekd.financetracker

import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import org.hamcrest.Matchers.allOf
import org.junit.Test

@RunWith(AndroidJUnit4::class)

open class DialogFragmentsTest {
    private var testWalletName = "TestWallet"

    @Rule
    @JvmField
    var mIntentsRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun onAddIncome() {
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.fab_transaction)).perform(click())
        onView(withId(R.id.input_amount)).perform(typeText("100"), closeSoftKeyboard())
        onView(withId(R.id.income)).perform(click())
        onView(withId(android.R.id.button1)).perform(click())
        onView(withId(R.id.fab)).perform(click())
        onView(allOf(withId(R.id.tv_main_currency_value), isDisplayed())).check(matches(isDisplayed()))

    }

    @Test
    fun onAddOutcome() {
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.fab_transaction)).perform(click())
        onView(withId(R.id.input_amount)).perform(typeText("100"), closeSoftKeyboard())
        onView(withId(R.id.expense)).perform(click())
        onView(withId(android.R.id.button1)).perform(click())
        onView(withId(R.id.fab)).perform(click())
        onView(allOf(withId(R.id.tv_main_currency_value), isDisplayed())).check(matches(isDisplayed()))

    }

    @Test
    fun onAddWallet() {
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.fab_add_wallet)).perform(click())
        onView(withId(R.id.input_wallet_name)).perform(typeText(testWalletName), closeSoftKeyboard())
        onView(withId(android.R.id.button1)).perform(click())
        onView(withId(R.id.fab)).perform(click())
        onView(allOf(withId(R.id.tv_main_currency_value), isDisplayed())).check(matches(isDisplayed()))

    }

    @Test
    fun onAddPeriodicTransaction() {
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.fab_pereodic_transaction)).perform(click())
        onView(withId(R.id.input_amount)).perform(typeText("250"), closeSoftKeyboard())
        onView(withId(android.R.id.button1)).perform(click())
        onView(withId(R.id.fab)).perform(click())
        onView(allOf(withId(R.id.tv_main_currency_value), isDisplayed())).check(matches(isDisplayed()))

    }
}
