package com.task.cdse.main

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.task.cdse.OnAuthStateChangeListener
import com.task.cdse.model.DepartmentItemModel
import com.task.cdse.model.DepartmentManagerItemModel
import com.task.cdse.model.EmployeeDepartmentItemModel
import com.task.cdse.model.EmployeeItemModel
import com.task.cdse.model.SalariesItemModel
import com.task.cdse.model.TitlesItemModel
import com.task.cdse.sqlite.UsersDBHelper
import com.task.cdse.utils.ProgressHelper


class MainViewModel :
    ViewModel() {
    private val  mainRepository: MainRepository = MainRepository()
    private var isUserSignedOutLiveData: LiveData<Boolean?>? = null
    private val _departmentItem = MutableLiveData<List<DepartmentItemModel>>()
    val departmentList: LiveData<List<DepartmentItemModel>> = _departmentItem

    private val _departmentManagerItem = MutableLiveData<List<DepartmentManagerItemModel>>()
    val departmentManagerList: LiveData<List<DepartmentManagerItemModel>> = _departmentManagerItem

    private val _titleItem = MutableLiveData<List<TitlesItemModel>>()
    val titleList: LiveData<List<TitlesItemModel>> = _titleItem

    private val _salariesItem = MutableLiveData<List<SalariesItemModel>>()
    val salariesList: LiveData<List<SalariesItemModel>> = _salariesItem

    private val _employeeItem = MutableLiveData<List<EmployeeItemModel>>()
    val employeeList: LiveData<List<EmployeeItemModel>> = _employeeItem

    private val _employeeDepartmentItem = MutableLiveData<List<EmployeeDepartmentItemModel>>()
    val employeeDepartmentList: LiveData<List<EmployeeDepartmentItemModel>> =
        _employeeDepartmentItem
    private lateinit var usersDBHelper : UsersDBHelper

    private val  progressHelper = ProgressHelper()

    private  val employeeAdd : Boolean  = false
    private  val employeeManagmentAdd : Boolean  = false
    private  val departmentAdd : Boolean  = false
    private  val deparmanAdd : Boolean  = false
    private  val salaryAdd : Boolean  = false
    private  val titleAdd : Boolean  = false
    fun setAuthStateChangeListener(onAuthStateChangeListener: OnAuthStateChangeListener?) {
        mainRepository.onAuthStateChangeListener = onAuthStateChangeListener
    }

    fun signOut() {
        isUserSignedOutLiveData = mainRepository.signOut()
    }

    fun addAuthListener() {
        mainRepository.addFirebaseAuthListener()
    }

    fun removeAuthListener() {
        mainRepository.removeFirebaseAuthListener()
    }


      fun insertDataInSqlite(usersDBHelper :UsersDBHelper,context: Context) {
          this.usersDBHelper = usersDBHelper
          progressHelper.showDialog(context,"Loading..")
          Looper.myLooper()?.let {
              Handler(it).postDelayed({
                  progressHelper.dismissDialog()
              }, 3000)
          }
          fetchDepartment()
          getEmployee()
          getEmployeeDepartment()
          getTitles()
          getDepartmentManager()
          getSalaries()
      }
    private fun fetchDepartment() {
        viewModelScope.launch {
            try {
                val cards = mainRepository.getDepartment()
                _departmentItem.value = cards
                _departmentItem.value?.let { usersDBHelper.insertDepartment(it) }
                Log.i("success", _departmentItem.value.toString());
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error
                Log.e("error", e.message.toString());
            }
        }
    }

    private fun getDepartmentManager() {
        viewModelScope.launch {
            try {
                val cards = mainRepository.getDepartmentManager()
                _departmentManagerItem.value = cards
                _departmentManagerItem.value?.let { usersDBHelper.insertDepartmentManager(it) }
                Log.i("department manager", _departmentManagerItem.value.toString());
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error
                Log.e("error", e.message.toString());
            }
        }
    }

    private fun getTitles() {
        viewModelScope.launch {
            try {
                val cards = mainRepository.getTitles()
                _titleItem.value = cards
                _titleItem.value?.let { usersDBHelper.insertTitle(it) }
                Log.i("titles manager", _titleItem.value.toString());
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error
                Log.e("error", e.message.toString());
            }
        }
    }

    private fun getEmployee() {
        viewModelScope.launch {
            try {
                val cards = mainRepository.getEmployee()
                _employeeItem.value = cards
                _employeeItem.value?.let { usersDBHelper.insertEmployee(it) }
                Log.i("employee manager", _employeeItem.value.toString());
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error
                Log.e("error", e.message.toString());
            }
        }
    }

    private fun getEmployeeDepartment() {
        viewModelScope.launch {
            try {
                val cards = mainRepository.getEmployeeDepartment()
                _employeeDepartmentItem.value = cards
                _employeeDepartmentItem.value?.let { usersDBHelper.insertEmployeeDepartment(it) }
                Log.i("employee department manager", _employeeDepartmentItem.value.toString());
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error
                Log.e("error", e.message.toString());
            }
        }
    }

    private fun getSalaries() {
        viewModelScope.launch {
            try {
                val cards = mainRepository.getSalaries()
                _salariesItem.value = cards
                _salariesItem.value?.let { usersDBHelper.insertSalaries(it) }
                Log.i("salaries manager", _salariesItem.value.toString());
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle error
                Log.e("error", e.message.toString());
            }
        }
    }

}