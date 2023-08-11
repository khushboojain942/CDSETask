package com.task.cdse.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.task.cdse.data.DataOrException
import com.task.cdse.data.User
import com.task.cdse.utils.Actions
import com.task.cdse.utils.HelperClass


class SplashActivity : AppCompatActivity() {
    @JvmField
     var splashViewModel: SplashViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        checkIfUserIsAuthenticated()
    }

    private fun checkIfUserIsAuthenticated() {
        val isUserAuthenticated = splashViewModel?.checkIfUserIsAuthenticated()
        if (isUserAuthenticated == true) {
//            val uid = splashViewModel?.uid
            Actions.gotoMainActivity(this, null)
        } else {
            Actions.gotoAuthActivity(this)
        }
    }

    private fun getUserData(uid: String?) {
        splashViewModel?.uid  = uid
        splashViewModel?.userLiveData?.observe(this) { dataOrException: DataOrException<User, Exception>? ->
            if (dataOrException?.data != null) {
                val user = dataOrException.data
                Actions.gotoMainActivity(this, user)
            }
            if (dataOrException?.exception != null) {
                HelperClass.logErrorMessage(dataOrException.exception?.message)
            }
        }
    }
}