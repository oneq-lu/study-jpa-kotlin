package com.demo.study_jpa_kotlin.controller

import com.demo.study_jpa_kotlin.dto.UserCreateDto
import com.demo.study_jpa_kotlin.dto.UserDto
import com.demo.study_jpa_kotlin.dto.UserUpdateDto
import com.demo.study_jpa_kotlin.entity.User
import com.demo.study_jpa_kotlin.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserDto>> {
        val users = userService.getAllUsers()
        val userDtos = users.map { toDto(it) }
        return ResponseEntity.ok(userDtos)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserDto> {
        val user = userService.getUserById(id)
        return ResponseEntity.ok(toDto(user))
    }

    @PostMapping
    fun createUser(@RequestBody userCreateDto: UserCreateDto): ResponseEntity<UserDto> {
        val user = User(
            name = userCreateDto.name,
            email = userCreateDto.email,
            password = userCreateDto.password
        )
        val savedUser = userService.createUser(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(savedUser))
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody userUpdateDto: UserUpdateDto
    ): ResponseEntity<UserDto> {
        val user = User(
            id = id,
            name = userUpdateDto.name,
            email = userUpdateDto.email,
            password = userUpdateDto.password
        )
        val updatedUser = userService.updateUser(id, user)
        return ResponseEntity.ok(toDto(updatedUser))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Unit> {
        userService.deleteUser(id)
        return ResponseEntity.noContent().build()
    }

    private fun toDto(user: User): UserDto {
        return UserDto(
            id = user.id,
            name = user.name,
            email = user.email
        )
    }
}
