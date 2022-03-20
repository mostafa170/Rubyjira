package com.devartlab.rubyjira.app.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devartlab.rubyjira.data.utils.UN_AUTH
import com.devartlab.rubyjira.domain.entities.project.MyProjectEntities
import com.devartlab.rubyjira.domain.entities.tasks.MytasksEntities
import com.devartlab.rubyjira.domain.usecases.home.MyProjectsUseCase
import com.devartlab.rubyjira.domain.usecases.home.MyTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val myProjectsUseCase: MyProjectsUseCase,
    private val myTasksUseCase: MyTasksUseCase
) : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _unauthenticated = MutableLiveData<Boolean>()
    val unauthenticated: LiveData<Boolean>
        get() = _unauthenticated

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    fun onErrorMessageShown() {
        _error.value = null
    }

    private val _selectProject = MutableLiveData<Boolean>()
    val selectProject: LiveData<Boolean>
        get() = _selectProject

    private val _selectStatusTask = MutableLiveData<Boolean>()
    val selectStatusTask: LiveData<Boolean>
        get() = _selectStatusTask

    private val _myTasks = MutableLiveData<MytasksEntities>()
    val myTask: LiveData<MytasksEntities>
        get() = _myTasks

    private val _selectProjectID = MutableLiveData<String>()
    fun setSelectProjectId(value: String) {
        _selectProjectID.value = value
        getMyTaskApi()
    }

    private val _selectTaskStatus = MutableLiveData<String>()
    fun setSelectTaskStatus(value: String) {
        _selectTaskStatus.value = value
        getMyTaskApi()
    }

    private val _myProject = MutableLiveData<List<MyProjectEntities>?>()
    val myProject: LiveData<List<MyProjectEntities>?>
        get() = _myProject

    init {
        getMyTaskApi()
    }

    fun getMyTaskApi() {
        viewModelScope.launch {
            _loading.postValue(true)
            myTasksUseCase.invoke(_selectProjectID.value.toString(), "", _selectTaskStatus.value.toString()).fold({
                if (it.toErrorString() == UN_AUTH) _unauthenticated.postValue(true)
                else _error.postValue(it.toErrorString())
            }, {
                _myTasks.postValue(it)
            })
            _loading.postValue(false)

        }
    }

    fun getSelectProjectApi() {
        viewModelScope.launch {
            _loading.postValue(true)
            myProjectsUseCase.invoke().fold({
                if (it.toErrorString() == UN_AUTH) _unauthenticated.postValue(true)
                else _error.postValue(it.toErrorString())
            }, {
                _myProject.postValue(it)
            })
            _loading.postValue(false)

        }
    }

    fun onSelectProjectClicked() {
        _selectProject.value = true
    }

    fun onSelectProjectDone() {
        _selectProject.value = false
    }

    fun onSelectTaskClicked() {
        _selectStatusTask.value = true
    }

    fun onSelectTaskDone() {
        _selectStatusTask.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}