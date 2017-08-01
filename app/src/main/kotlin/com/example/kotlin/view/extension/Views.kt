package com.example.kotlin.view.extension

import android.content.Context
import android.view.View
import android.view.ViewManager
import com.example.kotlin.view.component.RedButtonComponent
import com.example.kotlin.view.widget.SampleButton
import com.sackcentury.shinebuttonlib.ShineButton
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.sampleButton(theme: Int = 0, init: SampleButton.() -> Unit): SampleButton {
    return ankoView({ ctx: Context -> SampleButton(ctx) }, theme) { init() }
}

inline fun ViewManager.redButtonComponent(theme: Int = 0, init: View.(redButtonComponent: RedButtonComponent) -> Unit): View {
    val redButtonComponent = RedButtonComponent()
    return ankoView({ redButtonComponent.createView(AnkoContext.create(it)) }, theme, { init(redButtonComponent) })
}

inline fun ViewManager.shineButton(theme: Int = 0, init: ShineButton.() -> Unit): ShineButton {
    return ankoView({ ctx: Context -> ShineButton(ctx) }, theme) { init() }
}
