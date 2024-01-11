package com.codeenemy.assignmentapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.codeenemy.assignmentapp.R
import com.codeenemy.assignmentapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * [MainActivity] is the main entry point for the application.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Find the NavHostFragment and NavController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()

        // Setup Top Back button and hide the arrow for some fragments
        appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.charactersFragment,
                R.id.splashFragment
            ).build()

        // Set up the ActionBar and connect it to the NavController
        setSupportActionBar(binding.toolbarMain)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        // Navigate up in the hierarchy or handle the action as needed
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
