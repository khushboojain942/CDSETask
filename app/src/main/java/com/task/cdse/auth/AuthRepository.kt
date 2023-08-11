package com.task.cdse.auth

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.task.cdse.data.DataOrException
import com.task.cdse.data.User
import com.task.cdse.utils.Constants


 internal class AuthRepository {

     private val auth: FirebaseAuth = FirebaseAuth.getInstance()
     private val rootRef: FirebaseFirestore = FirebaseFirestore.getInstance()
     private val usersRef: CollectionReference = rootRef.collection(Constants.USER)

     fun firebaseSignInWithGoogle(googleAuthCredential: AuthCredential?): MutableLiveData<DataOrException<User, Exception>> {
        val dataOrExceptionMutableLiveData = MutableLiveData<DataOrException<User, Exception>>()
        auth.signInWithCredential(googleAuthCredential!!)
            .addOnCompleteListener { authTask: Task<AuthResult> ->
                val dataOrException = DataOrException<User, Exception>()
                if (authTask.isSuccessful) {
                    val isNewUser = authTask.result?.additionalUserInfo?.isNewUser
                    val firebaseUser = auth.currentUser
                    if (firebaseUser != null) {
                        val user = getUser(firebaseUser)
                        user.isNew = isNewUser == true
                        dataOrException.data = user
                    }
                } else {
                    dataOrException.exception = authTask.exception
                }
                dataOrExceptionMutableLiveData.setValue(dataOrException)
            }
        return dataOrExceptionMutableLiveData
    }

    private fun getUser(firebaseUser: FirebaseUser): User {
        val uid = firebaseUser.uid
        val name = firebaseUser.displayName
        val email = firebaseUser.email
        val photoUrl = firebaseUser.photoUrl.toString()
        return User(uid, name, email, photoUrl)
    }

    fun createUserInFirestore(authenticatedUser: User?): MutableLiveData<DataOrException<User, Exception>> {
        val dataOrExceptionMutableLiveData = MutableLiveData<DataOrException<User, Exception>>()
        val uidRef = usersRef.document(authenticatedUser?.uid!!)
        uidRef.set(authenticatedUser).addOnCompleteListener { userCreationTask: Task<Void?> ->
            val dataOrException = DataOrException<User, Exception>()
            if (userCreationTask.isSuccessful) {
                dataOrException.data = authenticatedUser
            } else {
                dataOrException.exception = userCreationTask.exception
            }
            dataOrExceptionMutableLiveData.setValue(dataOrException)
        }
        return dataOrExceptionMutableLiveData
    }
}