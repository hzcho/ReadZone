package com.example.remote.service_impl

import com.example.domain.model.Response
import com.example.domain.parameters.SignInParam
import com.example.domain.parameters.SignUpParam
import com.example.remote.model.UserModel
import com.example.remote.service.AuthService
import com.example.remote.utils.CollectionDefaults
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthServiceImpl(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
):AuthService {
    override suspend fun signUp(param: SignUpParam): Response<Boolean> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(param.email, param.password).await()

            if (authResult.user != null) {
                firestore.collection(CollectionDefaults.USERS).document(authResult.user!!.uid)
                    .set(hashMapOf(
                        UserModel.NAME to param.name,
                        UserModel.EMAIL to param.email
                    ))
                    .await()

                Response.Success(true)
            } else {
                Response.Error(error = Exception("User registration failed."))
            }
        } catch (e: Exception) {
            Response.Error(error = e)
        }
    }

    override suspend fun signIn(param: SignInParam): Response<Boolean> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(param.email, param.password).await()

            if (authResult.user != null) {
                Response.Success(true)
            } else {
                Response.Error(error = Exception("User registration failed."))
            }
        } catch (e: Exception) {
            Response.Error(error = e)
        }
    }

    override suspend fun signOut(): Response<Boolean> {
        return try {
            auth.signOut()

            when(val response=checkUserReg()){
                is Response.Error->Response.Error(response.error)
                is Response.Success->Response.Success(!response.data)
            }
        } catch (e: Exception) {
            Response.Error(error = e)
        }
    }

    override fun checkUserReg():Response<Boolean>{
        return try {
            val response=auth.currentUser?.uid != null
            Response.Success(response)
        }catch (e:Exception){
            Response.Error(error = e)
        }
    }
}