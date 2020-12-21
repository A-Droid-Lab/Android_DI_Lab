package com.practice.github_dagger2

import android.app.Application
import com.facebook.stetho.Stetho
import com.practice.github_dagger2.utils.extension.debug
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class GithubApp : DaggerApplication(){

//    @Inject
//    lateinit var androidInjector : DispatchingAndroidInjector<Any>

    //https://github.com/emrekose26/karchi

    private val appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)

        if (LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)

        debug {
            Stetho.initializeWithDefaults(this)
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }


}