package org.sopt.at.repository

import org.sopt.at.data.api.auth.AuthServicePool
import org.sopt.at.data.request.SignInRequestDto
import org.sopt.at.data.request.SignUpRequestDto
import org.sopt.at.data.response.SignInResponseDto
import org.sopt.at.data.response.SignUpResponseDto

class AuthRepository {
    private val authService by lazy {
        AuthServicePool.authService
    }

    suspend fun signUp(signUpRequestDto: SignUpRequestDto): Result<SignUpResponseDto?> {
        return try {
            val response = authService.signUp(signUpRequestDto)
            if (response.success) {
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signIn(signInRequestDto: SignInRequestDto): Result<SignInResponseDto?> {
        return try {
            val response = authService.signIn(signInRequestDto)
            if (response.success) {
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}