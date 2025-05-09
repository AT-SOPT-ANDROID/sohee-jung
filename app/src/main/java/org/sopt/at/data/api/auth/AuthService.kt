package org.sopt.at.data.api.auth

import org.sopt.at.data.request.SignInRequestDto
import org.sopt.at.data.request.SignUpRequestDto
import org.sopt.at.data.response.BaseResponse
import org.sopt.at.data.response.SignInResponseDto
import org.sopt.at.data.response.SignUpResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/signup")
    suspend fun signUp(
        @Body signUpRequestDto: SignUpRequestDto
    ): BaseResponse<SignUpResponseDto>

    @POST("/api/v1/auth/signin")
    suspend fun signIn(
        @Body signInRequestDto: SignInRequestDto
    ): BaseResponse<SignInResponseDto>
}