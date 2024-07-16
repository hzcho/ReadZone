package com.example.data.mapper

import com.example.data.model.UserEntity
import com.example.domain.model.UserModel

internal object UserMapper {
    fun mapFromData(type: UserEntity): UserModel {
        return UserModel(
            name = type.name,
            email = type.email
        )
    }
}