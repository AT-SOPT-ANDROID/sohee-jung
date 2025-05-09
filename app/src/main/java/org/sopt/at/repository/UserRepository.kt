package org.sopt.at.repository

import org.sopt.at.data.api.user.UserServicePool
import org.sopt.at.data.response.GetMyNicknameResponseDto

class UserRepository {
    private val userService by lazy {
        UserServicePool.userService
    }

    suspend fun getMyNickname(userId: Long): Result<GetMyNicknameResponseDto?> {
        return try {
            val response = userService.getMyNickname(userId)
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