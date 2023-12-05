package com.ivtogi.banco_ivtogi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivtogi.banco_ivtogi.adapter.AccountAdapter
import com.ivtogi.banco_ivtogi.adapter.OnClickAccountListener
import com.ivtogi.banco_ivtogi.bd.MiBancoOperacional
import com.ivtogi.banco_ivtogi.databinding.FragmentAccountsBinding
import com.ivtogi.banco_ivtogi.pojo.Cliente
import com.ivtogi.banco_ivtogi.pojo.Cuenta

private const val ARG_CLIENTE = "cliente"


class AccountsFragment : Fragment(), OnClickAccountListener {

    private var client: Cliente? = null

    private lateinit var binding: FragmentAccountsBinding

    private lateinit var accountAdapter: AccountAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var listener: AccountListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            client = it.getSerializable(ARG_CLIENTE) as Cliente
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountsBinding.inflate(inflater, container, false)

        val bancoOperacional: MiBancoOperacional? = MiBancoOperacional.getInstance(context)
        val accounts: ArrayList<*>? = bancoOperacional?.getCuentas(client)

        if (accounts != null)
            accountAdapter = AccountAdapter(accounts, this)

        linearLayoutManager = LinearLayoutManager(context)

        binding.recycler.apply {
            adapter = accountAdapter
            layoutManager = linearLayoutManager
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(cliente: Cliente) =
            AccountsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CLIENTE, cliente)
                }
            }
    }

    //Recogemos el listener del activity
    fun setAccountListener(listener: AccountListener) {
        this.listener = listener
    }

    override fun onclick(account: Cuenta) {
        if (listener != null)
            listener.onAccountSelected(account)
    }
}