package com.example.kaspresso.tests

import com.example.kaspresso.CounterFragment
import com.example.kaspresso.base.BaseCaspressoTest
import com.example.kaspresso.screens.CounterScreen
import com.example.kaspresso.screens.LoginScreenLesson
import org.junit.Test

class LoginTestLesson: BaseCaspressoTest() {

    @Test
    fun checkLoggedIn() {
        run {
            step("open login screen") {
                CounterScreen.openLoginScreenClick()
            }
            step("check views") {
                LoginScreenLesson.btnLogin.isDisplayed()
                LoginScreenLesson.etEmail.isDisplayed()
                LoginScreenLesson.etPassword.isDisplayed()
            }
            step("type credentials") {
                LoginScreenLesson.apply {
                    setEmail("admin@gmail.com")
                    setPassword("1234")
                }
            }
            step("try to login") {
                LoginScreenLesson.closeSoftKeyboard()
                LoginScreenLesson.btnLogin.click()
            }
            step("check is logged in") {
                LoginScreenLesson.checkLoginIsSucceded()
            }
            val isCounterFragment = checkFragmentIsDisplayed<CounterFragment>()
            assert(isCounterFragment)
        }
    }
}