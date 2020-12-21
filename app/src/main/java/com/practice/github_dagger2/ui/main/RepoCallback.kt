package com.practice.github_dagger2.ui.main

import com.practice.github_dagger2.data.local.entity.Repos

interface RepoCallback {
    fun onRepoClick(repos: Repos?)
}