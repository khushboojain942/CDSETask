package com.task.cdse.main

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.task.cdse.OnAuthStateChangeListener
import com.task.cdse.R
import com.task.cdse.databinding.ActivityMainBinding
import com.task.cdse.main.adapter.SearchEmployeeListAdapter
import com.task.cdse.model.DepartmentItemModel
import com.task.cdse.model.EmployeeDepartmentItemModel
import com.task.cdse.model.EmployeeItemModel
import com.task.cdse.model.SalariesItemModel
import com.task.cdse.model.SearchEmployeeItemModel
import com.task.cdse.model.TitlesItemModel
import com.task.cdse.sqlite.DBContract
import com.task.cdse.sqlite.UsersDBHelper
import com.task.cdse.utils.Actions


class MainActivity : AppCompatActivity(), OnAuthStateChangeListener {
    @JvmField
    var googleSignInClient: GoogleSignInClient? = null

    @JvmField
    var mainViewModel: MainViewModel? = null
    private var activityMainBinding: ActivityMainBinding? = null
    lateinit var usersDBHelper: UsersDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel?.setAuthStateChangeListener(this)
        initToolBar()
        initGoogleSignInClient()
        usersDBHelper = UsersDBHelper(this)
        mainViewModel?.insertDataInSqlite(usersDBHelper, this)
        val spinnerValue = arrayOf(
            resources.getString(R.string.department),
            resources.getString(R.string.employee)
        )
        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            spinnerValue
        )

        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        activityMainBinding?.spSelectTable?.adapter = ad
        activityMainBinding?.btnFilterSubmit?.setOnClickListener {
            val selectName = activityMainBinding?.spSelectTable?.selectedItem
            val name = activityMainBinding?.etName?.text.toString()
            val searchEmployeeList: MutableList<SearchEmployeeItemModel> = ArrayList()
            println("select * from " + DBContract.DepartmentEntry.TABLE_NAME
                    + " WHERE " + DBContract.DepartmentEntry.COLUMN_DEPT_NAME + "='" + "e" + "'")
          if (!TextUtils.isEmpty(name)){
              try {
                  val cursor = usersDBHelper.readDatabaseData(name)
                  if(cursor.moveToFirst()) {
                      Toast.makeText(this, "Table Name=> "+cursor.getString(0), Toast.LENGTH_LONG).show();
                      /* if (cursor.getInt(0) > 0) {

                                  } else {
                                      Toast.makeText(this, "No Data Found", Toast.LENGTH_LONG).show()
                                  }*/
                  }else{
                      Toast.makeText(this, "No Data Found", Toast.LENGTH_LONG).show()
                  }
              }catch (e : Exception){
                  e.printStackTrace()
                  Toast.makeText(this, "Wrong query", Toast.LENGTH_LONG).show()
              }
          }else{
              Toast.makeText(this, "Please enter query", Toast.LENGTH_LONG).show()

          }

            /*if (selectName?.equals(resources.getString(R.string.department)) == true) {
                val departmentList: MutableList<DepartmentItemModel> =
                    usersDBHelper.readDepartment(name)
                for (deptItem in departmentList) {
                    val employeeManagerList: ArrayList<EmployeeDepartmentItemModel>? =
                        deptItem.deptNo?.let { it1 -> usersDBHelper.readEmployeeManager(it1) }
                    if (employeeManagerList != null) {
                        for (employeeManager in employeeManagerList) {
                            employeeManager.empNo?.let { it2 ->
                                val employeeItemModel: EmployeeItemModel =
                                    usersDBHelper.readEmployeeByIds(
                                        it2
                                    )
                                val titlesItemModel: TitlesItemModel =
                                    usersDBHelper.readTitle(
                                        it2
                                    )
                                val salariesItemModel: ArrayList<SalariesItemModel> =
                                    usersDBHelper.readSalaries(it2)
                                var finalSalary = 0
                                for (salaryItem in salariesItemModel) {
                                    finalSalary += salaryItem.salary?.toInt() ?: 0
                                }
                                searchEmployeeList.add(
                                    SearchEmployeeItemModel(
                                        employeeItemModel.empNo,
                                        employeeItemModel.firstName,
                                        employeeItemModel.lastName,
                                        employeeItemModel.birthDate,
                                        employeeItemModel.gender,
                                        employeeItemModel.hireDate,
                                        finalSalary.toString(),
                                        titlesItemModel.title
                                    )
                                )
                            }
                        }
                    }
                }
            } else {
                val employeeList: MutableList<EmployeeItemModel> =
                    usersDBHelper.readEmployee(name)

                for (employee in employeeList) {
                    employee.empNo.let { it2 ->
                        val employeeItemModel: EmployeeItemModel =
                            usersDBHelper.readEmployeeByIds(
                                it2
                            )
                        val titlesItemModel: TitlesItemModel =
                            usersDBHelper.readTitle(
                                it2
                            )
                        val salariesItemModel: ArrayList<SalariesItemModel> =
                            usersDBHelper.readSalaries(it2)
                        var finalSalary = 0
                        for (salaryItem in salariesItemModel) {
                            finalSalary += salaryItem.salary?.toInt() ?: 0
                        }
                        searchEmployeeList.add(
                            SearchEmployeeItemModel(
                                employeeItemModel.empNo,
                                employeeItemModel.firstName,
                                employeeItemModel.lastName,
                                employeeItemModel.birthDate,
                                employeeItemModel.gender,
                                employeeItemModel.hireDate,
                                finalSalary.toString(),
                                titlesItemModel.title
                            )
                        )
                    }
                }
            }*/

            if (searchEmployeeList.isNotEmpty()) {
                activityMainBinding?.tvTitleSearch?.visibility = View.VISIBLE
                activityMainBinding?.rvSearchResult?.visibility = View.VISIBLE

                activityMainBinding?.rvSearchResult?.adapter =
                    SearchEmployeeListAdapter(searchEmployeeList)
            } else {
                activityMainBinding?.tvTitleSearch?.visibility = View.GONE
                activityMainBinding?.rvSearchResult?.visibility = View.GONE
                Toast.makeText(this, "No Data Found", Toast.LENGTH_LONG).show()
            }
        }

    }


    private fun initToolBar() {
        val toolbar = activityMainBinding?.toolbar
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setHomeButtonEnabled(false);
        }
    }

    override fun onAuthStateChanged(isUserLoggedOut: Boolean) {
        Actions.gotoAuthActivity(this)
    }

    override fun onStart() {
        super.onStart()
        mainViewModel?.addAuthListener()
    }

    override fun onStop() {
        super.onStop()
        mainViewModel?.removeAuthListener()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.sign_out_button) {
            mainViewModel?.signOut()
            return true
        }
        return super.onOptionsItemSelected(menuItem)
    }

    private fun initGoogleSignInClient() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }
}