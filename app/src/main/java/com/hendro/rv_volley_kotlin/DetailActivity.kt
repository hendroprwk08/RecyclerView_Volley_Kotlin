package com.hendro.rv_volley_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hendro.rv_volley_kotlin.databinding.ActivityDetailBinding
import com.hendro.rv_volley_kotlin.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}