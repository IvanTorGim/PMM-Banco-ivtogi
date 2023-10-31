package com.ivtogi.banco_ivtogi

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ivtogi.banco_ivtogi.databinding.ActivityTransferBinding
import java.time.Duration

class TransferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransferBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadAccountSpinner()
        loadCoinSpinner()
        typeAccount()
        accept()
        cancel()
    }

    private fun cancel() {
        binding.btnCancel.setOnClickListener {
            loadAccountSpinner()
            loadCoinSpinner()
            binding.etAmount.text.clear()
            binding.etDestinationAccount.text.clear()
            binding.cbProof.isChecked = false
        }
    }

    private fun accept() {


        binding.btnSend.setOnClickListener {

            val accountOrigin = binding.sprOriginAccount.selectedItem.toString()

            val accountDestination =
                if (binding.rbOwnAccount.isChecked)
                    binding.sprDestinationAccount.selectedItem.toString()
                else
                    binding.etDestinationAccount.text.toString()

            val amount = binding.etAmount.text.toString()

            val coin = binding.sprCoin.selectedItem.toString()

            val message =
                getString(R.string.accept_message, accountOrigin, accountDestination, amount, coin)


            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun typeAccount() {
        binding.rbOwnAccount.setOnClickListener {
            binding.etDestinationAccount.visibility = View.INVISIBLE
            binding.sprDestinationAccount.visibility = View.VISIBLE
        }
        binding.rbThirdPartyAccount.setOnClickListener {
            binding.sprDestinationAccount.visibility = View.INVISIBLE
            binding.etDestinationAccount.visibility = View.VISIBLE
        }

    }

    private fun loadCoinSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getCoins())
        binding.sprCoin.adapter = adapter
    }

    private fun loadAccountSpinner() {
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getAccounts())
        binding.sprOriginAccount.adapter = adapter
        binding.sprDestinationAccount.adapter = adapter
    }

    private fun getAccounts(): List<String> {
        return listOf(
            "ES23-2323-3254-5353-2233-2323",
            "ES54-2893-9037-9143-9034-9473",
            "ES85-9595-7394-3957-9375-2693",
            "ES23-3794-2784-2390-2390-2989"
        )
    }

    private fun getCoins(): List<String> {
        return listOf(
            "€",
            "$",
            "£",
            "¥"
        )
    }


}