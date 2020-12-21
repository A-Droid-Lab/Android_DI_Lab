package com.practice.github_dagger2.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practice.github_dagger2.di.ViewModelKey
import com.practice.github_dagger2.ui.detail.DetailViewModel
import com.practice.github_dagger2.ui.main.RepoListViewModel
import com.practice.github_dagger2.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RepoListViewModel::class)
    abstract fun bindsRepoListViewModel(repoListViewModel: RepoListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindsDetailViewModel(detailViewModel: DetailViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}