package org.sopt.at.presentation.signup

data class SignUpState(
    val id: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
    val isIdScreen: Boolean = true
)
