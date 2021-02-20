package com.kharismarizqii.githubuserapp.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kharismarizqii.githubuserapp.MyApplication
import com.kharismarizqii.githubuserapp.R
import com.kharismarizqii.githubuserapp.core.data.Resource
import com.kharismarizqii.githubuserapp.core.ui.UserAdapter
import com.kharismarizqii.githubuserapp.core.ui.ViewModelFactory
import com.kharismarizqii.githubuserapp.databinding.AppBarBinding
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



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return true
    }
}