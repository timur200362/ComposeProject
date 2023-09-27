package com.example.composeproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MainViewState(
    val title:String = "",
    val email:String = "",
    val isPassword:Boolean = false,
    val users:List<User> = listOf()
)
sealed interface MainEvent{
    data class OnEmailChange(val email:String):MainEvent
    data class OnUserClick(val user:User):MainEvent
    object OnPassClick:MainEvent
    object OnButtonClick:MainEvent
}

class MainViewModel: ViewModel(){
    private val _state = MutableStateFlow<MainViewState>(MainViewState())
    val state:StateFlow<MainViewState>
        get() = _state.asStateFlow()

    fun event(mainEvent: MainEvent){
        when (mainEvent){
            MainEvent.OnButtonClick -> TODO()
            MainEvent.OnPassClick -> onPassClick()
            is MainEvent.OnEmailChange -> onEmailChange(mainEvent)
            is MainEvent.OnUserClick -> TODO()
        }
    }
    private fun onPassClick(){
        _state.tryEmit(
            _state.value.copy(
                isPassword = _state.value.isPassword
            )
        )
    }
    private fun onEmailChange(event: MainEvent.OnEmailChange){
        _state.tryEmit(
            _state.value.copy(
                email = event.email
            )
        )
    }
}