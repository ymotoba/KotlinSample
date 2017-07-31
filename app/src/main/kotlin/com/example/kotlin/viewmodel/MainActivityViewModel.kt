package com.example.kotlin.viewmodel

import android.util.Log
import android.view.MenuItem
import com.benny.library.kbinding.annotation.Command
import com.benny.library.kbinding.annotation.Property
import com.benny.library.kbinding.viewmodel.ViewModel
import com.example.kotlin.R
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.rx.rx_object
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.Reader
import kotlin.properties.Delegates

class MainActivityViewModel() : ViewModel() {

    // TODO ViewModelにはContext持たせる

    @delegate:Property
    var name: String by Delegates.property("hoge@example.com")

    @Command
    fun sampleButtonClick() {
        Log.d("MainActivityViewModel", "sampleButtonClick!!!")
    }

    @Command
    fun shineButtonCheckedChange(checked: Boolean) {
        Log.d("MainActivityViewModel", "shineButtonCheckedChange $checked")
    }

    @Command
    fun getSampleButtonClick() {
        Log.d("MainActivityViewModel", "getSampleButtonClick!!!")
        "http://10.0.2.2:3000/users".httpGet().rx_object(User.Deserializer())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result ->
                    val responseJsonData: Array<User>? = result.component1()
                    if (responseJsonData != null) {
                        for (user in responseJsonData) {
                            Log.d("apiUsersButtonClick", "result = ${user.toString()}")
                            name = user.name
                        }
                    }
                    result.component2()
                }
    }

    @Command
    fun bottomNavigationItemSelect(item: MenuItem) {
        when (item.itemId) {
            (R.id.item1) -> {
                Log.d("SampleApp", "R.id.item1")
            }
            (R.id.item2) -> {
                Log.d("SampleApp", "R.id.item2")
            }
            (R.id.item3) -> {
                Log.d("SampleApp", "R.id.item3")
            }
            else -> {
                Log.d("SampleApp", "unknown item")
            }
        }
    }


    data class User(
            val id: Long,
            val name: String
    ) {
        class Deserializer : ResponseDeserializable<Array<User>> {
            override fun deserialize(reader: Reader): Array<User> = Gson().fromJson(reader, Array<User>::class.java)
        }

    }
}