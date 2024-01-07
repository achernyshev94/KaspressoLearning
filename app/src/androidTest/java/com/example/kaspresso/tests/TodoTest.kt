package com.example.kaspresso.tests

import androidx.test.rule.ActivityTestRule
import com.example.kaspresso.MainActivity
import com.example.kaspresso.screens.CounterScreen
import com.example.kaspresso.screens.TodoScreen
import com.example.kaspresso.screens.TodoScreen.btnSubmitClick
import com.example.kaspresso.screens.TodoScreen.checkListItemAtPosition
import com.example.kaspresso.screens.TodoScreen.checkListItemNotExist
import com.example.kaspresso.screens.TodoScreen.clearEtTitle
import com.example.kaspresso.screens.TodoScreen.typeTodoTitle
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class TodoTest : TestCase() {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    private val clocks = mutableListOf<String>()

    private fun getClockTime() = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

    @Test
    fun checkTodoAddedToListTest() = run {
        step("Проверить, что на экране отображаются все элементы") {
            CounterScreen.checkButtons()
        }
        step("Открыть экран заметок") {
            CounterScreen.openTodoScreenClick()
        }
        step("Проверить, что на экране отображаются все элементы") {
            TodoScreen.checkViews()
        }
        step("Добавить заметку") {
            TodoScreen {
                typeTodoTitle("first to-do item")
                btnSubmitClick().also { clocks.add(getClockTime()) }
                clearEtTitle()
                typeTodoTitle("second to-do item")
                btnSubmitClick().also { clocks.add(getClockTime()) }
            }
        }
        step("Проверить, что заметка добавлена") {
            TodoScreen {
                flakySafely(2500L) {
                    checkListItemAtPosition(0, "first to-do item", clocks[0])
                    checkListItemAtPosition(1, "second to-do item", clocks[1])
                }
            }
        }
        step("Отметить первую заметку выполненной") {
            TodoScreen.clickTodoIsDone(0)
        }
        step("Проверить, что заметка удалена") {
            TodoScreen {
                flakySafely(2000L) {
                    checkListItemAtPosition(0, "second to-do item", clocks[1])
                    checkListItemNotExist(0, "first to-do item")
                }
            }
            clocks.removeAt(0)
        }
    }
}
