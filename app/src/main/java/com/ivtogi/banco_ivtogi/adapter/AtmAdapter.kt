package com.ivtogi.banco_ivtogi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivtogi.banco_ivtogi.R
import com.ivtogi.banco_ivtogi.databinding.ItemAtmBinding
import com.ivtogi.banco_ivtogi.entities.CajeroEntity

class AtmAdapter(
    private val cajeroEntityList: MutableList<CajeroEntity>,
    private val listener: OnClickAtmListener
) :
    RecyclerView.Adapter<AtmAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_atm, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cajeroEntityList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cajero = cajeroEntityList[position]
        holder.binding.apply {
            tvName.text = "ATM ${cajero.id}"
            tvAddress.text = cajero.direccion
        }
        holder.setListener(cajero)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemAtmBinding.bind(view)

        fun setListener(cajeroEntity: CajeroEntity) {
            binding.root.setOnClickListener {
                listener.onClick(cajeroEntity)
            }
        }
    }


}