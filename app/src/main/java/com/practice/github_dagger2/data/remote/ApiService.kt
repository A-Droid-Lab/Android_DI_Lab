package com.practice.github_dagger2.data.remote

import com.practice.github_dagger2.data.local.entity.Repos
import com.practice.github_dagger2.data.local.entity.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Single<User>

    @GET("users/{username}/repos")
    fun getUserRepos(@Path("username") username: String): Single<List<Repos>>
}