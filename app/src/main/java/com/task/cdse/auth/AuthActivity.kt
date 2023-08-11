package com.task.cdse.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.task.cdse.R
import com.task.cdse.data.DataOrException
import com.task.cdse.data.User
import com.task.cdse.databinding.ActivityAuthBinding
import com.task.cdse.utils.Actions
import com.task.cdse.utils.Constants
import com.task.cdse.utils.HelperClass

 class AuthActivity : AppCompatActivity() {
    @JvmField
     var googleSignInClient: GoogleSignInClient? = null

    @JvmField
     var authViewModel: AuthViewModel? = null
    private var activityAuthBinding: ActivityAuthBinding? = null
    private var progressBar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAuthBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        progressBar = activityAuthBinding?.progressBar
        initGoogleSignInClient()
        initSignInButton()
    }

    private fun initSignInButton() {
        activityAuthBinding?.googleSignInButton?.setOnClickListener { view: View? -> signIn() }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient?.signInIntent
        startActivityForResult(signInIntent, Constants.Companion.RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.Companion.RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val googleSignInAccount = task.getResult(
                    ApiException::class.java
                )
                googleSignInAccount?.let { getGoogleAuthCredential(it) }
            } catch (e: ApiException) {
                HelperClass.logErrorMessage(e.message)
            }
        }
    }

    private fun getGoogleAuthCredential(googleSignInAccount: GoogleSignInAccount) {
        val googleTokenId = googleSignInAccount.idToken
        val googleAuthCredential = GoogleAuthProvider.getCredential(googleTokenId, null)
        signInWithGoogleAuthCredential(googleAuthCredential)
    }

    private fun signInWithGoogleAuthCredential(googleAuthCredential: AuthCredential) {
        displayProgressBar()
        authViewModel?.signInWithGoogle(googleAuthCredential)
        authViewModel?.authenticatedUserLiveData?.observe(this) { dataOrException: DataOrException<User, Exception>? ->
            if (dataOrException?.data != null) {
                val authenticatedUser = dataOrException.data
                if (authenticatedUser?.isNew == true) {
                    createNewUser(authenticatedUser)
                } else {
                    Actions.gotoMainActivity(this, authenticatedUser)
                    hideProgressBar()
                }
            }
            if (dataOrException?.exception != null) {
                HelperClass.logErrorMessage(dataOrException.exception?.message)
            }
        }
    }

    private fun createNewUser(authenticatedUser: User?) {
        displayProgressBar()
        authViewModel?.createUser(authenticatedUser)
        authViewModel?.createdUserLiveData?.observe(this) { dataOrException: DataOrException<User, Exception>? ->
            if (dataOrException?.data != null) {
                val createdUser = dataOrException.data
                Actions.gotoMainActivity(this, createdUser)
                hideProgressBar()
            }
            if (dataOrException?.exception != null) {
                HelperClass.logErrorMessage(dataOrException.exception?.message)
            }
        }
    }

    private fun displayProgressBar() {
        if (progressBar?.visibility == View.GONE) {
            progressBar?.visibility = View.VISIBLE
        }
    }

    private fun hideProgressBar() {
        if (progressBar?.visibility == View.VISIBLE) {
            progressBar?.visibility = View.GONE
        }
    }


     private fun initGoogleSignInClient() {
         val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
             .requestIdToken(getString(R.string.default_web_client_id))
             .requestEmail()
             .build()

         googleSignInClient = GoogleSignIn.getClient(this, gso)
     }
}