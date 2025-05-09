package org.sopt.at.data.api.user

import org.sopt.at.data.response.BaseResponse
import org.sopt.at.data.response.GetMyNicknameResponseDto
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("/api/v1/users/me")
    suspend fun getMyNickname(
        @Header("userId") userId: Long
    ): BaseResponse<GetMyNicknameResponseDto>
}