package com.practice.github_dagger2.utils.extension

import androidx.databinding.library.BuildConfig
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun debug(block: () -> Unit) {
    if (BuildConfig.DEBUG) block()
}

inline fun FragmentManager.transact(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}