package com.practice.github_dagger2.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.github_dagger2.data.local.entity.Repos
import com.practice.github_dagger2.databinding.ItemRepoBinding
import com.practice.github_dagger2.ui.base.BaseAdapter

class RepoListRecyclerAdapter(var callback: RepoCallback) : BaseAdapter<RepoListRecyclerAdapter.ViewHolder, Repos>() {

    var repoList: ArrayList<Repos> = ArrayList()

    override fun setData(dataList: List<Repos>) {
        val diffCallback = DiffUtil.calculateDiff(RepoDiffCallback(repoList, dataList))
        with(repoList) { clear(); addAll(dataList) }
        diffCallback.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = repoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(LayoutInflater.from(parent.context), parent, callback)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.bind(repoList[position])
    }

    class ViewHolder(val itemRepoBinding: ItemRepoBinding, val callback: RepoCallback) : RecyclerView.ViewHolder(itemRepoBinding.root) {
        companion object {
            fun create(layoutInflater: LayoutInflater, parent: ViewGroup?, callback: RepoCallback): ViewHolder {
                val itemRepoBinding = ItemRepoBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(itemRepoBinding, callback)
            }
        }

        init {
            itemRepoBinding.root.setOnClickListener {
                callback.let { callback.onRepoClick(itemRepoBinding.repo) }
            }
        }

        fun bind(repos: Repos) {
            itemRepoBinding.apply {
                repo = repos
                executePendingBindings()
            }
        }

    }


}