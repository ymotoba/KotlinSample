package com.example.kotlin.viewmodel

import android.util.Log
import com.benny.library.kbinding.annotation.Command
import com.benny.library.kbinding.annotation.Property
import com.benny.library.kbinding.viewmodel.ViewModel
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.rx.rx_object
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.Reader
import kotlin.properties.Delegates

class MainActivityViewModel() : ViewModel() {

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
                        }
                    }
                    result.component2()
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
