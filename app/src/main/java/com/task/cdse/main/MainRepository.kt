package com.task.cdse.main

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.task.cdse.OnAuthStateChangeListener
import com.task.cdse.model.DepartmentItemModel
import com.task.cdse.model.DepartmentManagerItemModel
import com.task.cdse.model.EmployeeDepartmentItemModel
import com.task.cdse.model.EmployeeItemModel
import com.task.cdse.model.SalariesItemModel
import com.task.cdse.model.TitlesItemModel
import com.task.cdse.service.RetrofitInstance


class MainRepository() : AuthStateListener {
     private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var onAuthStateChangeListener: OnAuthStateChangeListener? = null

    private val apiInterfaceService = RetrofitInstance.apiService


     override fun onAuthStateChanged(auth: FirebaseAuth) {
        val firebaseUser = auth.currentUser
        if (firebaseUser == null) {
            onAuthStateChangeListener?.onAuthStateChanged(true)
        }
    }

    fun addFirebaseAuthListener() {
        auth.addAuthStateListener(this)
    }

    fun removeFirebaseAuthListener() {
        auth.removeAuthStateListener(this)
    }

    fun signOut(): MutableLiveData<Boolean> {
        val mutableLiveData = MutableLiveData<Boolean>()
        val firebaseUser = auth.currentUser
        if (firebaseUser != null) {
            firebaseSignOut()
             mutableLiveData.value = true
        }
        return mutableLiveData
    }


    private fun firebaseSignOut() {
        auth.signOut()
    }

    suspend fun getDepartment(): List<DepartmentItemModel> {
        return apiInterfaceService.getDepartment()
    }

    suspend fun getDepartmentManager(): List<DepartmentManagerItemModel> {
        return apiInterfaceService.getDepartmentManager()
    }

    suspend fun getTitles(): List<TitlesItemModel> {
        return apiInterfaceService.getTitles()
    }

    suspend fun getSalaries(): List<SalariesItemModel> {
        return apiInterfaceService.getSalaries()
    }

    suspend fun getEmployee(): List<EmployeeItemModel> {
        return apiInterfaceService.getEmployees()
    }

    suspend fun getEmployeeDepartment(): List<EmployeeDepartmentItemModel> {
        return apiInterfaceService.getEmployeeDepartment()
    }
}