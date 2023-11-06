package com.ivtogi.banco_ivtogi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ivtogi.banco_ivtogi.bd.MiBancoOperacional
import com.ivtogi.banco_ivtogi.databinding.ActivityLoginBinding
import com.ivtogi.banco_ivtogi.pojo.Cliente

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bancoOperacional: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

        binding.btnLogin.setOnClickListener {

            val client = Cliente()

            client.setNif(binding.tietUser.text.toString())
            client.setClaveSeguridad(binding.tietPassword.text.toString())

            val logged = bancoOperacional?.login(client) ?: -1

            if (logged == -1) {
                Toast.makeText(this, getString(R.string.error_login), Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("user", logged)
                startActivity(intent)
            }

        }

        binding.btnExit.setOnClickListener {
            finish()
        }

    }
}