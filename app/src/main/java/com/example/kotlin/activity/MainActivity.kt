package com.example.kotlin.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.view.GravityCompat
import android.support.v7.widget.Toolbar
import android.util.Log
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
import com.example.kotlin.view.component.TitleToolBarView
import com.example.kotlin.view.extension.redButtonComponent
import com.example.kotlin.view.extension.sampleButton
import com.example.kotlin.viewmodel.MainActivityViewModel
import com.example.kotlin.viewmodel.`MainActivityViewModel$$KB`.k_name
import com.example.kotlin.viewmodel.`MainActivityViewModel$$KB`.k_sampleButtonClick
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.support.v4.drawerLayout
import org.jetbrains.anko.support.v4.nestedScrollView
import kotlin.reflect.KClass


class MainActivity : BaseActivity() {

    lateinit var toolBar: Toolbar
    var mainActivityViewModel: MainActivityViewModel = MainActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this).bindTo(mainActivityViewModel)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        toolBar.setNavigationIcon(android.R.drawable.sym_def_app_icon)
        toolBar.setNavigationOnClickListener {
            Log.d("MainActivity", "NavigationOnClick!!!")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_settings -> {
            onBackPressed(); true
        }
        else -> super.onOptionsItemSelected(item)
    }

    fun <T : Activity> Activity.startActivity(classRef: KClass<T>, bundle: Bundle? = null) {
        val intent = Intent(this, classRef.java).setAction(Intent.ACTION_VIEW)
        bundle?.let {
            intent.putExtra("args", bundle)
        }
        startActivity(intent)
    }

    inner class MainActivityUI : ViewBinderComponent<MainActivity> {
        override fun builder(): AnkoContext<out MainActivity>.() -> Unit = {
            drawerLayout {
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
                        }
                    }.lparams(matchParent, matchParent) {
                        behavior = AppBarLayout.ScrollingViewBehavior()
                    }
                }.lparams(matchParent, matchParent)
                navigationView {
                    fitsSystemWindows = true
                    inflateHeaderView(R.layout.nav_header_main)
                    inflateMenu(R.menu.activity_main_drawer)
                }.lparams(wrapContent, matchParent, GravityCompat.START)
            }
        }
    }
}
