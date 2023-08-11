package com.task.cdse.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.cdse.data.DataOrException
import com.task.cdse.data.User

class SplashViewModel : ViewModel() {
    private val  splashRepository: SplashRepository = SplashRepository()
    var userLiveData: MutableLiveData<DataOrException<User, Exception>>? = null
    fun checkIfUserIsAuthenticated(): Boolean {
        return splashRepository.checkIfUserIsAuthenticatedInFirebase()
    }

    var uid: String?
        get() = splashRepository.firebaseUserUid
        set(uid) {
            userLiveData = splashRepository.getUserDataFromFirestore(uid)
        }
}