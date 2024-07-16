package com.example.remote.mapper

import com.example.data.model.UserEntity
import com.example.remote.model.UserModel

internal object UserMapper {
    fun mapFromRemote(type:UserModel):UserEntity{
        return UserEntity(
            name =type.name,
            email =type.email
        )
    }
}