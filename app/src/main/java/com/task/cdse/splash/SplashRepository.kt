package com.task.cdse.splash

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.task.cdse.data.DataOrException
import com.task.cdse.data.User
import com.task.cdse.utils.Constants

class SplashRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val rootRef: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val usersRef: CollectionReference = rootRef.collection(Constants.USER)

    fun checkIfUserIsAuthenticatedInFirebase(): Boolean {
        return auth.currentUser != null
    }

    val firebaseUserUid: String
        get() {
            val firebaseUser = auth.currentUser
            return firebaseUser?.uid.toString()
        }

    fun getUserDataFromFirestore(uid: String?): MutableLiveData<DataOrException<User, Exception>> {
        val userMutableLiveData = MutableLiveData<DataOrException<User, Exception>>()
        usersRef.document(uid!!).get().addOnCompleteListener { userTask: Task<DocumentSnapshot?> ->
            val dataOrException = DataOrException<User, Exception>()
            dataOrException.exception?.printStackTrace()
            if (userTask.isSuccessful) {
                val userDoc = userTask.result
                if (userDoc?.exists() == true) {
                    dataOrException.data = userDoc.toObject(User::class.java)
                }
            } else {
                dataOrException.exception = userTask.exception
            }
            userMutableLiveData.setValue(dataOrException)
        }
        return userMutableLiveData
    }
}