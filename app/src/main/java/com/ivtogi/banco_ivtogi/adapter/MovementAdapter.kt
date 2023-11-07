package com.ivtogi.banco_ivtogi.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivtogi.banco_ivtogi.R
import com.ivtogi.banco_ivtogi.databinding.ItemMovementBinding
import com.ivtogi.banco_ivtogi.pojo.Movimiento
import java.text.DateFormat

class MovementAdapter(private val movements: ArrayList<*>) :
    RecyclerView.Adapter<MovementAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movement, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movements.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movement: Movimiento = movements[position] as Movimiento
        holder.binding.apply {
            tvMovementName.text = movement.getDescripcion()

            val import = movement.getImporte()
            val date = movement.getFechaOperacion()
            val dateFormat = DateFormat.getDateInstance().format(date!!)
            tvBalance.text = context.getString(R.string.balance, dateFormat, import.toString())

            if (import > 0) tvBalance.setTextColor(Color.GREEN)
            else tvBalance.setTextColor(Color.RED)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMovementBinding.bind(view)
    }
}