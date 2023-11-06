package com.ivtogi.banco_ivtogi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivtogi.banco_ivtogi.adapter.AccountAdapter
import com.ivtogi.banco_ivtogi.bd.MiBancoOperacional
import com.ivtogi.banco_ivtogi.databinding.ActivityGlobalPositionBinding
import com.ivtogi.banco_ivtogi.pojo.Cliente
import com.ivtogi.banco_ivtogi.pojo.Cuenta

class GlobalPositionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGlobalPositionBinding

    private lateinit var accountAdapter: AccountAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAccounts()

    }

    private fun initAccounts() {

        val bancoOperacional: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
        val client: Cliente = intent.getSerializableExtra("user") as Cliente
        val accounts  = bancoOperacional?.getCuentas(client)

        if (accounts != null)
            accountAdapter = AccountAdapter(accounts)

        linearLayoutManager = LinearLayoutManager(this)

        binding.recycler.apply {
            adapter = accountAdapter
            layoutManager = linearLayoutManager
        }
    }
}