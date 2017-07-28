package com.example.kotlin.activity

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import com.benny.library.kbinding.common.bindings.click
import com.benny.library.kbinding.common.bindings.text
import com.benny.library.kbinding.dsl.OneWay
import com.benny.library.kbinding.dsl.bind
import com.benny.library.kbinding.dsl.inflate
import com.benny.library.kbinding.view.ViewBinderComponent
import com.benny.library.kbinding.view.setContentView
import com.example.kotlin.R
import com.example.kotlin.view.component.NavHeaderComponent
import com.example.kotlin.view.component.TitleToolBarView
import com.example.kotlin.view.extension.checkedChange
import com.example.kotlin.view.extension.redButtonComponent
import com.example.kotlin.view.extension.sampleButton
import com.example.kotlin.view.extension.shineButton
import com.example.kotlin.viewmodel.MainActivityViewModel
import com.example.kotlin.viewmodel.`MainActivityViewModel$$KB`.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.support.v4.drawerLayout
import org.jetbrains.anko.support.v4.nestedScrollView


class MainActivity : BaseActivity(), AnkoLogger {

    lateinit var toolBar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    var mainActivityViewModel: MainActivityViewModel = MainActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this).bindTo(mainActivityViewModel)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        toolBar.setNavigationIcon(android.R.drawable.sym_def_app_icon)
        toolBar.setNavigationOnClickListener {
            Log.d("MainActivity", "NavigationOnClick!!!")
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        // TODO compilation error occurs...
//            R.id.nav_camera -> verbose("-> camera")
//            R.id.nav_gallery -> debug("-> gallery")
//            R.id.nav_slideshow -> info("-> slideshow")
//            R.id.nav_manage -> warn("-> manage")
//            R.id.nav_share -> error("-> share")
//            R.id.nav_send -> wtf("-> send")
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    inner class MainActivityUI : ViewBinderComponent<MainActivity> {
        override fun builder(): AnkoContext<out MainActivity>.() -> Unit = {
            drawerLayout = drawerLayout {
                coordinatorLayout {
                    appBarLayout {
                        toolBar = inflate(TitleToolBarView("home"), this@appBarLayout) as Toolbar
                    }.lparams(matchParent, wrapContent)
                    nestedScrollView {
                        verticalLayout {
                            textView {
                                bind { text(k_name, mode = OneWay) }
                            }.lparams(matchParent, wrapContent)
                            button("テストボタン") {
                                bind { click(k_sampleButtonClick) }
                            }.lparams(matchParent, wrapContent)
                            sampleButton {
                                text = "サンプルボタン"
                            }.lparams(matchParent, wrapContent)
                            redButtonComponent() {
                            }.lparams(matchParent, wrapContent)
                            redButtonComponent() {
                            }.lparams(matchParent, wrapContent)
                            shineButton {
                                setBtnColor(Color.GRAY)
                                setBtnFillColor(Color.RED)
                                setShapeResource(R.drawable.heart)
                                bind { checkedChange(k_shineButtonCheckedChange) }
                            }.lparams(dip(40), dip(40)) {
                                gravity = Gravity.CENTER_HORIZONTAL
                            }
                        }
                    }.lparams(matchParent, matchParent) {
                        behavior = AppBarLayout.ScrollingViewBehavior()
                    }
                }.lparams(matchParent, matchParent)
                navigationView {
                    fitsSystemWindows = true
                    val headerContext = AnkoContext.create(ctx, this)
                    val headerView = NavHeaderComponent()
                            .createView(headerContext)
                    addHeaderView(headerView)
                    inflateMenu(R.menu.activity_main_drawer)
                }.lparams(wrapContent, matchParent, GravityCompat.START)
            }
        }
    }
}

//verticalLayout {
//    backgroundDrawable = ContextCompat.getDrawable(this@MainActivity, R.drawable.side_nav_bar)
//}.layoutParams = with(FrameLayout.LayoutParams(matchParent, dimen(R.dimen.nav_header_height))) {
//    this
//}