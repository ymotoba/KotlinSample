package com.example.kotlin.view.rxbinding


import android.support.annotation.CheckResult
import com.jakewharton.rxbinding.internal.Preconditions.checkUiThread
import com.sackcentury.shinebuttonlib.ShineButton
import rx.Observable
import rx.Subscriber
import rx.android.MainThreadSubscription

class ShineButtonCheckStateChangeOnSubscribe private constructor(private val shineButton: ShineButton) : Observable.OnSubscribe<Boolean> {

    override fun call(subscriber: Subscriber<in Boolean>) {
        checkUiThread()

        shineButton.setOnCheckStateChangeListener { _, checked ->
            if (!subscriber.isUnsubscribed) {
                subscriber.onNext(checked)
            }
        }

        subscriber.add(object : MainThreadSubscription() {
            override fun onUnsubscribe() {
                shineButton.setOnCheckStateChangeListener(null)
            }
        })
    }

    companion object {
        @CheckResult
        fun changes(view: ShineButton): Observable<Boolean> {
            return Observable.create(ShineButtonCheckStateChangeOnSubscribe(view))
        }
    }
}
