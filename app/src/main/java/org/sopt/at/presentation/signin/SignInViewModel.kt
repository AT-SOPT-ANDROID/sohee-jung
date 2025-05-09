package org.sopt.at.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.at.data.request.SignInRequestDto
import org.sopt.at.local.datastore.UserLocalDataStore
import org.sopt.at.repository.AuthRepository

class SignInViewModel(
    private val userLocalDataStore: UserLocalDataStore
) : ViewModel() {

    private val _uiState: MutableStateFlow<SignInState> = MutableStateFlow(SignInState())
    val uiState: StateFlow<SignInState> = _uiState.asStateFlow()

    private val authRepository = AuthRepository()

    fun signIn(
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        viewModelScope.launch {
            val signInRequest = SignInRequestDto(
                loginId = uiState.value.id,
                password = uiState.value.password
            )

            val signInResult = authRepository.signIn(signInRequest)

            if (signInResult.isSuccess) {
                val userId = signInResult.getOrNull()?.userId ?: -1
                userLocalDataStore.saveUserId(userId)
                onSuccess()
            } else {
                onFailure(signInResult.exceptionOrNull()?.message ?: "회원가입에 실패하였습니다.")
            }

        }
    }

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