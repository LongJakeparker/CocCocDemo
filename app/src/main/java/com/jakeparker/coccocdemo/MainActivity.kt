package com.jakeparker.coccocdemo

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.observe
import com.jakeparker.coccocdemo.adapter.NewsAdapter
import com.jakeparker.coccocdemo.dagger.DaggerCocCocComponent
import com.jakeparker.coccocdemo.databinding.ActivityMainBinding
import com.jakeparker.coccocdemo.model.Item
import com.jakeparker.coccocdemo.model.Status
import com.jakeparker.coccocdemo.util.CommonUtils
import com.jakeparker.coccocdemo.view.NewsDetailActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter: NewsAdapter? = null

    private val mViewModel: MainViewModel by viewModels {
        DaggerCocCocComponent.create().getMainViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
            viewModel = mViewModel
            lifecycleOwner = this@MainActivity
        }

        if (Preferences.getInstance(this).isContainDarkModeFlag()) {
            if (Preferences.getInstance(this).getDarkModeFlag()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        setupRecyclerView()

        if (!mViewModel.isCreated) {
            getListNews()
        } else {
            adapter?.submitList(mViewModel.listData)
        }

        observeEvents()
    }

    private fun observeEvents() {
        mViewModel.apply {
            eventRefresh.observe(this@MainActivity) {
                this@MainActivity.getListNews()
            }

            evenDarkMode.observe(this@MainActivity) {
                when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_YES -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        Preferences.getInstance(this@MainActivity).setDarkModeFlag(false)
                    }
                    Configuration.UI_MODE_NIGHT_NO -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        Preferences.getInstance(this@MainActivity).setDarkModeFlag(true)
                    }
                }
            }
        }
    }

    private val itemClickCallback = object : NewsAdapter.Callback {
        override fun onSelect(item: Item) {
            if (CommonUtils.checkClickTimeInvalidation())
                return

            item.link?.let { NewsDetailActivity.start(this@MainActivity, item.title, it) }
        }
    }

    private fun setupRecyclerView() {
        adapter = NewsAdapter(itemClickCallback)
        binding.rvNews.adapter = adapter
    }

    private fun getListNews() {
        mViewModel.getListNews().observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    if (!mViewModel.isRefresh) {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }

                Status.ERROR -> {
                    onCallFinished()
                    Toast.makeText(
                        this@MainActivity,
                        it.exception?.localizedMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }

                Status.SUCCESS -> {
                    onCallFinished()
                    mViewModel.listData = it.data
                    adapter?.submitList(it.data)
                }
            }
        }
    }

    private fun onCallFinished() {
        binding.progressBar.visibility = View.GONE
        binding.refreshLayout.isRefreshing = false
        mViewModel.isRefresh = false
        mViewModel.isCreated = true
    }
}