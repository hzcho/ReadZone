package com.example.readingzone.presentation.screens.splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Response
import com.example.domain.usecase.auth.CheckUserReg
import com.example.readingzone.presentation.navigation.routes.AuthRoutes
import com.example.readingzone.presentation.navigation.routes.NavGraphRoutes
import com.example.readingzone.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkUserReg: CheckUserReg
):ViewModel() {
    private var isAuth=false
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    private val _splashState = MutableStateFlow(SplashState())
    val splashState = _splashState.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            when(val response=checkUserReg(Unit)){
                is Response.Error -> {}
                is Response.Success -> {
                    isAuth=response.data
                }
            }
        }
    }

    fun onEvent(event: SplashEvent)=with(_splashState){
        when(event){
            SplashEvent.OnFinishedAnim -> {
                if(isAuth){
                    sendUiEvent(UiEvent.NavigateAndClear(route = NavGraphRoutes.BottomNavGraph.route))
                }else{
                    sendUiEvent(UiEvent.NavigateAndClear(route = AuthRoutes.SignUp.route))
                }
            }
            SplashEvent.StartAnim -> value=value.copy(isStartAnim = true)
        }
    }

    private fun sendUiEvent(event:UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}