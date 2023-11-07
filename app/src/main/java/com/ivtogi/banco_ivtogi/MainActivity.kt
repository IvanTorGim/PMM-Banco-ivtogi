package com.ivtogi.banco_ivtogi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ivtogi.banco_ivtogi.databinding.ActivityMainBinding
import com.ivtogi.banco_ivtogi.pojo.Cliente

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var user: Cliente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getSerializableExtra("user") as Cliente

        showWelcome()
        globalPosition()
        movements()
        transferActivity()
        changePasswordActivity()
        exit()
    }

    private fun showWelcome() {
        binding.tvUser.text = getString(R.string.welcome, user.getNombre().toString())
    }

    private fun globalPosition() {
        binding.btnPosition.setOnClickListener {
            val intent = Intent(this, GlobalPositionActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }

    private fun movements() {
        binding.btnMovement.setOnClickListener {
            val intent = Intent(this, MovementsActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }

    private fun transferActivity() {
        binding.btnTransfers.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }
    }

    private fun changePasswordActivity() {
        binding.btnPassword.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }

    private fun exit() {
        binding.btnExit.setOnClickListener { finish() }
    }

}