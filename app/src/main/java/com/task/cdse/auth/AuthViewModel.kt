package com.task.cdse.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthCredential
import com.task.cdse.data.DataOrException
import com.task.cdse.data.User

class AuthViewModel :
    ViewModel() {
    private val authRepository: AuthRepository= AuthRepository()
    var authenticatedUserLiveData: MutableLiveData<DataOrException<User, Exception>>? = null
    var createdUserLiveData: MutableLiveData<DataOrException<User, Exception>>? = null
    fun signInWithGoogle(googleAuthCredential: AuthCredential?) {
        authenticatedUserLiveData = authRepository.firebaseSignInWithGoogle(googleAuthCredential)
    }

    fun createUser(authenticatedUser: User?) {
        createdUserLiveData = authRepository.createUserInFirestore(authenticatedUser)
    }
}