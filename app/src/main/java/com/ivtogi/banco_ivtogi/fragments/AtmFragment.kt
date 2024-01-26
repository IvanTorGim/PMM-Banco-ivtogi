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
import com.ivtogi.banco_ivtogi.databinding.FragmentAtmBinding
import com.ivtogi.banco_ivtogi.entities.CajeroEntity

private const val ARG_PARAM1 = "param1"

class AtmFragment : Fragment() {

    private var param1: String? = null
    private lateinit var binding: FragmentAtmBinding

    private lateinit var atmAdapter: AtmAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var atmActivity: AtmActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

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

        // TODO hay que cambiar esta lista para que la coja desde Room
        val cajerosEntityLists: MutableList<CajeroEntity> = mutableListOf(
            CajeroEntity(
                1, "Carrer del Clariano, 1, 46021 Valencia, Valencia, España",
                39.47600769999999, -0.3524475000000393, ""
            ),
            CajeroEntity(
                2, "Avinguda del Cardenal Benlloch, 65, 46021 València, Valencia, España",
                39.4710366, -0.3547525000000178, ""
            ),
            CajeroEntity(
                3, "Av. del Port, 237, 46011 València, Valencia, España",
                39.46161999999999, -0.3376299999999901, ""
            ),
            CajeroEntity(
                4, "Carrer del Batxiller, 6, 46010 València, Valencia, España",
                39.4826729, -0.3639118999999482, ""
            ),
            CajeroEntity(
                5, "Av. del Regne de València, 2, 46005 València, Valencia, España",
                39.4647669, -0.3732760000000326, ""
            )
        )

        atmAdapter = AtmAdapter(cajerosEntityLists)
        linearLayoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        binding.recycler.apply {
            adapter = atmAdapter
            layoutManager = linearLayoutManager
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            AtmFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}