package com.example.investbuddy.data.responses

data class UserResponse(
    val balance: Double,
    val email: String,
    val hashed_password: String,
    val id: Int,
    val is_active: Boolean,
    val is_superuser: Boolean,
    val is_verified: Boolean,
    val registered_at: String,
    val username: String
)