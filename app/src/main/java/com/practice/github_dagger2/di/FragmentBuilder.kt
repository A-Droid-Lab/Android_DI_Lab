package com.practice.github_dagger2.di

import com.practice.github_dagger2.ui.detail.DetailFragment
import com.practice.github_dagger2.ui.main.RepoListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun contributeRepoListFragment(): RepoListFragment


    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment

}