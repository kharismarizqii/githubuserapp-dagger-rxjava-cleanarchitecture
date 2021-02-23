package com.kharismarizqii.githubuserapp.user

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxSearchView
import com.kharismarizqii.githubuserapp.MyApplication
import com.kharismarizqii.githubuserapp.R
import com.kharismarizqii.githubuserapp.core.data.Resource
import com.kharismarizqii.githubuserapp.core.ui.UserAdapter
import com.kharismarizqii.githubuserapp.core.ui.ViewModelFactory
import com.kharismarizqii.githubuserapp.databinding.AppBarBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: UserViewModel by viewModels {
        factory
    }
    private lateinit var binding: AppBarBinding
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = AppBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter()

        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }

    @SuppressLint("CheckResult")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)

        val queryStream = RxSearchView.queryTextChanges(searchView)
            .map { query ->
                query.toString()
            }
            .debounce(700, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())


        queryStream.subscribe({
            if (it.isNotEmpty()) {
                Log.e("queryStream", "ini querynya ${it}")
                getSearchUser(it)
            }
        }, {
            Log.e("queryStream Exception", "${it.message}")
        })
        return true
    }

    private fun getSearchUser(q: String) {

        viewModel.getSearchUser(q).observe(this, { user ->
            Log.e("Observe LiveData", "${user.data}")
            if (user != null) {
                when (user) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.viewStarter.root.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        Log.e("Observe LiveData", " Resource Success: ${user.data}")
                        binding.rvUser.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.GONE
                        userAdapter.setData(user.data)
                    }
                    is Resource.Error -> {
                        binding.rvUser.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        binding.viewStarter.root.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            user.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })
    }
}