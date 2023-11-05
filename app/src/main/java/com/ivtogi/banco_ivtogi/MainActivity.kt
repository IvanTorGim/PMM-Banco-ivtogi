package com.ivtogi.banco_ivtogi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ivtogi.banco_ivtogi.databinding.ActivityMainBinding
import com.ivtogi.banco_ivtogi.pojo.Cliente

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showWelcome()
        changePasswordActivity()
        transferActivity()
        exit()
    }

    private fun transferActivity() {
        binding.btnTransfers.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showWelcome() {
        val user: Cliente? = intent.getSerializableExtra("LOGGED") as Cliente
        binding.tvUser.text = getString(R.string.welcome, user?.getNombre())
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