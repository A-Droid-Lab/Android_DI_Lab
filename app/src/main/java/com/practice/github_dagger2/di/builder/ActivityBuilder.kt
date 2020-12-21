package com.practice.github_dagger2.di.builder

import com.practice.github_dagger2.ui.detail.DetailActivity
import com.practice.github_dagger2.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [FragmentBuilder::class])
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBuilder::class])
    abstract fun detailActivity(): DetailActivity
}