package com.practice.github_dagger2.ui.detail

import androidx.lifecycle.ViewModel
import com.practice.github_dagger2.repository.GithubRepository
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val githubRepository: GithubRepository) : ViewModel() {
}