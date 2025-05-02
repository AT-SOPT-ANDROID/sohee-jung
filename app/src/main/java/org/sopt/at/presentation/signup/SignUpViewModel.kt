package org.sopt.at.presentation.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState())
    val uiState: StateFlow<SignUpState> = _uiState.asStateFlow()

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

    fun validateId(): String? {
        return validateId(_uiState.value.id)
    }

    fun validatePassword(): String? {
        return validatePassword(_uiState.value.password)
    }

    private fun validateId(id: String): String? {
        return when {
            id.length < 6 -> "아이디는 6자 이상으로 입력해주세요."
            id.length > 12 -> "아이디는 12자 이하로 입력해주세요."
            !id.matches(Regex("^[a-zA-Z0-9]*$")) -> "아이디는 영문자와 숫자만 입력해주세요."
            !id.contains(Regex("[a-zA-Z]")) -> "아이디에 영문자를 최소 한 글자 포함해주세요."
            !id.contains(Regex("[0-9]")) -> "아이디에 숫자를 최소 한 글자 포함해주세요."
            else -> null
        }
    }

    private fun validatePassword(password: String): String? {
        return when {
            password.length < 8 -> "비밀번호는 8자 이상으로 입력해주세요."
            password.length > 15 -> "비밀번호는 15자 이하로 입력해주세요."
            !password.matches(Regex(".*[a-zA-Z0-9].*")) || !password.matches(Regex(".*[!@#^&*()].*"))
                -> "비밀번호 입력 형식을 맞춰주세요."

            else -> null
        }
    }
}