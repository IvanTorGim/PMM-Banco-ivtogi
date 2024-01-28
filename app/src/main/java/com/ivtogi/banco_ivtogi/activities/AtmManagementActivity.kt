package com.ivtogi.banco_ivtogi.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.ivtogi.banco_ivtogi.R
import com.ivtogi.banco_ivtogi.bd.BancoApplication
import com.ivtogi.banco_ivtogi.databinding.ActivityAtmManagementBinding
import com.ivtogi.banco_ivtogi.entities.CajeroEntity
import java.util.concurrent.LinkedBlockingQueue

class AtmManagementActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAtmManagementBinding
    private var cajeroEntity: CajeroEntity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtmManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cajeroEntity = intent.getSerializableExtra("cajero") as CajeroEntity?

        initUI()
        buttonSave()
        buttonCancel()
        buttonUpdate()
        buttonDelete()
    }

    private fun initUI() {
        // Si hay cajeroEntity han clicado en el item modificamos la activity para update y delete,
        // si no hay cajeroEntity ha clicado en el fab y es para crear un cajero nuevo

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
    }

    private fun buttonDelete() {
        binding.btnDelete.setOnClickListener {

            val queue = LinkedBlockingQueue<Int>()
            Thread {
                val id = BancoApplication.database.cajeroDao().deleteCajero(cajeroEntity!!)
                queue.add(id)
            }.start()

            if (queue.take() > 0) {
                Toast.makeText(
                    this,
                    getText(R.string.deletedSuccessfully),
                    Toast.LENGTH_SHORT
                ).show()
            }
            finish()
        }
    }

    private fun buttonUpdate() {
        binding.btnUpdate.setOnClickListener {
            // Controlamos que en latitud y longitud sea un double
            cajeroEntity!!.apply {
                try {
                    direccion = binding.tilAddress.editText?.text.toString()
                    latitud = binding.tilLatitude.editText?.text.toString().toDouble()
                    longitud = binding.tilLongitude.editText?.text.toString().toDouble()

                } catch (e: NumberFormatException) {
                    Snackbar.make(
                        binding.root,
                        getText(R.string.numberException),
                        Snackbar.LENGTH_SHORT
                    ).setAnchorView(R.id.btnUpdate).show()
                    return@setOnClickListener
                }
            }

            val queue = LinkedBlockingQueue<Int>()
            Thread {
                val id = BancoApplication.database.cajeroDao().updateCajero(cajeroEntity!!)
                queue.add(id)
            }.start()

            if (queue.take() > 0) {
                Toast.makeText(
                    this,
                    getText(R.string.updatedSuccessfully),
                    Toast.LENGTH_SHORT
                ).show()
            }
            finish()
        }
    }

    private fun buttonSave() {

        binding.btnSave.setOnClickListener {
            val cajero: CajeroEntity
            // Controlamos que en latitud y longitud sea un double
            try {
                cajero = CajeroEntity(
                    direccion = binding.tilAddress.editText?.text.toString(),
                    latitud = binding.tilLatitude.editText?.text.toString().toDouble(),
                    longitud = binding.tilLongitude.editText?.text.toString().toDouble(),
                    zoom = ""
                )
            } catch (e: NumberFormatException) {
                Snackbar.make(
                    binding.root,
                    getText(R.string.numberException),
                    Snackbar.LENGTH_SHORT
                ).setAnchorView(R.id.btnSave).show()
                return@setOnClickListener
            }

            val queue = LinkedBlockingQueue<Long>()
            Thread {
                val id = BancoApplication.database.cajeroDao().addCajero(cajero)
                queue.add(id)
            }.start()

            if (queue.take() > 0)
                Toast.makeText(
                    this,
                    getText(R.string.createdSuccessfully),
                    Toast.LENGTH_SHORT
                ).show()

            finish()
        }
    }

    private fun buttonCancel() {
        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}