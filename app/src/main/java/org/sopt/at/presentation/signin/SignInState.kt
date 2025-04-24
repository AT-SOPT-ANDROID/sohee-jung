package org.sopt.at.presentation.signin

data class SignInState(
    val id: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
)
