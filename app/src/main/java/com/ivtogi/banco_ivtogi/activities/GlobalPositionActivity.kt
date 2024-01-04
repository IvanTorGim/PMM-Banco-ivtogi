package com.ivtogi.banco_ivtogi.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.ivtogi.banco_ivtogi.databinding.ActivityGlobalPositionBinding
import com.ivtogi.banco_ivtogi.fragments.AccountListener
import com.ivtogi.banco_ivtogi.fragments.AccountsFragment
import com.ivtogi.banco_ivtogi.fragments.AccountsMovementsFragment
import com.ivtogi.banco_ivtogi.pojo.Cliente
import com.ivtogi.banco_ivtogi.pojo.Cuenta

class GlobalPositionActivity : AppCompatActivity(), AccountListener {

    private lateinit var binding: ActivityGlobalPositionBinding
    private lateinit var accountsFragment: AccountsFragment
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client: Cliente = intent.getSerializableExtra("user") as Cliente

        fragmentManager = supportFragmentManager
        accountsFragment = AccountsFragment.newInstance(client)


        // Si no hay instancia del activity añadimos el fragment y si hay lo remplazamos
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction().add(
                binding.frgGlobalPosition.id,
                accountsFragment,
                AccountsFragment::class.java.name
            ).commit()
        } else {
            fragmentManager.beginTransaction().replace(
                binding.frgGlobalPosition.id,
                accountsFragment,
                AccountsFragment::class.java.name
            ).commit()
        }

        accountsFragment.setAccountListener(this)

    }


    override fun onAccountSelected(account: Cuenta) {
        // Si tiene frgGlobalPositionDetails es una tablet, si devuelve nulo es movil
        val tablet: Boolean = binding.frgGlobalPositionDetails?.let {
            supportFragmentManager.findFragmentById(
                it.id
            )
        } != null

        if (tablet) {
            val accountsMovementsFragment = AccountsMovementsFragment.newInstance(account)
            supportFragmentManager.beginTransaction().replace(
                binding.frgGlobalPositionDetails!!.id,
                accountsMovementsFragment,
                AccountsMovementsFragment::class.java.name
            ).commit()

        } else {
            val intent = Intent(this, GlobalPositionDetailsActivity::class.java)
            intent.putExtra("account", account)
            startActivity(intent)
        }
    }
}