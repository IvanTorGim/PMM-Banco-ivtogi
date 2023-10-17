package com.ivtogi.banco_ivtogi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ivtogi.banco_ivtogi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("USERNAME")
        binding.tvUser.text = "Bienvenido/a\n${username}"
    }
}