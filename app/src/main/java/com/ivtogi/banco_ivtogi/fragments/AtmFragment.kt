package com.ivtogi.banco_ivtogi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivtogi.banco_ivtogi.R
import com.ivtogi.banco_ivtogi.activities.AtmActivity
import com.ivtogi.banco_ivtogi.adapter.AtmAdapter
import com.ivtogi.banco_ivtogi.bd.BancoApplication
import com.ivtogi.banco_ivtogi.databinding.FragmentAtmBinding
import com.ivtogi.banco_ivtogi.entities.CajeroEntity
import java.util.concurrent.LinkedBlockingQueue

class AtmFragment : Fragment() {

    private lateinit var binding: FragmentAtmBinding

    private lateinit var atmAdapter: AtmAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var atmActivity: AtmActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAtmBinding.inflate(inflater, container, false)

        atmActivity = activity as AtmActivity

        binding.fab.setOnClickListener {
            atmActivity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frgContainer, AtmManagementFragment())
                .addToBackStack(null)
                .commit()
        }

        val queue = LinkedBlockingQueue<MutableList<CajeroEntity>>()
        Thread {
            val cajeroEntityList = BancoApplication.database.cajeroDao().getAllCajeros()
            queue.add(cajeroEntityList)
        }.start()

        atmAdapter = AtmAdapter(queue.take(), atmActivity)
        linearLayoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        binding.recycler.apply {
            adapter = atmAdapter
            layoutManager = linearLayoutManager
        }

        // Inflate the layout for this fragment
        return binding.root
    }


}