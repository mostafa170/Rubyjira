package com.devartlab.rubyjira.app.presentation.editProfile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devartlab.rubyjira.domain.entities.user.UserEntities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
@HiltViewModel
class EditProfileViewModel @Inject constructor() : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error
    fun onErrorMessageShown() {
        _error.value = null
    }
    private val _back = MutableLiveData<Boolean>()
    val back: LiveData<Boolean>
        get() = _back

    private val _profile = MutableLiveData<UserEntities>()
    val profile: LiveData<UserEntities>
        get() = _profile
    fun setProfile(){
        _profile.value = UserEntities.getSavedProfile()
    }

    fun onBackClicked(){
        _back.value = true
    }
    fun onBackNavigated() {
        _back.value = false
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}