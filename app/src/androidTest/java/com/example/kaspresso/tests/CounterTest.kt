package com.example.kaspresso.tests

import com.example.kaspresso.LoginFragment
import com.example.kaspresso.TodoFragment
import com.example.kaspresso.base.BaseCaspressoTest
import com.example.kaspresso.screens.CounterScreen
import com.example.kaspresso.screens.CounterScreen.checkButtons
import com.example.kaspresso.screens.CounterScreen.checkCounterValue
import com.example.kaspresso.screens.CounterScreen.checkToastVisibility
import com.example.kaspresso.screens.CounterScreen.checkTvCounter
import com.example.kaspresso.screens.CounterScreen.decreaseCounterClick
import com.example.kaspresso.screens.CounterScreen.increaseCounterClick
import org.junit.Test

class CounterTest : BaseCaspressoTest() {

    @Test
    fun increaseCounterFiveTimes() {
        run {
            step("Check views is displayed") {
                CounterScreen {
                    checkTvCounter()
                    checkButtons()
                }
            }
            step("icrease counter by 5") {
                CounterScreen {
                    increaseCounterClick()
                    increaseCounterClick()
                    increaseCounterClick()
                    increaseCounterClick()
                    increaseCounterClick()
                }
            }
            step("assert counter value") {
                CounterScreen {
                    checkCounterValue("Counter: 5")
                }
            }
        }
    }

    @Test
    fun increaseAndDecreaseTest() {
        run {
            step("Check views is displayed") {
                CounterScreen {
                    checkTvCounter()
                    checkButtons()
                }
            }
            step("Increase counter 3 times") {
                CounterScreen {
                    increaseCounterClick()
                    increaseCounterClick()
                    increaseCounterClick()
                }
            }
            step("Decrease counter 2 times") {
                CounterScreen {
                    decreaseCounterClick()
                    decreaseCounterClick()
                }
            }
            step("assert counter value") {
                CounterScreen {
                    checkCounterValue("Counter: 1")
                }
            }
        }
    }

    @Test
    fun checkToastMessage() {
        run {
            step("Check views is displayed") {
                CounterScreen {
                    checkTvCounter()
                    checkButtons()
                }
            }
            step("decrease lower than 0") {
                CounterScreen {
                    decreaseCounterClick()
                }
            }
            step("check toast message") {
                CounterScreen {
                    checkToastVisibility("Can't count lower than 0")
                }
            }
        }
    }

    @Test
    fun checkOpenTodoScreen() {
        run {
            step("Check buttons is displayed") {
                CounterScreen.checkButtons()
            }
            step("Click open todo screen button") {
                CounterScreen.openTodoScreenClick()
            }
            step("Verify destination is TodoFragment") {
                val isTodoFragment = checkFragmentIsDisplayed<TodoFragment>()
                assert(isTodoFragment)
            }
        }
    }

    @Test
    fun checkOpenLoginScreen() {
        run {
            step("check buttons is displayed") {
                CounterScreen.checkButtons()
            }
            step("click open login screen button") {
                CounterScreen.openLoginScreenClick()
            }
            step("Verify destination is LoginFragment") {
                val isLoginFragment = checkFragmentIsDisplayed<LoginFragment>()
                assert(isLoginFragment)
            }
        }
    }
}