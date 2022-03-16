package com.devartlab.rubyjira.app.presentation.main

import android.app.Application
import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel (private val application: Application, private val resources: Resources): ViewModel(){


    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = _showLoading

    private val _showSuccess = MutableLiveData<String?>()
    val showSuccess: LiveData<String?>
        get() = _showSuccess

    private val showLogOutDialog = MutableLiveData<Boolean>()
    val showLogOutDialogLiveData: LiveData<Boolean>
        get() = showLogOutDialog

    private val _currentScreen = MutableLiveData<Int>()
    val currentScreen: LiveData<Int>
        get() = _currentScreen

    private val _showBottomNavigation = MutableLiveData<Boolean>()
    val showBottomNavigation: LiveData<Boolean>
        get() = _showBottomNavigation

    fun onBottomNavigationClicked(position: Int){
        _currentScreen.value = position
    }

    fun setShowBottomNavigation(value: Boolean) {
        _showBottomNavigation.value = value
    }

    fun setCurrentScreen(screen: Int) {
        _currentScreen.value = screen
    }
    fun showLogOutDialog(){
        Log.e("TAG", "showLogOutDialog: " )
        showLogOutDialog.value = true
    }
    fun onShowLogOutDialogDone(){
        showLogOutDialog.value = false
    }


    init {

    }

    fun showLoading(){
        _showLoading.value = true
    }
    fun stopLoading(){
        _showLoading.value = false
    }

    fun showSuccessMessage(message: String){
        _showSuccess.value = message
    }

    class Factory(private val application: Application, private val resources: Resources) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(application,resources) as T
            }
            throw IllegalArgumentException("Unable to construct viewModel")
        }
    }
}