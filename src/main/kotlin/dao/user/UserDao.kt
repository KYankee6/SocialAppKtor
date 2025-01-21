package com.uicheon.dao.user

import com.uicheon.model.SignUpParams
import com.uicheon.model.User

interface UserDao {
    suspend fun insert(params: SignUpParams): User?
    suspend fun findByEmail(email: String): User?
}