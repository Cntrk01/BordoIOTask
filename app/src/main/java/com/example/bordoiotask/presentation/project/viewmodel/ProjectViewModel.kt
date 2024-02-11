package com.example.bordoiotask.presentation.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bordoiotask.data.response.Response
import com.example.bordoiotask.domain.use_case.BordoProjectUseCase
import com.example.bordoiotask.presentation.project.state.ProjectState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(private val projectUseCase: BordoProjectUseCase) : ViewModel() {

    private val _state = MutableStateFlow(ProjectState())
    val state : StateFlow<ProjectState> = _state

    init {
        getProjects()
    }

    fun getProjects() = viewModelScope.launch(Dispatchers.IO){
        projectUseCase
            .bordoGetProject()
            .collectLatest {
                when(it){
                    is Response.Loading->{
                        _state.update { projectState->
                            projectState.copy(
                                error = "",
                                loading = true
                            )
                        }
                    }

                    is Response.Error->{
                        _state.update { projectState->
                            projectState.copy(
                                loading = false,
                                error = it.message.toString()
                            )
                        }
                    }

                    else->{
                        _state.update { projectState->
                            projectState.copy(
                                loading = false,
                                error = "",
                                projectsList = it.data
                            )
                        }
                    }
                }
        }
    }
}