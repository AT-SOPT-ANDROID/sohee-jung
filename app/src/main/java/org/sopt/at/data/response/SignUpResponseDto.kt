package org.sopt.at.data.response

import kotlinx.serialization.SerialName

data class SignUpResponseDto(
    @SerialName("userId")
    val userId: Long,
    @SerialName("nickname")
    val nickname: String
)
