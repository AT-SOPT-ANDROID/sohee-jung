package org.sopt.at.presentation.signup

import androidx.compose.runtime.Immutable

@Immutable
data class SignUpState(
    val id: String = "",
    val password: String = "",
    val nickname: String = "",
    val passwordVisibility: Boolean = false,
    val isIdScreen: Boolean = true
)