package com.practice.github_dagger2.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.practice.github_dagger2.data.local.entity.Repos

class RepoDiffCallback(var oldRepoList: List<Repos>, var newRepoList: List<Repos>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldRepoList.size

    override fun getNewListSize(): Int = newRepoList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldRepoList[oldItemPosition].repoId == newRepoList[newItemPosition].repoId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldRepoList[oldItemPosition].equals(newRepoList[newItemPosition])
}
