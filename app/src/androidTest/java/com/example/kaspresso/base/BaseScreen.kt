package com.example.kaspresso.base

import com.agoda.kakao.common.builders.ViewBuilder
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.screen.Screen
import com.kaspersky.kaspresso.screens.KScreen

abstract class BaseScreen<T: KScreen<*>> : KScreen<BaseScreen<T>>() {

    protected fun getToast(text: String, action: ViewBuilder.() -> Unit) = KView {
        withText(text)
        action()
    }
}