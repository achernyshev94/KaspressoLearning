package com.example.kaspresso.screens

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.text.KButton
import com.example.kaspresso.LoginFragment
import com.example.kaspresso.R

object LoginScreen : BaseScreen<LoginScreen>() {
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


    fun setEmail(text: String) {
        etEmail.typeText(text)
    }

    fun setPassword(text: String) {
        etPassword.typeText(text)
    }

    fun clickLoginButton() {
        btnLogin.click()
    }

    fun checkEmail() {
        etEmail.isDisplayed()
    }

    fun checkLogin() {
        btnLogin.isDisplayed()
    }

    fun checkPassword() {
        etPassword.isDisplayed()
    }

    fun checkLoginIsSucceded() {
        getToast("Success logged in") {
            isDisplayed()
        }
    }
}
