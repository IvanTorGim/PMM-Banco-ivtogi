package com.ivtogi.banco_ivtogi.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivtogi.banco_ivtogi.adapter.AtmAdapter
import com.ivtogi.banco_ivtogi.adapter.OnClickAtmListener
import com.ivtogi.banco_ivtogi.bd.BancoApplication
import com.ivtogi.banco_ivtogi.databinding.ActivityAtmBinding
import com.ivtogi.banco_ivtogi.entities.CajeroEntity
import java.util.concurrent.LinkedBlockingQueue

class AtmActivity : AppCompatActivity(), OnClickAtmListener {

    private lateinit var binding: ActivityAtmBinding

    private lateinit var atmAdapter: AtmAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtmBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // Usamos el onStart para que cada vez que modifiquemos en AtmManagementActivity, y volvamos
    // a esta actividad actualice el recycler
    override fun onStart() {
        super.onStart()
        initUI()
    }

    private fun initUI() {
        initRecycler()

        binding.fab.setOnClickListener {
            val intent = Intent(this, AtmManagementActivity::class.java)
            startActivity(intent)
        }

    }

    fun initRecycler() {
        val queue = LinkedBlockingQueue<MutableList<CajeroEntity>>()
        Thread {
            // Si recupera algun cajero de la base de datos los mostramos en el recycler
            val cajeroEntityList = BancoApplication.database.cajeroDao().getAllCajeros()
            queue.add(cajeroEntityList)
        }.start()

        // Si no recupera nada de la base de datos, insertamos los cajeros por defecto
        // y los mostramos en el recycler
        var cajeroList = queue.take()
        if (cajeroList.isNullOrEmpty()) {
            cajeroList = getCajerosDefault() as MutableList<CajeroEntity>
            Thread {
                BancoApplication.database.cajeroDao().insertAll(cajeroList)
            }.start()
        }

        atmAdapter = AtmAdapter(cajeroList, this)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.recycler.apply {
            adapter = atmAdapter
            layoutManager = linearLayoutManager
        }

    }

    override fun onClick(cajeroEntity: CajeroEntity) {
        val intent = Intent(this, AtmManagementActivity::class.java)
        intent.putExtra("cajero", cajeroEntity)
        startActivity(intent)
    }

    fun getCajerosDefault(): List<CajeroEntity> {
        return listOf(
            CajeroEntity(
                1,
                "Carrer del Clariano, 1, 46021 Valencia, Valencia, España",
                39.47600769999999,
                -0.3524475000000393,
                ""
            ),
            CajeroEntity(
                2,
                "Avinguda del Cardenal Benlloch, 65, 46021 València, Valencia, España",
                39.4710366,
                -0.3547525000000178,
                ""
            ),
            CajeroEntity(
                3, "Av. del Port, 237, 46011 València, Valencia, España",
                39.46161999999999, -0.3376299999999901, ""
            ),
            CajeroEntity(
                4,
                "Carrer del Batxiller, 6, 46010 València, Valencia, España",
                39.4826729,
                -0.3639118999999482,
                ""
            ),
            CajeroEntity(
                5,
                "Av. del Regne de València, 2, 46005 València, Valencia, España",
                39.4647669,
                -0.3732760000000326,
                ""
            )
        )
    }
}