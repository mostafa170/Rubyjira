package com.devartlab.rubyjira.app.presentation.editProfile

import android.content.res.Resources
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devartlab.rubyjira.R
import com.devartlab.rubyjira.data.utils.CAMERA_REQUEST_CODE1
import com.devartlab.rubyjira.data.utils.GALLERY_REQUEST_CODE1
import com.devartlab.rubyjira.data.utils.StringExpression
import com.devartlab.rubyjira.domain.entities.user.UserEntities
import com.devartlab.rubyjira.domain.usecases.updateProfileData.UpdateProfileDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val resources: Resources,private val updateProfileDataUseCase: UpdateProfileDataUseCase
) : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val updateUserRequest = UpdateUserRequest()
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    fun onErrorMessageShown() {
        _error.value = null
    }

    private val _userUpdate = MutableLiveData<UserEntities>()
    val userUpdate: LiveData<UserEntities>
        get() = _userUpdate
    private val _back = MutableLiveData<Boolean>()
    val back: LiveData<Boolean>
        get() = _back

    private val _profile = MutableLiveData<UserEntities>()
    val profile: LiveData<UserEntities>
        get() = _profile

    fun setProfile() {
        _profile.value = UserEntities.getSavedProfile()
    }

    fun onBackClicked() {
        _back.value = true
    }

    fun onBackNavigated() {
        _back.value = false
    }
    private val _profilePicture = MutableLiveData<MultipartBody.Part>()
    fun updateProfilePictureImage(bodyFile: MultipartBody.Part) {
        _profilePicture.value = bodyFile
    }
    private val _profilePictureUser = MutableLiveData<Boolean>()
    val profilePictureUser: LiveData<Boolean>
        get() = _profilePictureUser
    fun onProfilePictureClicked(){
        _profilePictureUser.value = true
    }
    fun onProfilePictureDone() {
        _profilePictureUser.value = false
    }

    private val _openCamera = MutableLiveData<Int>()
    val openCamera: LiveData<Int>
        get() = _openCamera

    fun setCameraImageNumber() {
        Log.e("TAG", "setCameraImageNumber: ${_profilePicture.value}")
        when {
            _profilePictureUser.value == true -> _openCamera.value = CAMERA_REQUEST_CODE1
        }
    }

    private val _openGallery = MutableLiveData<Int>()
    val openGallery: LiveData<Int>
        get() = _openGallery

    fun setGalleryImageNumber() {
        Log.e("TAG", "setGalleryImageNumber: ${_profilePicture.value}")
        when {
            _profilePictureUser.value == true -> _openGallery.value = GALLERY_REQUEST_CODE1
        }
    }

    fun getUserUpdateApi() {
        if (!StringExpression.isTextValid(updateUserRequest.name))
            _error.postValue(resources.getString(R.string.enter_name))
        else if (!StringExpression.isTextValid(updateUserRequest.email))
            _error.postValue(resources.getString(R.string.label_enter_your_email))
        else {
            val name: RequestBody = updateUserRequest.name
                .toRequestBody("multipart/form-data".toMediaTypeOrNull())

            val email: RequestBody = updateUserRequest.email
                .toRequestBody("multipart/form-data".toMediaTypeOrNull())
            viewModelScope.launch {
                val body: HashMap<String, RequestBody> = HashMap()
                body["name"] = name
                body["email"] = email

                _loading.postValue(true)
                updateProfileDataUseCase.invoke(body, _profilePicture.value).fold({
                    _error.postValue(it.toErrorString())
                }, {
                    _userUpdate.postValue(it)
                })
                _loading.postValue(false)
            }
        }
    }


    fun onNameChanged(s: CharSequence, start: Int, before: Int, count: Int){
        updateUserRequest.name = s.toString()
    }
    fun onEmailChanged(s: CharSequence, start: Int, before: Int, count: Int){
        updateUserRequest.email = s.toString()
    }
    private val _profilePictureImagePickedBitmap = MutableLiveData<Bitmap?>()
    val profilePictureImagePickedBitmap: LiveData<Bitmap?>
        get() = _profilePictureImagePickedBitmap

    fun setProfilePictureImageBitmap(imageBitmap: Bitmap) {
        _profilePictureImagePickedBitmap.value = imageBitmap
    }
    fun onProfilePictureImageBitmapSet(){
        _profilePictureImagePickedBitmap.value = null
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}