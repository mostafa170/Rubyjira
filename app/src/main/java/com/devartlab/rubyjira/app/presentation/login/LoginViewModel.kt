package com.devartlab.rubyjira.app.presentation.login

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devartlab.rubyjira.R
import com.devartlab.rubyjira.data.utils.StringExpression
import com.devartlab.rubyjira.domain.entities.user.UserEntities
import com.devartlab.rubyjira.domain.usecases.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel@Inject constructor(private val resources: Resources
                                        ,private val loginUseCase: LoginUseCase): ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val loginRequest= LoginRequest()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading


    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error
    fun onErrorMessageShown() {
        _error.value = null
    }

    private val _user = MutableLiveData<UserEntities>()
    val user: LiveData<UserEntities>
        get() = _user

    fun getLogin(){
        if(!StringExpression.isPasswordValid(loginRequest.password))
            _error.value = resources.getString(R.string.password_not_valid)
        else if (!StringExpression.isTextValid(loginRequest.email))
            _error.postValue(resources.getString(R.string.label_enter_your_email))
        else{
            viewModelScope.launch {
                _loading.postValue(true)
                loginUseCase.invoke(loginRequest).fold({
                    _error.postValue(it.toErrorString())
                    Log.e("TAG", "getLogin: ${it.toErrorString()}")
                },{
                    _user.postValue(it)
                })
                _loading.postValue(false)
            }
        }
    }

    fun onEmailChanged(s: CharSequence, start: Int, before: Int, count: Int){
        loginRequest.email = s.toString()
    }
    fun onPasswordChanged(s: CharSequence, start: Int, before: Int, count: Int){
        loginRequest.password = s.toString()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}