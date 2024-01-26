package com.ivtogi.banco_ivtogi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.ivtogi.banco_ivtogi.R
import com.ivtogi.banco_ivtogi.activities.AtmActivity
import com.ivtogi.banco_ivtogi.bd.BancoApplication
import com.ivtogi.banco_ivtogi.databinding.FragmentAtmManagementBinding
import com.ivtogi.banco_ivtogi.entities.CajeroEntity
import java.util.concurrent.LinkedBlockingQueue

private const val ARG_CAJERO_ENTITY = "cajeroEntity"


class AtmManagementFragment : Fragment() {
    private var cajeroEntity: CajeroEntity? = null
    private lateinit var binding: FragmentAtmManagementBinding
    private lateinit var atmActivity: AtmActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cajeroEntity = it.getSerializable(ARG_CAJERO_ENTITY) as CajeroEntity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAtmManagementBinding.inflate(inflater, container, false)
        atmActivity = activity as AtmActivity

        if (cajeroEntity == null) {
            binding.apply {
                tvTitle.text = getString(R.string.createAtm)
                btnSave.isVisible = true
                btnCancel.isVisible = true
                btnUpdate.isVisible = false
                btnDelete.isVisible = false
            }
        } else {
            binding.apply {
                tvTitle.text = getString(R.string.modifyAtm)
                btnSave.isVisible = false
                btnCancel.isVisible = false
                btnUpdate.isVisible = true
                btnDelete.isVisible = true
                tilAddress.editText?.setText(cajeroEntity!!.direccion)
                tilLatitude.editText?.setText(cajeroEntity!!.latitud.toString())
                tilLongitude.editText?.setText(cajeroEntity!!.longitud.toString())
            }
        }

        binding.btnSave.setOnClickListener {
            val cajero = CajeroEntity(
                direccion = binding.tilAddress.editText?.text.toString(),
                latitud = binding.tilLatitude.editText?.text.toString().toDouble(),
                longitud = binding.tilLongitude.editText?.text.toString().toDouble(),
                zoom = ""
            )

            val queue = LinkedBlockingQueue<Long>()
            Thread {
                val id = BancoApplication.database.cajeroDao().addCajero(cajero)
                queue.add(id)
            }.start()

            atmActivity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frgContainer, AtmFragment())
                .commit()
        }

        binding.btnCancel.setOnClickListener {
            atmActivity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.frgContainer, AtmFragment())
                .commit()
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(cajeroEntity: CajeroEntity) =
            AtmManagementFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CAJERO_ENTITY, cajeroEntity)
                }
            }
    }
}