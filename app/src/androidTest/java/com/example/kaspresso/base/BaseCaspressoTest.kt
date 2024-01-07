package com.example.kaspresso.base

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.common.builders.ViewBuilder
import com.agoda.kakao.common.views.KView
import com.example.kaspresso.MainActivity
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule

open class BaseCaspressoTest : TestCase(Kaspresso.Builder.simple()) {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    protected inline fun <reified T : Fragment> checkFragmentIsDisplayed() =
        activityTestRule.activity.supportFragmentManager.fragments.firstOrNull {
            it is T
        } != null
}