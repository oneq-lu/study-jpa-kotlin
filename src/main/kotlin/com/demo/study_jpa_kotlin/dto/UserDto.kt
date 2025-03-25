package com.demo.study_jpa_kotlin.dto

data class UserDto(
    val id: Long?,
    val name: String,
    val email: String
)

data class UserCreateDto(
    val name: String,
    val email: String,
    val password: String
)

data class UserUpdateDto(
    val name: String,
    val email: String,
    val password: String
)
