package com.example.kaspresso.screens

import com.agoda.kakao.common.builders.ViewBuilder
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.text.KButton
import com.example.kaspresso.LoginFragment
import com.example.kaspresso.R
import com.example.kaspresso.base.BaseScreen
import com.kaspersky.kaspresso.screens.KScreen

object LoginScreen: BaseScreen<LoginScreen>() {
    override val layoutId: Int = R.layout.fragment_login
    override val viewClass: Class<*> = LoginFragment::class.java

    val etEmail = KEditText {
        withId(R.id.et_email)
    }

    val etPassword = KEditText {
        withId(R.id.et_password)
    }

    val btnLogin = KButton {
        withId(R.id.btn_login)
    }

    fun checkViews() {
        etEmail { isVisible() }
        etPassword { isVisible() }
        btnLogin { isVisible() }
    }

    fun checkLoggedIn() {
        getToast("Success logged in") {
            isVisible()
        }
    }

    fun checkLoginFailed() {
        getToast("Login failed") {
            isVisible()
        }
    }

    fun checkCredentialsInvalid() {
        getToast("Credentials is invalid") {
            isVisible()
        }
    }

    fun clickLogin() {
        closeSoftKeyboard()
        btnLogin.click()
    }

    fun setEmail(text: String) {
        etEmail.typeText(text)
    }

    fun setPassword(text: String) {
        etPassword.typeText(text)
    }
}