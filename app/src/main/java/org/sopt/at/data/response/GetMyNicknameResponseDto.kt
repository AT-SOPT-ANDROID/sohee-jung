package org.sopt.at.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMyNicknameResponseDto(
    @SerialName("nickname")
    val nickname: String
)