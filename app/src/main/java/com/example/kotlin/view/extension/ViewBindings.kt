package com.example.kotlin.view.extension

import com.benny.library.kbinding.bind.PropertyBinding
import com.benny.library.kbinding.bind.commandBinding
import com.example.kotlin.view.rxbinding.ShineButtonCheckStateChangeOnSubscribe
import com.jakewharton.rxbinding.view.RxView
import com.sackcentury.shinebuttonlib.ShineButton

fun ShineButton.checkedChange(path: String) : PropertyBinding = commandBinding(path, ShineButtonCheckStateChangeOnSubscribe.changes(this), RxView.enabled(this))
