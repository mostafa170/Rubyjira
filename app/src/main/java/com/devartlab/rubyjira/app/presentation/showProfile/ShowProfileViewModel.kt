package com.devartlab.rubyjira.app.presentation.showProfile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devartlab.rubyjira.data.models.LogoutResponse
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

    private val _logout=MutableLiveData<LogoutResponse>()
    val logout:LiveData<LogoutResponse>
    get() = _logout

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error
    fun onErrorMessageShown() {
        _error.value = null
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
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}