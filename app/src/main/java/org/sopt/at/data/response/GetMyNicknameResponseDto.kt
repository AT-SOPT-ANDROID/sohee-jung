package org.sopt.at.data.response

import kotlinx.serialization.SerialName

data class GetMyNicknameResponseDto(
    @SerialName("nickname")
    val nickname: String
)