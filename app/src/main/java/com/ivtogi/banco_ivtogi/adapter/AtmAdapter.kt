package com.ivtogi.banco_ivtogi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivtogi.banco_ivtogi.R
import com.ivtogi.banco_ivtogi.databinding.ItemAtmBinding
import com.ivtogi.banco_ivtogi.entities.CajeroEntity

class AtmAdapter(private val atmList: MutableList<CajeroEntity>) :
    RecyclerView.Adapter<AtmAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_atm, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = atmList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val atm = atmList[position]
        holder.binding.apply {
            tvName.text = "ATM ${atm.id}"
            tvAddress.text = atm.direccion
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemAtmBinding.bind(view)
    }
}