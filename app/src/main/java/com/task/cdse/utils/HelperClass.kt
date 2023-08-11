package com.task.cdse.utils

import android.util.Log

object HelperClass {
    fun logErrorMessage(errorMessage: String?) {
        Log.d(Constants.Companion.TAG, errorMessage!!)
    }
}