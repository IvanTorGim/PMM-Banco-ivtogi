package com.ivtogi.banco_ivtogi

import android.content.Intent
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
        changePasswordActivity()
        exit()
    }

    private fun showWelcome() {
        val username: String? = intent.getStringExtra("USERNAME")
        binding.tvUser.text = getString(R.string.welcome, username)
    }

    private fun exit() {
        binding.btnExit.setOnClickListener { finish() }
    }

    private fun changePasswordActivity() {
        binding.btnPassword.setOnClickListener {
            val password: String? = intent.getStringExtra("PASSWORD")
            val intent = Intent(this, ChangePasswordActivity::class.java)
            intent.putExtra("PASSWORD", password)
            startActivity(intent)
        }
    }
}