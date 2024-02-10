package com.example.bordoiotask.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bordoiotask.data.response.Response
import com.example.bordoiotask.domain.use_case.BordoHomeUseCase
import com.example.bordoiotask.presentation.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val bordoHomeUseCase: BordoHomeUseCase) : ViewModel(){

    private val _state = MutableStateFlow(HomeState())
    val state : StateFlow<HomeState> = _state

    init {
        getHomeData()
    }

    fun getHomeData() = viewModelScope.launch (Dispatchers.IO){
        bordoHomeUseCase.bordoGetHome().collectLatest {
            when(it){
                is Response.Loading->{
                    _state.update { homeState->
                        homeState.copy(
                            loading = true
                        )
                    }
                }

                is Response.Error->{
                    _state.update { homeState->
                        homeState.copy(
                            loading = false,
                            error = it.message
                        )
                    }
                }

                else->{
                    _state.update { homeState->
                        homeState.copy(
                            loading = false,
                            error = "",
                            homeData = it.data
                        )
                    }
                }
            }
        }
    }
}