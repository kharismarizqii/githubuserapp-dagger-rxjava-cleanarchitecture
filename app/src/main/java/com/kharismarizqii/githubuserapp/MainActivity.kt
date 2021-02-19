package com.kharismarizqii.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.kharismarizqii.githubuserapp.R
import com.kharismarizqii.githubuserapp.databinding.AppBarBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: AppBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AppBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return true
    }
}