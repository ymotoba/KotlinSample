package com.example.kotlin.view.component

import android.content.Context
import android.view.View
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.button
import org.jetbrains.anko.toast

class RedButtonComponent() : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        button {
            text = "赤いボタンだよ"
            setOnClickListener { toast("HaHaHaHa!!!!!") }
        }
    }
}