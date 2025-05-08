package org.sopt.at.data.response

import kotlinx.serialization.SerialName

data class BaseResponse<T>(
    @SerialName("success")
    val success: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: T? = null
)
