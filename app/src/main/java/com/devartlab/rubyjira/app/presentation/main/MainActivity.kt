package com.devartlab.rubyjira.app.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.devartlab.rubyjira.R
import com.devartlab.rubyjira.app.utilsView.MyDialog
import com.devartlab.rubyjira.data.utils.SharedPreferencesData
import com.devartlab.rubyjira.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainActivityEventsListener,
    NavController.OnDestinationChangedListener {
    private val myDialog: MyDialog by lazy { MyDialog(this) }
    private var currentDestinationId: Int = 0
    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, MainViewModel.Factory(activity.application, resources)).get(
            MainViewModel::class.java
        )
    }
    private val navOptions by lazy {
        NavOptions.Builder()
            .setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
            .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
            .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
            .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.executePendingBindings()

        val navController = this.findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener(this)
        viewModel.currentScreen.observe(this) {
            if (it != null) {
                when (it) {
                    0 -> if (currentDestinationId != R.id.homeFragment) navController.navigate(
                        R.id.homeFragment,
                        null,
                        navOptions
                    )
                    1 -> if (currentDestinationId != R.id.profileFragment) navController.navigate(
                        R.id.profileFragment,
                        null,
                        navOptions
                    )
                    2 -> if (currentDestinationId != R.id.notificationFragment) navController.navigate(
                        R.id.notificationFragment,
                        null,
                        navOptions
                    )
                }
            }
        }
        if (SharedPreferencesData.getAuthToken().isNotEmpty())
            navController.navigate(R.id.homeFragment, null, navOptions)
        else
            navController.navigate(R.id.loginFragment, null, navOptions)
    }

    override fun showLoading() {
        myDialog.showLoadingDialog()
    }

    override fun hideLoading() {
       myDialog.hideLoadingDialog()
    }

    override fun showErrorMessage( message: String) {
        myDialog.showErrorMessageDialog(message)
    }

    override fun showSuccessMessage( message: String) {
        myDialog.showSuccessMessageDialog(message)
    }

    override fun userUnauthenticated() {
        TODO("Not yet implemented")
    }

    override fun userLogout() {
        TODO("Not yet implemented")
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        currentDestinationId = destination.id
        when (currentDestinationId) {
            R.id.homeFragment -> {
                Log.e("TAG", "homeFragment: ")
                viewModel.setShowBottomNavigation(true)
                viewModel.setCurrentScreen(0)
            }
            R.id.profileFragment -> {
                Log.e("TAG", "profileFragment: ")
                viewModel.setShowBottomNavigation(true)
                viewModel.setCurrentScreen(1)
            }
            R.id.notificationFragment -> {
                Log.e("TAG", "notificationFragment: ")
                viewModel.setShowBottomNavigation(true)
                viewModel.setCurrentScreen(2)
            }
            else -> viewModel.setShowBottomNavigation(false)
        }
    }
}