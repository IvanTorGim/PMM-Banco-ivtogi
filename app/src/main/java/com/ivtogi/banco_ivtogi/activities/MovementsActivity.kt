package com.ivtogi.banco_ivtogi.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivtogi.banco_ivtogi.adapter.MovementAdapter
import com.ivtogi.banco_ivtogi.adapter.OnClickMovementListener
import com.ivtogi.banco_ivtogi.bd.MiBancoOperacional
import com.ivtogi.banco_ivtogi.databinding.ActivityMovementsBinding
import com.ivtogi.banco_ivtogi.pojo.Cliente
import com.ivtogi.banco_ivtogi.pojo.Cuenta
import com.ivtogi.banco_ivtogi.pojo.Movimiento

class MovementsActivity : AppCompatActivity(), OnClickMovementListener {

    private lateinit var binding: ActivityMovementsBinding

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var movementAdapter: MovementAdapter
    private lateinit var itemDecoration: DividerItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSpinner()
    }

    private fun initSpinner() {
        val bancoOperacional = MiBancoOperacional.getInstance(this)
        val client = intent.getSerializableExtra("user") as Cliente
        val accounts = bancoOperacional?.getCuentas(client) as MutableList<*>
        val accountsNumber: MutableList<String> = mutableListOf()

        for (account in accounts) {
            account as Cuenta
            accountsNumber.add("${account.getBanco()}-${account.getSucursal()}-${account.getDc()}-${account.getNumeroCuenta()}")
        }


        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, accountsNumber)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val account: Cuenta = accounts[position] as Cuenta
                val movements: ArrayList<*> =
                    bancoOperacional.getMovimientos(account) as ArrayList<*>
                initMovements(movements)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    private fun initMovements(movements: ArrayList<*>) {
        movementAdapter = MovementAdapter(movements, this)
        linearLayoutManager = LinearLayoutManager(this)
        itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        binding.recycler.apply {
            adapter = movementAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(itemDecoration)
        }

    }

    override fun onClick(movement: Movimiento) {
        // Para que no de error
    }

}