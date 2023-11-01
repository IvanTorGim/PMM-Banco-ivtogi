package com.ivtogi.banco_ivtogi

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ivtogi.banco_ivtogi.databinding.ActivityTransferBinding

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
            binding.apply {
                etAmount.text.clear()
                etDestinationAccount.text.clear()
                cbProof.isChecked = false
            }
        }
    }

    private fun accept() {
        binding.btnSend.setOnClickListener {
            var accountOrigin: String
            var accountDestination: String
            var amount: String
            var coin: String
            var message: String

            binding.apply {
                accountOrigin = sprOriginAccount.selectedItem.toString()

                accountDestination =
                    if (rbOwnAccount.isChecked) sprDestinationAccount.selectedItem.toString()
                    else etDestinationAccount.text.toString()

                amount = etAmount.text.toString()

                coin = sprCoin.selectedItem.toString()

                message = if (cbProof.isChecked) getString(
                    R.string.accept_proof_message, accountOrigin, accountDestination, amount, coin
                )
                else getString(
                    R.string.accept_message, accountOrigin, accountDestination, amount, coin
                )
            }

            if (accountOrigin.isNotBlank() && accountDestination.isNotBlank()
                && amount.isNotBlank() && coin.isNotBlank()
            )
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun typeAccount() {
        binding.apply {
            rbOwnAccount.setOnClickListener {
                etDestinationAccount.visibility = View.INVISIBLE
                sprDestinationAccount.visibility = View.VISIBLE
            }
            rbThirdPartyAccount.setOnClickListener {
                sprDestinationAccount.visibility = View.INVISIBLE
                etDestinationAccount.visibility = View.VISIBLE
            }
        }


    }

    private fun loadCoinSpinner() {
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, getCoins())
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