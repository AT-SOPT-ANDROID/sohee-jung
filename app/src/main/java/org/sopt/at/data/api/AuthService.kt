package org.sopt.at.data.api

import org.sopt.at.data.response.BaseResponse
import org.sopt.at.data.response.GetMyNicknameResponseDto
import org.sopt.at.data.response.SignInResponseDto
import org.sopt.at.data.response.SignUpResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/signup")
    suspend fun signUp(
        @Body signUpResponseDto: SignUpResponseDto
    ): BaseResponse<SignUpResponseDto>

    @POST("/api/v1/auth/signin")
    suspend fun signIn(
        @Body signInResponseDto: SignInResponseDto
    ): BaseResponse<SignInResponseDto>

    @GET("/api/v1/users/me")
    suspend fun getMyNickname(
        @Header("userId") userId: String
    ): BaseResponse<GetMyNicknameResponseDto>
}