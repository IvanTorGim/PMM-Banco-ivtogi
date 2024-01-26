package com.ivtogi.banco_ivtogi.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ivtogi.banco_ivtogi.R
import com.ivtogi.banco_ivtogi.adapter.OnClickAtmListener
import com.ivtogi.banco_ivtogi.databinding.ActivityAtmBinding
import com.ivtogi.banco_ivtogi.entities.CajeroEntity
import com.ivtogi.banco_ivtogi.fragments.AtmManagementFragment

class AtmActivity : AppCompatActivity(), OnClickAtmListener {

    private lateinit var binding: ActivityAtmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtmBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onClick(cajeroEntity: CajeroEntity) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frgContainer, AtmManagementFragment.newInstance(cajeroEntity))
            .addToBackStack(null)
            .commit()
    }

}