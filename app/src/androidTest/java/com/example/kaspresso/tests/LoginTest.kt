package com.example.kaspresso.tests

import androidx.test.rule.ActivityTestRule
import com.example.kaspresso.CounterFragment
import com.example.kaspresso.MainActivity
import com.example.kaspresso.extensions.checkFragmentIsDisplayed
import com.example.kaspresso.screens.CounterScreen
import com.example.kaspresso.screens.LoginScreen
import com.example.kaspresso.screens.LoginScreen.checkEmail
import com.example.kaspresso.screens.LoginScreen.checkLogin
import com.example.kaspresso.screens.LoginScreen.checkPassword
import com.example.kaspresso.screens.LoginScreen.clickLoginButton
import com.example.kaspresso.screens.LoginScreen.setEmail
import com.example.kaspresso.screens.LoginScreen.setPassword
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class LoginTest : TestCase() {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkLoggedIn() = run {
        step("Открыть экран Авторизации") {
            CounterScreen.openLoginScreenClick()
        }
        step("Проверитб элементы на экране") {
            LoginScreen {
                checkEmail()
                checkLogin()
                checkPassword()
            }
        }
        step("Ввести логин и пароль") {
            LoginScreen {
                setEmail("admin@gmail.com")
                setPassword("1234")
            }
        }
        step("Авторизоваться") {
            LoginScreen {
                closeSoftKeyboard()
                clickLoginButton()
            }
        }
        step("Проверить, что юзер авторизовался") {
            LoginScreen.checkLoginIsSucceded()
        }
        val isCounterFragment = checkFragmentIsDisplayed<CounterFragment>()
        assert(isCounterFragment)
    }
}
