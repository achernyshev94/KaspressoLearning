package com.example.kaspresso.tests

import androidx.test.rule.ActivityTestRule
import com.example.kaspresso.LoginFragment
import com.example.kaspresso.MainActivity
import com.example.kaspresso.TodoFragment
import com.example.kaspresso.extensions.checkFragmentIsDisplayed
import com.example.kaspresso.screens.CounterScreen
import com.example.kaspresso.screens.CounterScreen.checkButtons
import com.example.kaspresso.screens.CounterScreen.checkCounterValue
import com.example.kaspresso.screens.CounterScreen.checkToastVisibility
import com.example.kaspresso.screens.CounterScreen.checkTvCounter
import com.example.kaspresso.screens.CounterScreen.decreaseCounterClick
import com.example.kaspresso.screens.CounterScreen.increaseCounterClick
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class CounterTest : TestCase() {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun increaseCounterFiveTimes() = run {

        step("Проверить элементы на экране") {
            CounterScreen {
                checkTvCounter()
                checkButtons()
            }
        }
        step("Увеличить счетчик на 5") {
            CounterScreen {
                increaseCounterClick()
                increaseCounterClick()
                increaseCounterClick()
                increaseCounterClick()
                increaseCounterClick()
            }
        }
        step("Проверить значение счетчика") {
            CounterScreen {
                checkCounterValue("Counter: 5")
            }
        }
    }

    @Test
    fun increaseAndDecreaseTest() = run {
        step("Проверить элементы на экране") {
            CounterScreen {
                checkTvCounter()
                checkButtons()
            }
        }
        step("Увеличить счетчик на 3") {
            CounterScreen {
                increaseCounterClick()
                increaseCounterClick()
                increaseCounterClick()
            }
        }
        step("Уменьшить счетчик на 2") {
            CounterScreen {
                decreaseCounterClick()
                decreaseCounterClick()
            }
        }
        step("Проверить значение счетчика") {
            CounterScreen {
                checkCounterValue("Counter: 1")
            }
        }
    }

    @Test
    fun checkToastMessage() = run {
        step("Проверить элементы на экране") {
            CounterScreen {
                checkTvCounter()
                checkButtons()
            }
        }
        step("Сделать счетчик меньше 0") {
            CounterScreen {
                decreaseCounterClick()
            }
        }
        step("Проверить сообщение тоста") {
            CounterScreen {
                checkToastVisibility("Can't count lower than 0")
            }
        }
    }

    @Test
    fun checkOpenTodoScreen() = run {
        step("Проверить элементы на экране") {
            CounterScreen.checkButtons()
        }
        step("Нажать на кнопку ToDo") {
            CounterScreen.openTodoScreenClick()
        }
        step("Проверить текущий фрагмент") {
            val isTodoFragment = checkFragmentIsDisplayed<TodoFragment>()
            assert(isTodoFragment)
        }
    }

    @Test
    fun checkOpenLoginScreen() = run {
        step("Проверить элементы на экране") {
            CounterScreen.checkButtons()
        }
        step("Нажать кнопку логина") {
            CounterScreen.openLoginScreenClick()
        }
        step("Проверить текущий фрагмент") {
            val isLoginFragment = checkFragmentIsDisplayed<LoginFragment>()
            assert(isLoginFragment)
        }
    }
}
