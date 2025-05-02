package org.sopt.at.presentation.signin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<SignInState> = MutableStateFlow(SignInState())
    val uiState: StateFlow<SignInState> = _uiState.asStateFlow()

    fun updateId(value: String) {
        _uiState.update {
            it.copy(
                id = value
            )
        }
    }

    fun updatePassword(value: String) {
        _uiState.update {
            it.copy(
                password = value
            )
        }
    }

    fun updatePasswordVisibility(value: Boolean) {
        _uiState.update {
            it.copy(
                passwordVisibility = value
            )
        }
    }

}