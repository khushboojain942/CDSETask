package com.task.cdse.utils

import android.app.Activity
import android.content.Intent
import com.task.cdse.auth.AuthActivity
import com.task.cdse.data.User
import com.task.cdse.main.MainActivity

object Actions {
    fun gotoMainActivity(activity: Activity, user: User?) {
        val intent = Intent(activity, MainActivity::class.java)
        intent.putExtra(Constants.USER, user)
        activity.startActivity(intent)
        activity.finish()
    }

    fun gotoAuthActivity(activity: Activity) {
        val intent = Intent(activity, AuthActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }
}