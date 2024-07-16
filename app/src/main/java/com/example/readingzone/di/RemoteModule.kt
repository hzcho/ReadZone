package com.example.readingzone.di

import com.example.data.repository.AuthRemote
import com.example.data.repository.BookRemote
import com.example.data.repository.CategoryRemote
import com.example.data.repository.UserRemote
import com.example.remote.repository_impl.AuthRemoteImpl
import com.example.remote.repository_impl.BookRemoteImpl
import com.example.remote.repository_impl.CategoryRemoteImpl
import com.example.remote.repository_impl.UserRemoteImpl
import com.example.remote.service.AuthService
import com.example.remote.service.BookService
import com.example.remote.service.CategoryService
import com.example.remote.service.UserService
import com.example.remote.service_impl.AuthServiceImpl
import com.example.remote.service_impl.BookServiceImpl
import com.example.remote.service_impl.CategoryServiceImpl
import com.example.remote.service_impl.UserServiceImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun provideFirestore()=FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuth()=FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideBookService(firestore: FirebaseFirestore):BookService{
        return BookServiceImpl(firestore=firestore)
    }

    @Provides
    @Singleton
    fun provideBookRemote(bookService: BookService):BookRemote{
        return BookRemoteImpl(bookService=bookService)
    }

    @Provides
    @Singleton
    fun provideUserService(firestore: FirebaseFirestore, auth:FirebaseAuth): UserService {
        return UserServiceImpl(firestore=firestore, auth=auth)
    }

    @Provides
    @Singleton
    fun provideUserRemote(userServiceImpl: UserService):UserRemote{
        return UserRemoteImpl(userService=userServiceImpl)
    }

    @Provides
    @Singleton
    fun provideAuthService(firestore: FirebaseFirestore, auth: FirebaseAuth): AuthService {
        return AuthServiceImpl(firestore=firestore, auth=auth)
    }

    @Provides
    @Singleton
    fun provideAuthRemote(authServiceImpl: AuthService):AuthRemote{
        return AuthRemoteImpl(authService=authServiceImpl)
    }

    @Provides
    @Singleton
    fun provideCategoryService(firestore: FirebaseFirestore):CategoryService{
        return CategoryServiceImpl(firestore=firestore)
    }

    @Provides
    @Singleton
    fun provideCategoryRemote(categoryService:CategoryService):CategoryRemote{
        return CategoryRemoteImpl(categoryService=categoryService)
    }
}