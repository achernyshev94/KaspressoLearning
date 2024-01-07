package com.example.kaspresso.extensions

import androidx.fragment.app.Fragment
import androidx.test.rule.ActivityTestRule
import com.example.kaspresso.MainActivity
import org.junit.Rule

@get:Rule
val activityTestRule = ActivityTestRule(MainActivity::class.java)

inline fun <reified T : Fragment> checkFragmentIsDisplayed() =
    activityTestRule.activity.supportFragmentManager.fragments.firstOrNull {
        it is T
    } != null