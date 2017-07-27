package com.example.kotlin.view.component

import android.support.v4.view.GravityCompat.START
import android.support.v4.widget.DrawerLayout
import android.view.View
import com.example.kotlin.R
import com.example.kotlin.activity.BaseActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.support.v4.drawerLayout

class NavDrawerComponent : AnkoComponent<BaseActivity>, AnkoLogger {

    lateinit var drawer: DrawerLayout

    override fun createView(ui: AnkoContext<BaseActivity>): View = with(ui) {
        drawer = drawerLayout {
            fitsSystemWindows = true
            navigationView {
                fitsSystemWindows = true
                val headerContext = AnkoContext.create(ctx, this)
                val headerView = NavHeaderComponent()
                        .createView(headerContext)
                addHeaderView(headerView)
                inflateMenu(R.menu.activity_main_drawer)
            }.lparams(height = matchParent) {
                gravity = START
            }

            if (isInEditMode) {
                openDrawer(START)
            }
        }
        drawer
    }
}