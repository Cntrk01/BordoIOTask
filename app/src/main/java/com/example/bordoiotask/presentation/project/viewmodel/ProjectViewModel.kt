package com.example.bordoiotask.presentation.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bordoiotask.data.response.BordoProjectsItem
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
class ProjectViewModel @Inject constructor(private val projectUseCase: BordoProjectUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow(ProjectState())
    val state: StateFlow<ProjectState> = _state

    init {
        getProjects()
    }

    fun searchProject(query: String)
    = viewModelScope.launch(Dispatchers.Default){
        _state.value.apply {
            val list = if (isSearchStarting == true) {
                projectsList
            } else {
                cachedProjectsList
            }
            println("SEARCHSTART"+isSearchStarting)
            println("LİST"+list)

            if (query.isBlank()) {
                _state.update {
                    it.copy(loading = false,
                        error = "",
                        projectsList = cachedProjectsList,
                        cachedProjectsList = emptyList(),
                        isSearchStarting = true)
                }
                return@launch //değeri return edince sorun ortadan kalktı.2.aramada  isSearchStarting = true bu değer güncellenmediği için boş gfeliyordu
            }

            val result = list?.filter {
                it.title?.contains(query.trim(), ignoreCase = true) == true || it.desc?.contains(
                    query.trim(),
                    ignoreCase = true
                ) == true
            }
            println("PROJECTLIST" + projectsList)
            println("cachedList" + cachedProjectsList)
            println("RESULT " + result)

            if (isSearchStarting == true) {
                _state.update {
                    it.copy(
                        cachedProjectsList = projectsList,
                        isSearchStarting = false
                    )
                }
            }

            if (result?.isNotEmpty() == true) {
                _state.update {
                    it.copy(
                        projectsList = result,
                        loading = false,
                        error = "",
                        isSearchStarting = false
                    )
                }
            } else {
                if (query.isNotEmpty()){
                    _state.update {
                        it.copy(
                            projectsList = emptyList(),
                            loading = false,
                            error = "Query Not Found",
                            isSearchStarting = false
                        )
                    }
                }else{
                    _state.update {
                        it.copy(
                            projectsList = cachedProjectsList,
                            loading = false,
                            error = "",
                            isSearchStarting = true
                        )
                    }
                }
            }
        }
    }

    fun getProjects() = viewModelScope.launch(Dispatchers.IO) {
        projectUseCase
            .bordoGetProject()
            .collectLatest {
                when (it) {
                    is Response.Loading -> {
                        _state.update { projectState ->
                            projectState.copy(
                                error = "",
                                loading = true
                            )
                        }
                    }

                    is Response.Error -> {
                        _state.update { projectState ->
                            projectState.copy(
                                loading = false,
                                error = it.message.toString()
                            )
                        }
                    }

                    else -> {
                        _state.update { projectState ->
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