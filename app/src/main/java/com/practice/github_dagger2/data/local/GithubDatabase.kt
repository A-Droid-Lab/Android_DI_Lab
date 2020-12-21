package com.practice.github_dagger2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practice.github_dagger2.data.local.dao.GithubDao
import com.practice.github_dagger2.data.local.entity.Repos
import com.practice.github_dagger2.data.local.entity.User

@Database(entities = arrayOf(User::class, Repos::class), version = 1, exportSchema = false)
abstract class GithubDatabase: RoomDatabase() {
    abstract fun githubDao(): GithubDao
}