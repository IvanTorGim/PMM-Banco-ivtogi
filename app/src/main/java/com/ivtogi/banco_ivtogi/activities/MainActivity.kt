package com.ivtogi.banco_ivtogi.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.ivtogi.banco_ivtogi.R
import com.ivtogi.banco_ivtogi.databinding.ActivityMainBinding
import com.ivtogi.banco_ivtogi.pojo.Cliente

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var user: Cliente

    private lateinit var drawerLayout: DrawerLayout

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
        drawerLayout = binding.drawerLayout

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
        binding.btnExit.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }

            R.id.nav_position -> {
                val intent = Intent(this, GlobalPositionActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }

            R.id.nav_movements -> {
                val intent = Intent(this, MovementsActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }

            R.id.nav_transfers -> {
                val intent = Intent(this, TransferActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_password -> {
                val intent = Intent(this, ChangePasswordActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }

            R.id.nav_logout -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        // Para cerrar el menu cada vez que se pulse una opción
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            onBackPressedDispatcher.onBackPressed()
    }

}