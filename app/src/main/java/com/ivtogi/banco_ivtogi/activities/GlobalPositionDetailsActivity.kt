package com.ivtogi.banco_ivtogi.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.ivtogi.banco_ivtogi.R
import com.ivtogi.banco_ivtogi.databinding.ActivityGlobalPositionBinding
import com.ivtogi.banco_ivtogi.fragments.AccountsMovementsFragment
import com.ivtogi.banco_ivtogi.pojo.Cuenta

class GlobalPositionDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGlobalPositionBinding

    private lateinit var fragmentManager: FragmentManager
    private lateinit var accountsMovementsFragment: AccountsMovementsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val account: Cuenta = intent.getSerializableExtra("account") as Cuenta

        accountsMovementsFragment = AccountsMovementsFragment.newInstance(account)
        fragmentManager = supportFragmentManager

        fragmentManager.beginTransaction().replace(
            R.id.frgGlobalPosition,
            accountsMovementsFragment,
            AccountsMovementsFragment::class.java.name
        ).commit()

    }

}