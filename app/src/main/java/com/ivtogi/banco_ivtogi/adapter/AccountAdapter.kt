package com.ivtogi.banco_ivtogi.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivtogi.banco_ivtogi.R
import com.ivtogi.banco_ivtogi.databinding.ItemAccountBinding
import com.ivtogi.banco_ivtogi.pojo.Cuenta

class AccountAdapter(private val accounts: ArrayList<*>) :
    RecyclerView.Adapter<AccountAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_account, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = accounts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val account = accounts[position] as Cuenta

        holder.apply {
            binding.tvAccount.text =
                "${account.getBanco()}-${account.getSucursal()}-${account.getDc()}-${account.getNumeroCuenta()}"
            val balance = account.getSaldoActual()
            binding.tvBalance.text = balance.toString()
            if (balance != null && balance > 0) binding.tvBalance.setTextColor(Color.GREEN)
            else binding.tvBalance.setTextColor(Color.RED)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemAccountBinding.bind(view)
    }
}