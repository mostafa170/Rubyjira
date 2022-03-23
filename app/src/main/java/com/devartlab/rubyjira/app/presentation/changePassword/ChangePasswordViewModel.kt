package com.devartlab.rubyjira.app.presentation.changePassword

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devartlab.rubyjira.R
import com.devartlab.rubyjira.data.models.DefaultResponse
import com.devartlab.rubyjira.data.utils.StringExpression
import com.devartlab.rubyjira.domain.usecases.changePassword.ChangePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel@Inject constructor(private val resources: Resources
                                                 ,private val changePasswordUseCase: ChangePasswordUseCase): ViewModel(){

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val changePasswordRequest=ChangePasswordRequest()

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

    fun onBackClicked(){
        _back.value = true
    }
    fun onBackNavigated() {
        _back.value = false
    }

    private val _changePassword = MutableLiveData<DefaultResponse>()
    val changePassword: LiveData<DefaultResponse>
        get() = _changePassword


    fun getPasswordChanged(){
        if(!StringExpression.isPasswordValid(changePasswordRequest.password))
            _error.value = resources.getString(R.string.password_not_valid)
        else if (!StringExpression.isTextValid(changePasswordRequest.passwordConfirmation))
            _error.postValue(resources.getString(R.string.password_not_valid))
        else if (!StringExpression.isEqual(changePasswordRequest.password,changePasswordRequest.passwordConfirmation))
            _error.postValue(resources.getString(R.string.password_not_match))
        else{
            viewModelScope.launch {
                _loading.postValue(true)
                changePasswordUseCase.invoke(changePasswordRequest).fold({
                    _error.postValue(it.toErrorString())
                    Log.e("TAG", "getPasswordChanged: ${it.toErrorString()}")
                },{
                    _changePassword.postValue(it)
                })
                _loading.postValue(false)
            }
        }
    }
    fun onPasswordChanged(s: CharSequence, start: Int, before: Int, count: Int){
        changePasswordRequest.password = s.toString()
    }
    fun onPasswordConfirmationChanged(s: CharSequence, start: Int, before: Int, count: Int){
        changePasswordRequest.passwordConfirmation = s.toString()
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}