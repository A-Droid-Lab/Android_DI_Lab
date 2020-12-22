package com.practice.github_dagger2

import com.facebook.stetho.Stetho
import com.practice.github_dagger2.di.DaggerAppComponent
import com.practice.github_dagger2.utils.extension.debug
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class GithubApp : DaggerApplication(){

    private val applicationInjector = DaggerAppComponent.builder().application(this).build()
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector

//    override fun onCreate() {
//        super.onCreate()
//        if (LeakCanary.isInAnalyzerProcess(this)) return
//        LeakCanary.install(this)
//        debug {
//            Stetho.initializeWithDefaults(this)
//        }
//    }
}