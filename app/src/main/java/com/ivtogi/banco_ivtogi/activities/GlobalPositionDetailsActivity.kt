package com.ivtogi.banco_ivtogi.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.ivtogi.banco_ivtogi.R
import com.ivtogi.banco_ivtogi.databinding.ActivityGlobalPositionDetailsBinding
import com.ivtogi.banco_ivtogi.fragments.AccountsMovementsFragment
import com.ivtogi.banco_ivtogi.pojo.Cuenta

class GlobalPositionDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGlobalPositionDetailsBinding

    private lateinit var fragmentManager: FragmentManager
    private lateinit var accountsMovementsFragment: AccountsMovementsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val account: Cuenta = intent.getSerializableExtra("account") as Cuenta

        supportFragmentManager.beginTransaction().replace(
            R.id.frgGlobalPositionDetails,
            AccountsMovementsFragment.newInstance(account, -1)
        ).commit()

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_all -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frgGlobalPositionDetails,
                        AccountsMovementsFragment.newInstance(account, -1)
                    ).commit()
                    true
                }

                R.id.nav_type_0 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frgGlobalPositionDetails,
                        AccountsMovementsFragment.newInstance(account, 0)
                    ).commit()
                    true
                }

                R.id.nav_type_1 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frgGlobalPositionDetails,
                        AccountsMovementsFragment.newInstance(account, 1)
                    ).commit()
                    true
                }

                R.id.nav_type_2 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frgGlobalPositionDetails,
                        AccountsMovementsFragment.newInstance(account, 2)
                    ).commit()
                    true
                }

                else -> {
                    false
                }
            }

        }

    }

}