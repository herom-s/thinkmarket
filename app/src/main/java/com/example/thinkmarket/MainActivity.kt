package com.example.thinkmarket

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.thinkmarket.data.AppContainer
import com.example.thinkmarket.data.DefaultAppContainer
import com.example.thinkmarket.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var container: AppContainer
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_cad_empresa, R.id.nav_perfil_usuario
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        changeDrawerState()
    }

    private fun changeDrawerState() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        navHostFragment.navController.addOnDestinationChangedListener(listener = { _, destination, _ ->
            //set in case app asked for verify auth, signIn page drawer will be locked
            if (destination.id == R.id.nav_login || destination.id == R.id.nav_cad_usuario) {
                binding.appBarMain.toolbar.visibility = View.INVISIBLE
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            } else if (drawer.getDrawerLockMode(GravityCompat.START) != DrawerLayout.LOCK_MODE_UNLOCKED) {
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                binding.appBarMain.toolbar.visibility = View.VISIBLE
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_main_drawer, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}