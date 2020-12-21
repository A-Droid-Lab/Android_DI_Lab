package com.practice.github_dagger2.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice.github_dagger2.data.local.entity.Repos
import com.practice.github_dagger2.repository.GithubRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RepoListViewModel @Inject constructor(private val githubRepository: GithubRepository): ViewModel() {

    val disposable = CompositeDisposable()

    val repoLiveList = MutableLiveData<List<Repos>>()

    val repoList: LiveData<List<Repos>>
        get() = repoLiveList

    fun getRepoList(username: String): LiveData<List<Repos>> {
        disposable.add(githubRepository.getRepoList(username)
            .subscribe { response ->
                repoLiveList.value = response
            })
        return repoList
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}
