package com.example.kaspresso.tests

import com.example.kaspresso.base.BaseCaspressoTest
import com.example.kaspresso.screens.CounterScreen
import com.example.kaspresso.screens.TodoScreen
import com.example.kaspresso.screens.TodoScreen.btnSubmitClick
import com.example.kaspresso.screens.TodoScreen.checkListItemAtPosition
import com.example.kaspresso.screens.TodoScreen.checkListItemNotExist
import com.example.kaspresso.screens.TodoScreen.clearEtTitle
import com.example.kaspresso.screens.TodoScreen.typeTodoTitle
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class TodoTest : BaseCaspressoTest() {

    private val clocks = mutableListOf<String>()

    private fun getClockTime() = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

    @Test
    fun checkTodoAddedToList() {
        run {
            step("Check first screen views") {
                CounterScreen.checkButtons()
            }
            step("Open todo screen") {
                CounterScreen.openTodoScreenClick()
            }
            step("Check views visible") {
                TodoScreen.checkViews()
            }
            step("Type text and click submit button") {
                TodoScreen {
                    typeTodoTitle("first to-do item")
                    btnSubmitClick().also { clocks.add(getClockTime()) }
                    clearEtTitle()
                    typeTodoTitle("second to-do item")
                    btnSubmitClick().also { clocks.add(getClockTime()) }
                }
            }
            Thread.sleep(2000L)
            step("Check todo is added to list") {
                TodoScreen {
                    checkListItemAtPosition(0, "first to-do item", clocks[0])
                    checkListItemAtPosition(1, "second to-do item", clocks[1])
                }
            }
            Thread.sleep(2000L)
            step("Click first ToDo is done") {
                TodoScreen.clickTodoIsDone(0)
            }
            Thread.sleep(2000L)
            step("Check first ToDo was deleted") {
                TodoScreen {
                    checkListItemAtPosition(0, "second to-do item", clocks[1])
                    checkListItemNotExist(0, "first to-do item")
                }
                clocks.removeAt(0)
            }
            Thread.sleep(2000L)
        }
    }
}