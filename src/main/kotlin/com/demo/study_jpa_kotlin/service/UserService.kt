package com.demo.study_jpa_kotlin.service

import com.demo.study_jpa_kotlin.entity.User
import com.demo.study_jpa_kotlin.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val userRepository: UserRepository) {

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getUserById(id: Long): User = userRepository.findById(id).orElseThrow {
        NoSuchElementException("User not found with id: $id")
    }

    fun createUser(user: User): User = userRepository.save(user)

    @Transactional
    fun updateUser(id: Long, user: User): User {
        val existingUser = getUserById(id)
        existingUser.name = user.name
        existingUser.email = user.email
        existingUser.password = user.password
        return userRepository.save(existingUser)
    }

    fun deleteUser(id: Long) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
        } else {
            throw NoSuchElementException("User not found with id: $id")
        }
    }
}
