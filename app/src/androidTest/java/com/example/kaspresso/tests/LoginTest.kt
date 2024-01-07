package com.example.kaspresso.tests

import com.example.kaspresso.CounterFragment
import com.example.kaspresso.base.BaseCaspressoTest
import com.example.kaspresso.screens.CounterScreen
import com.example.kaspresso.screens.LoginScreen
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.DEFAULT)
class LoginTest : BaseCaspressoTest() {

    @Test
    fun checkSuccessLogin() {
        run {
            scenario(openLoginScenario)
            step("check views and type valid credentials") {
                LoginScreen.apply {
                    checkViews()
                    setEmail("admin@gmail.com")
                    setPassword("1234")
                }
            }
            scenario(
                tryToLoginAndCheckIsLoggedIn(
                    description = "check login is success",
                    loginChecker = LoginScreen::checkLoggedIn
                )
            )
            val isCounterFragment = checkFragmentIsDisplayed<CounterFragment>()
            assert(isCounterFragment)
        }
    }

    @Test
    fun checkInvalidCredentials() {
        run {
            scenario(openLoginScenario)
            step("check views and type invalid credentials") {
                LoginScreen.apply {
                    checkViews()
                    setEmail("asdasjf")
                    setPassword("asfadvajhv")
                }
            }
            scenario(
                tryToLoginAndCheckIsLoggedIn(
                    description = "check credentials is invalid",
                    loginChecker = LoginScreen::checkCredentialsInvalid
                )
            )
        }
    }

    @Test
    fun checkLoginFailed() {
        run {
            scenario(openLoginScenario)
            step("check views and type not recognized credentials") {
                LoginScreen.apply {
                    checkViews()
                    setEmail("asdasd@gfg.vsa")
                    setPassword("asfqf")
                }
            }
            scenario(
                tryToLoginAndCheckIsLoggedIn(
                    description = "check login is failed",
                    loginChecker = LoginScreen::checkLoginFailed
                )
            )
        }
    }

    private val openLoginScenario = object : Scenario() {
        override val steps: TestContext<Unit>.() -> Unit
            get() = {
                step("open login screen") {
                    CounterScreen.btnOpenLogin.click()
                }
            }

    }

    private fun tryToLoginAndCheckIsLoggedIn(description: String, loginChecker: () -> Unit) =
        object : Scenario() {
            override val steps: TestContext<Unit>.() -> Unit
                get() = {
                    step("try to login") {
                        LoginScreen.clickLogin()
                    }
                    step(description) {
                        loginChecker()
                    }
                }

        }
}