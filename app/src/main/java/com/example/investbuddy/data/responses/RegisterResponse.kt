package com.example.investbuddy.data.responses

data class RegisterResponse(
    val email: String,
    val id: Int,
    val is_active: Boolean,
    val is_superuser: Boolean,
    val is_verified: Boolean,
    val username: String
)