package com.example.kaspresso.espresso

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.kaspresso.R
import com.example.kaspresso.espresso.matchers.ToastMatcher
import org.junit.Test
import org.junit.runner.RunWith

class LoginEspressoTest: BaseEspressoTest() {

    @Test fun loginTest() {
        onView(withId(R.id.btn_open_login_screen))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.et_email))
            .check(matches(isDisplayed()))
            .perform(typeText("admin@gmail.com"))
        onView(withId(R.id.et_password))
            .check(matches(isDisplayed()))
            .perform(typeText("1234"))
            .perform(closeSoftKeyboard())

        onView(withId(R.id.btn_login))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withText("Success logged in"))
            .inRoot(ToastMatcher().apply {
                isDisplayed()
            })

    }
}