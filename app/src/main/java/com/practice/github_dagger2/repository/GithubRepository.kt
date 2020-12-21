package com.practice.github_dagger2.repository

import com.practice.github_dagger2.data.local.dao.GithubDao
import com.practice.github_dagger2.data.local.entity.Repos
import com.practice.github_dagger2.data.remote.ApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GithubRepository @Inject constructor(private val apiService: ApiService,
                                           private val githubDao: GithubDao) {

    fun getRepoList(username: String): Single<List<Repos>> {
        return apiService.getUserRepos(username).onErrorResumeNext {
            githubDao.getReposFromDb(username)
        }.doOnSuccess {
            githubDao.insertRepos(it)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}