package com.practice.github_dagger2.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity<DB: ViewDataBinding>: AppCompatActivity(), HasAndroidInjector {

//    dagger 2.24 version부터 삭제됨
//    @Inject
//    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>

    lateinit var dataBinding: DB

    @LayoutRes
    abstract fun getLayoutRes(): Int

    @MenuRes
    open fun getMenuRes(): Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes())
    }

    /*
    dagger 2.24버전 이후 지원
    https://medium.com/@fornewid/hasandroidinjector-in-dagger-2-24-f50f06f35150
    */
    override fun androidInjector(): AndroidInjector<Any> = androidInjector

//    dagger 2.24 version부터 삭제됨
//    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector
}
