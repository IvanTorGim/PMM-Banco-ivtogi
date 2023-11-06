package com.ivtogi.banco_ivtogi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ivtogi.banco_ivtogi.bd.MiBancoOperacional
import com.ivtogi.banco_ivtogi.databinding.ActivityChangePasswordBinding
import com.ivtogi.banco_ivtogi.pojo.Cliente

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding
    private lateinit var user: Cliente


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getSerializableExtra("user") as Cliente

        changePassword()
        exit()
    }

    private fun newPasswordMatch(): Boolean {
        val newPassword: String = binding.tietNewPassword.text.toString()
        val confirmPassword: String = binding.tietConfirmPassword.text.toString()

        binding.tilNewPassword.isErrorEnabled = false
        binding.tilConfirmPassword.isErrorEnabled = false

        if (newPassword.isBlank()) {
            binding.tilNewPassword.error = getString(R.string.empty_field)
            return false
        } else if (newPassword != confirmPassword) {
            binding.tilConfirmPassword.error = getString(R.string.error_match_password)
            return false
        }

        return true
    }

    private fun oldPasswordMatch(password: String?): Boolean {
        val oldPassword: String = binding.tietOldPassword.text.toString()

        binding.tilOldPassword.isErrorEnabled = false

        if (password != oldPassword) {
            binding.tilOldPassword.error = getString(R.string.error_match_password)
            return false
        }

        return true
    }

    private fun changePassword() {
        binding.btnAccept.setOnClickListener {
            val password: String = user.getClaveSeguridad().toString()
            val bancoOperacional: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

            if (oldPasswordMatch(password) && newPasswordMatch()) {

                user.setClaveSeguridad(binding.tietNewPassword.text.toString())

                if (bancoOperacional?.changePassword(user) == 0) {
                    Toast.makeText(
                        this,
                        getString(R.string.error_change_password),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val newUser = bancoOperacional?.login(user)
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user", newUser)
                    startActivity(intent)
                }
            }

        }

    }

    private fun exit() {
        binding.btnExit.setOnClickListener {
            finish()
        }
    }


}