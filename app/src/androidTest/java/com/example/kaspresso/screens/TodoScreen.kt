package com.example.kaspresso.screens

import android.view.View
import com.agoda.kakao.check.KCheckBox
import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.example.kaspresso.R
import com.example.kaspresso.TodoAdapter.TodoViewHolder
import com.example.kaspresso.TodoFragment
import org.hamcrest.Matcher

object TodoScreen : BaseScreen<TodoScreen>() {
    override val layoutId: Int = R.layout.fragment_todo
    override val viewClass: Class<*> = TodoFragment::class.java

    private val etTodoTitle = KEditText {
        withId(R.id.et_todo)
    }

    private val btnSubmit = KButton {
        withId(R.id.btn_submit)
    }

    private val rvTodo = KRecyclerView(
        builder = {
            withId(R.id.rv_todo)
        },
        itemTypeBuilder = {
            itemType(TodoScreen::TodoItem)
        }
    )

    class TodoItem(parent: Matcher<View>) : KRecyclerItem<TodoViewHolder>(parent) {
        val tvTitle = KTextView(parent) { withId(R.id.tv_title) }
        val tvClock = KTextView(parent) { withId(R.id.tv_clock) }
        val cbIsDone = KCheckBox(parent) { withId(R.id.cb_is_done) }
    }

    fun checkViews() {
        etTodoTitle {
            isDisplayed()
            hasText("")
        }
        btnSubmit.isVisible()
    }

    fun btnSubmitClick() {
        btnSubmit.click()
    }

    fun typeTodoTitle(text: String) {
        etTodoTitle.typeText(text)
    }

    fun clearEtTitle() {
        etTodoTitle.clearText()
    }

    fun checkListItemAtPosition(position: Int, text: String, time: String) {
        rvTodo {
            childAt<TodoItem>(position) {
                tvTitle.hasText(text)
                tvClock.hasText(time)
            }
        }
    }

    fun checkListItemNotExist(position: Int, text: String) {
        rvTodo {
            childAt<TodoItem>(position) {
                tvTitle.hasNoText(text)
            }
        }
    }

    fun clickTodoIsDone(position: Int) {
        rvTodo {
            childAt<TodoItem>(position) { cbIsDone.click() }
        }
    }
}