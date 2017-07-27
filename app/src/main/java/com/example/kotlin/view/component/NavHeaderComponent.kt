package com.example.kotlin.view.component

import android.support.design.widget.NavigationView
import android.view.Gravity.START
import android.view.View
import com.example.kotlin.R
import org.jetbrains.anko.*

class NavHeaderComponent : AnkoComponent<NavigationView> {
    override fun createView(ui: AnkoContext<NavigationView>): View = with(ui) {
        verticalLayout {
            verticalPadding = dimen(R.dimen.activity_vertical_margin)
            horizontalPadding = dimen(R.dimen.activity_horizontal_margin)
            backgroundResource = R.drawable.side_nav_bar
            imageView(android.R.drawable.sym_def_app_icon) {
                topPadding = dimen(R.dimen.nav_header_vertical_spacing)
            }.lparams {
                gravity = START
            }
            textView("Android Studio") {
                topPadding = dimen(R.dimen.nav_header_vertical_spacing)
            }
            textView("android.studio@android.com")
        }
    }
}