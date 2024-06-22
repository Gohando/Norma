package com.androiddevs.norma.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.androiddevs.norma.R
import com.androiddevs.norma.databinding.ActivityMainBinding
import com.androiddevs.norma.other.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import com.androiddevs.norma.other.Constants.KEY_NAME
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.navHostFragment) }

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = sharedPreferences.getString(KEY_NAME, "")
        if (name != "") {
            binding.tvToolbarTitle.text = getString(R.string.let_s_go, name)
        }
        else {
            binding.tvToolbarTitle.text = getString(R.string.app_name)
        }
        navigateToTrackingFragmentIfNeeded(intent)

        setSupportActionBar(binding.toolbar)



        navHostFragment?.let {
            binding.bottomNavigationView.setupWithNavController(
                it.findNavController()
            )
            binding.bottomNavigationView.setOnItemReselectedListener { /* NO-OP */ }
            it.findNavController()
                .addOnDestinationChangedListener { _, destination, _ ->
                when(destination.id) {
                    R.id.settingsFragment, R.id.runFragment, R.id.statisticsFragment ->
                        binding.bottomNavigationView.visibility = View.VISIBLE
                    else -> binding.bottomNavigationView.visibility = View.GONE
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeeded(intent)
    }
    private fun navigateToTrackingFragmentIfNeeded(intent: Intent) {
        if (intent.action == ACTION_SHOW_TRACKING_FRAGMENT) {
            navHostFragment?.findNavController()?.navigate(R.id.action_global_trackingFragment)
        }
    }
}
