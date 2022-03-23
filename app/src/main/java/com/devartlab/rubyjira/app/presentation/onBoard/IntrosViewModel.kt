package com.devartlab.rubyjira.app.presentation.onBoard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class IntrosViewModel: ViewModel() {
    private var currentItemIndex: Int = 0

    private val _currentIntroItem = MutableLiveData<Int>()
    val currentIntroItem: LiveData<Int>
        get() = _currentIntroItem

    private val _skipIntro = MutableLiveData<Boolean>()
    val skipIntro: LiveData<Boolean>
        get() = _skipIntro

    init {
        _currentIntroItem.value = currentItemIndex
    }

    fun setCurrentIntroItem(position: Int){
        _currentIntroItem.value = position
        currentItemIndex = position
    }

    fun onSkipClicked(){
        _skipIntro.value = true
    }
    fun onIntrosSkipped() {
        _skipIntro.value = false
    }

    fun onNextClicked(){
        if(currentItemIndex < 2) {
            currentItemIndex += 1
            _currentIntroItem.value = currentItemIndex
        }else if(currentItemIndex == 2)
            onSkipClicked()
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(IntrosViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return IntrosViewModel() as T
            }
            throw IllegalArgumentException("Unable to construct viewModel")
        }
    }

}