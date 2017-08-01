package com.example.kotlin.view.rxbinding


import android.support.annotation.CheckResult
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.jakewharton.rxbinding.internal.Preconditions.checkUiThread
import rx.Observable
import rx.Subscriber
import rx.android.MainThreadSubscription

class BottomNavigationViewNavigationItemSelectOnSubscribe private constructor(private val bottomNavigationView: BottomNavigationView) : Observable.OnSubscribe<MenuItem> {

    override fun call(subscriber: Subscriber<in MenuItem>) {
        checkUiThread()

        bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            if (!subscriber.isUnsubscribed) {
                subscriber.onNext(item)
            }
            return@setOnNavigationItemSelectedListener true;
        }

        subscriber.add(object : MainThreadSubscription() {
            override fun onUnsubscribe() {
                bottomNavigationView.setOnNavigationItemSelectedListener(null)
            }
        })
    }

    companion object {
        @CheckResult
        fun selects(view: BottomNavigationView): Observable<MenuItem> {
            return Observable.create(BottomNavigationViewNavigationItemSelectOnSubscribe(view))
        }
    }
}
