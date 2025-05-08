package org.sopt.at.data.response

import kotlinx.serialization.SerialName

data class SignInResponseDto(
    @SerialName("userId")
    val userId: Long
)