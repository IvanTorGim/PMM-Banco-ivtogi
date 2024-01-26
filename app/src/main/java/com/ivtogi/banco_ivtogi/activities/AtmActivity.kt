package com.ivtogi.banco_ivtogi.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ivtogi.banco_ivtogi.databinding.ActivityAtmBinding

class AtmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAtmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtmBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}