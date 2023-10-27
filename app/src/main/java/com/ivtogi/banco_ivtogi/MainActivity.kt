package com.ivtogi.banco_ivtogi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ivtogi.banco_ivtogi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showWelcome()
        exit()
    }

    private fun showWelcome() {
        val username = intent.getStringExtra("USERNAME")
        binding.tvUser.text = "Bienvenido/a\n${username}"
    }

    private fun exit() {
        binding.btnExit.setOnClickListener { finish() }
    }
}