package com.ivtogi.banco_ivtogi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ivtogi.banco_ivtogi.R
import com.ivtogi.banco_ivtogi.adapter.MovementAdapter
import com.ivtogi.banco_ivtogi.adapter.OnClickMovementListener
import com.ivtogi.banco_ivtogi.bd.MiBancoOperacional
import com.ivtogi.banco_ivtogi.databinding.DialogMovementBinding
import com.ivtogi.banco_ivtogi.databinding.FragmentAccountsMovementsBinding
import com.ivtogi.banco_ivtogi.pojo.Cuenta
import com.ivtogi.banco_ivtogi.pojo.Movimiento

private const val ARG_ACCOUNT = "account"

class AccountsMovementsFragment : Fragment(), OnClickMovementListener {
    private lateinit var binding: FragmentAccountsMovementsBinding

    private var account: Cuenta? = null

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var movementAdapter: MovementAdapter
    private lateinit var dividerItemDecoration: DividerItemDecoration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            account = it.getSerializable(ARG_ACCOUNT) as Cuenta
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountsMovementsBinding.inflate(inflater, container, false)


        val bancoOperacional = MiBancoOperacional.getInstance(context)
        val movements = bancoOperacional?.getMovimientos(account) as ArrayList<*>

        linearLayoutManager = LinearLayoutManager(context)
        movementAdapter = MovementAdapter(movements, this)
        dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        binding.recycler.apply {
            layoutManager = linearLayoutManager
            adapter = movementAdapter
            addItemDecoration(dividerItemDecoration)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(account: Cuenta) =
            AccountsMovementsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_ACCOUNT, account)
                }
            }
    }

    override fun onClick(movement: Movimiento) {
        val dialogoBinding = DialogMovementBinding.inflate(layoutInflater)
        val context = dialogoBinding.tvDialog.context

        val originAccount = "${movement.getCuentaOrigen()?.getBanco()}" +
                "-${movement.getCuentaOrigen()?.getSucursal()}" +
                "-${movement.getCuentaOrigen()?.getDc()}" +
                "-${movement.getCuentaOrigen()?.getNumeroCuenta()}"

        val destinationAccount = "${movement.getCuentaDestino()?.getBanco()}" +
                "-${movement.getCuentaDestino()?.getSucursal()}" +
                "-${movement.getCuentaDestino()?.getDc()}" +
                "-${movement.getCuentaDestino()?.getNumeroCuenta()}"

        dialogoBinding.tvDialog.text = getString(
            R.string.movement_dialog,
            movement.getId().toString(),
            movement.getTipo().toString(),
            movement.getFechaOperacion(),
            movement.getDescripcion(),
            movement.getImporte().toString(),
            originAccount,
            destinationAccount
        )


        MaterialAlertDialogBuilder(context)
            .setView(dialogoBinding.root)
            .show()
    }

}