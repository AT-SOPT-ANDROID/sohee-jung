package org.sopt.at.presentation.signup

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.at.common.ValidationResult
import org.sopt.at.data.request.SignUpRequestDto
import org.sopt.at.repository.AuthRepository

class SignUpViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState())
    val uiState: StateFlow<SignUpState> = _uiState.asStateFlow()

    private val authRepository = AuthRepository()

    fun signUp(
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        viewModelScope.launch {
            val signUpRequest = SignUpRequestDto(
                loginId = uiState.value.id,
                password = uiState.value.password,
                nickname = uiState.value.nickname
            )

            val signUpResult = authRepository.signUp(signUpRequest)
            Log.d("SignUpResult", "결과: $signUpResult")

            if (signUpResult.isSuccess) {
                Log.i("SignUp", "회원가입 성공")
                onSuccess()
            } else {
                onFailure(signUpResult.exceptionOrNull()?.message ?: "회원가입에 실패하였습니다.")
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

    fun updateNickname(value: String) {
        _uiState.update {
            it.copy(
                nickname = value
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

    fun validateId(): ValidationResult {
        val id = _uiState.value.id
        return when {
            id.length < 8 -> ValidationResult.Error("아이디는 8자 이상으로 입력해주세요.")
            id.length > 20 -> ValidationResult.Error("아이디는 20자 이하로 입력해주세요.")
            !id.matches(Regex("^[a-zA-Z0-9]+$")) -> ValidationResult.Error("아이디 형식을 맞춰주세요.")
            else -> ValidationResult.Success
        }
    }

    fun validatePassword(): ValidationResult {
        val password = _uiState.value.password
        return when {
            password.length < 8 -> ValidationResult.Error("비밀번호는 8자 이상으로 입력해주세요.")
            password.length > 20 -> ValidationResult.Error("비밀번호는 20자 이하로 입력해주세요.")
            !password.matches(Regex("^[a-zA-Z0-9]+$")) -> ValidationResult.Error("비밀번호 형식을 맞춰주세요.")
            else -> ValidationResult.Success
        }
    }

    fun validateNickname(): ValidationResult {
        val nickname = _uiState.value.nickname
        return when {
            nickname.length > 20 -> ValidationResult.Error("닉네임은 20자 이하로 입력해주세요.")
            !nickname.matches(Regex("^[a-zA-Z0-9]+$")) -> ValidationResult.Error("닉네임 형식을 맞춰주세요.")
            else -> ValidationResult.Success
        }
    }
}