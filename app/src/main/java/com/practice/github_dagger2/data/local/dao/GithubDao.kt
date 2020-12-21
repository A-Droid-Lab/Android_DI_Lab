package com.practice.github_dagger2.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.practice.github_dagger2.data.local.entity.Repos
import io.reactivex.Single

@Dao
abstract class GithubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRepos(repoList: List<Repos>)

    @Query("SELECT * FROM repos WHERE username = :username")
    abstract fun getReposFromDb(username: String): Single<List<Repos>>
}