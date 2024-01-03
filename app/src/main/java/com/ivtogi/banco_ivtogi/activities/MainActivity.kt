package com.ivtogi.banco_ivtogi.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import com.ivtogi.banco_ivtogi.R
import com.ivtogi.banco_ivtogi.databinding.ActivityMainBinding
import com.ivtogi.banco_ivtogi.pojo.Cliente

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var user: Cliente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getSerializableExtra("user") as Cliente


        navigationDrawer()
        showWelcome()
        globalPosition()
        movements()
        transferActivity()
        changePasswordActivity()
        exit()
    }

    private fun navigationDrawer() {
        val drawerLayout = binding.drawerLayout

        val appBar = binding.bottomAppBar

        val navigationView = binding.navView
        navigationView.setNavigationItemSelectedListener(this)

        val toggle =
            ActionBarDrawerToggle(this, drawerLayout, appBar, R.string.open_nav, R.string.close_nav)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }


    private fun showWelcome() {
        binding.tvUser.text = getString(R.string.welcome, user.getNombre().toString())
    }

    private fun globalPosition() {
        binding.btnPosition.setOnClickListener {
            val intent = Intent(this, GlobalPositionActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }

    private fun movements() {
        binding.btnMovement.setOnClickListener {
            val intent = Intent(this, MovementsActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }

    private fun transferActivity() {
        binding.btnTransfers.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }
    }

    private fun changePasswordActivity() {
        binding.btnPassword.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }
    }

    private fun exit() {
        binding.btnExit.setOnClickListener { finish() }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Falta hacer que navegue a las activitys cuando se pulse en cada nombre")
    }

}