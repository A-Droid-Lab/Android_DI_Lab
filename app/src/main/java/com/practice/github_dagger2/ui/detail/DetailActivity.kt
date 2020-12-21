package com.practice.github_dagger2.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.practice.github_dagger2.R
import com.practice.github_dagger2.data.local.entity.Repos
import com.practice.github_dagger2.databinding.ActivityDetailBinding
import com.practice.github_dagger2.ui.base.BaseActivity
import com.practice.github_dagger2.utils.extension.transact

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    companion object {
        fun newIntent(context: Context, repos: Repos?) =
            Intent(context, DetailActivity::class.java).apply { putExtra("REPOS", repos) }
    }

    override fun getLayoutRes(): Int = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repos: Repos? = intent.extras?.getParcelable("REPOS")

        repos?.let {
            title = it.name
            supportFragmentManager.transact {
                replace(R.id.detail_activity_container, DetailFragment.newInstance(it))
            }
        }

    }
}
