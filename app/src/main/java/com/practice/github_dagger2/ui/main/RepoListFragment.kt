package com.practice.github_dagger2.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.github_dagger2.R
import com.practice.github_dagger2.data.local.entity.Repos
import com.practice.github_dagger2.databinding.FragmentRepoListBinding
import com.practice.github_dagger2.ui.base.BaseFragment
import com.practice.github_dagger2.ui.detail.DetailActivity
import com.practice.github_dagger2.ui.detail.DetailFragment
import com.practice.github_dagger2.utils.extension.transact


class RepoListFragment : BaseFragment<RepoListViewModel, FragmentRepoListBinding>(), RepoCallback {

    private var username: String? = null
    private var mTwoPane = false
    private var adapter = RepoListRecyclerAdapter(this)

    companion object {
        val USERNAME_KEY = "username"

        fun newInstance(username: String? = null): RepoListFragment {

            var args = Bundle()
            var fragment = RepoListFragment()
            username?.let { args.putString(RepoListFragment.USERNAME_KEY, username) }
            fragment.arguments = args
            return fragment
        }
    }

    override fun getViewModel(): Class<RepoListViewModel> = RepoListViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_repo_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { username = it.getString(RepoListFragment.USERNAME_KEY) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        dataBinding.repoListRecycler.layoutManager = LinearLayoutManager(activity)
        dataBinding.repoListRecycler.adapter = adapter
        dataBinding.progressBar.visibility = View.VISIBLE

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mTwoPane = activity?.findViewById<FrameLayout>(R.id.detail_container) != null

        username?.let {
            viewModel.getRepoList(username!!).observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.setData(it)
                    dataBinding.progressBar.visibility = View.GONE
                }
            })
        }
    }

    override fun onRepoClick(repos: Repos?) {
        if (!mTwoPane) {
            startActivity(DetailActivity.newIntent(activity!!, repos))
        } else {
            activity?.supportFragmentManager?.transact { replace(R.id.detail_container, DetailFragment.newInstance(repos)) }
        }
    }
}