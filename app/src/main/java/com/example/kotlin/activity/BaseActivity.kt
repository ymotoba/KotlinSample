package com.example.kotlin.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.benny.library.kbinding.bind.BindingDelegate
import com.benny.library.kbinding.bind.BindingDisposer
import com.benny.library.kbinding.view.BindingDisposerGenerator
import com.example.kotlin.viewmodel.ActivityViewModel
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import kotlin.reflect.KClass

open class BaseActivity : RxAppCompatActivity(), BindingDisposerGenerator, BindingDelegate {
    override lateinit var viewModel: ActivityViewModel
    override val bindingDisposer: BindingDisposer = BindingDisposer()

    override fun onDestroy() {
        super.onDestroy()
        bindingDisposer.unbind()
    }

    fun <T : Activity> Activity.startActivity(classRef: KClass<T>, bundle: Bundle? = null) {
        val intent = Intent(this, classRef.java).setAction(Intent.ACTION_VIEW)
        bundle?.let {
            intent.putExtra("args", bundle)
        }
        startActivity(intent)
    }

    protected fun bindViewModel(activityViewModel: ActivityViewModel) {
        this.viewModel = activityViewModel
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }
}