package com.example.kotlin.viewmodel

import android.util.Log
import com.benny.library.kbinding.annotation.Command
import com.benny.library.kbinding.annotation.Property
import com.benny.library.kbinding.viewmodel.ViewModel
import kotlin.properties.Delegates

class MainActivityViewModel() : ViewModel() {

    @delegate:Property
    var name: String by Delegates.property("hoge@example.com")

    @Command
    fun sampleButtonClick() {
        Log.d("MainActivityViewModel", "sampleButtonClick!!!")
    }

    @Command
    fun shineButtonCheckedChange(checked : Boolean) {
        Log.d("MainActivityViewModel", "shineButtonCheckedChange $checked")
    }
}

