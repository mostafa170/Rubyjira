package com.devartlab.rubyjira.app.presentation.showProfile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devartlab.rubyjira.data.models.DefaultResponse
import com.devartlab.rubyjira.domain.entities.user.UserEntities
import com.devartlab.rubyjira.domain.usecases.logout.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowProfileViewModel @Inject constructor(private val logoutUseCase: LogoutUseCase):ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _logout=MutableLiveData<DefaultResponse>()
    val logout:LiveData<DefaultResponse>
    get() = _logout

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error
    fun onErrorMessageShown() {
        _error.value = null
    }

    private val _profile = MutableLiveData<UserEntities>()
    val profile: LiveData<UserEntities>
        get() = _profile

    private val _goToEditProfile = MutableLiveData<Boolean>()
    val goToEditProfile: LiveData<Boolean>
        get() = _goToEditProfile

    fun onGoToEditProfileClicked(){
        _goToEditProfile.value = true
    }
    fun onGoToEditProfileNavigated() {
        _goToEditProfile.value = false
    }

    private val _goToChangePassword = MutableLiveData<Boolean>()
    val goToChangePassword: LiveData<Boolean>
        get() = _goToChangePassword

    fun onGoToChangePasswordClicked(){
        _goToChangePassword.value = true
    }
    fun onGoToChangePasswordNavigated() {
        _goToChangePassword.value = false
    }

    fun getLogoutApi(){
            viewModelScope.launch {
                _loading.postValue(true)
                logoutUseCase.invoke().fold({
                    _error.postValue(it.toErrorString())
                    Log.e("TAG", "getLogout: ${it.toErrorString()}")
                },{
                    _logout.postValue(it)
                })
                _loading.postValue(false)
        }
    }
    fun setProfile(){
        _profile.value = UserEntities.getSavedProfile()
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}