package com.example.remote.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp

@IgnoreExtraProperties
data class EmptyDocModel(
    @DocumentId
    val id:String="",
    @ServerTimestamp
    val createdAt:Timestamp?=null
){
    companion object{
        const val CREATED_AT="createdAt"
    }
}
