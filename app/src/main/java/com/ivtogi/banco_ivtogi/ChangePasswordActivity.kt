package com.ivtogi.banco_ivtogi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ivtogi.banco_ivtogi.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changePassword()
        exit()
    }

    private fun newPasswordMatch(): Boolean {
        val newPassword: String = binding.tilNewPassword.editText?.text.toString()
        val confirmPassword: String = binding.tilConfirmPassword.editText?.text.toString()

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
        val oldPassword: String = binding.tilOldPassword.editText?.text.toString()

        binding.tilOldPassword.isErrorEnabled = false

        if (password != oldPassword) {
            binding.tilOldPassword.error = getString(R.string.error_match_password)
            return false
        }

        return true
    }

    private fun changePassword() {
        binding.btnAccept.setOnClickListener {
            val password: String? = intent.getStringExtra("PASSWORD")
            if (oldPasswordMatch(password) && newPasswordMatch())
                finish()
        }

    }

    private fun exit() {
        binding.btnExit.setOnClickListener {
            finish()
        }
    }


}