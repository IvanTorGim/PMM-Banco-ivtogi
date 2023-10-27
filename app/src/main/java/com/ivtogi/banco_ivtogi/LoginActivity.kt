package com.ivtogi.banco_ivtogi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.ivtogi.banco_ivtogi.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var user: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            user = binding.tilUser.editText?.text.toString()
            password = binding.tilPassword.editText?.text.toString()

            if (user.isNullOrBlank() || password.isNullOrBlank()) {
                Snackbar.make(binding.root, R.string.error_login, Snackbar.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("USERNAME", user)
                startActivity(intent)
            }
        }

        binding.btnExit.setOnClickListener {
            finish()
        }

    }
}