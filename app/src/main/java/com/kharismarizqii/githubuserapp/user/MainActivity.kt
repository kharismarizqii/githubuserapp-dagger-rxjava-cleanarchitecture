package com.kharismarizqii.githubuserapp.user

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxSearchView
import com.kharismarizqii.githubuserapp.MyApplication
import com.kharismarizqii.githubuserapp.R
import com.kharismarizqii.githubuserapp.core.data.Resource
import com.kharismarizqii.githubuserapp.core.ui.UserAdapter
import com.kharismarizqii.githubuserapp.core.ui.ViewModelFactory
import com.kharismarizqii.githubuserapp.databinding.AppBarBinding
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: UserViewModel by viewModels {
        factory
    }
    private lateinit var binding: AppBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = AppBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = UserAdapter()
        viewModel.getSearchUser("kharisma").observe(this, { user ->
            if(user != null){
                when(user){
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.setData(user.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = user.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })

        with(binding.rvUser){
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            this.adapter = adapter
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
            .skipInitialValue()
            .distinctUntilChanged()
            .debounce(400, TimeUnit.MILLISECONDS)

        queryStream.subscribe{
            Log.e("Query", "ini querynya: $it")
//            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        return true
    }
}