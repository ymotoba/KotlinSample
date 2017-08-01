package com.example.kotlin.viewmodel;

import com.benny.library.kbinding.viewmodel.ViewModel
import com.example.kotlin.activity.BaseActivity
import com.trello.rxlifecycle.LifecycleTransformer

abstract class ActivityViewModel(val baseActivity: BaseActivity) : ViewModel() {

    fun <T> bindToLifecycle(): LifecycleTransformer<T> {
        return baseActivity.bindToLifecycle();
    }

    abstract fun onStart()

    abstract fun onResume()

    abstract fun onPause()

    abstract fun onStop()
}
