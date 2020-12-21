package com.practice.github_dagger2.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.FrameLayout
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import com.practice.github_dagger2.R
import com.practice.github_dagger2.databinding.ActivityMainBinding
import com.practice.github_dagger2.ui.base.BaseActivity
import com.practice.github_dagger2.ui.detail.DetailFragment
import com.practice.github_dagger2.utils.extension.transact

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var mSearchView: SearchView
    private var searchString: String? = null
    private val SEARCH_KEY = "search"

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState?.let {
            searchString = savedInstanceState.getString(SEARCH_KEY)

            findViewById<FrameLayout>(R.id.detail_activity_container)?.let {
                supportFragmentManager.transact {
                    replace(R.id.list_container, RepoListFragment.newInstance(searchString))
                    replace(R.id.detail_activity_container, DetailFragment.newInstance())
                }
            } ?: supportFragmentManager.transact { replace(R.id.list_container, RepoListFragment.newInstance(searchString)) }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.options_menu, menu)

        val searchItem = menu?.findItem(R.id.search)

        mSearchView = MenuItemCompat.getActionView(searchItem) as SearchView
        MenuItemCompat.expandActionView(searchItem)
        mSearchView.maxWidth = Int.MAX_VALUE

        if (searchString != null && searchString!!.isNotEmpty()) mSearchView.setQuery(searchString, true)

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                supportFragmentManager.transact { replace(R.id.list_container, RepoListFragment.newInstance(query)) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        searchString = mSearchView.query.toString()
        outState.putString(SEARCH_KEY, searchString)
    }


}