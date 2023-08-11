package com.task.cdse.service

import com.task.cdse.model.DepartmentItemModel
import com.task.cdse.model.DepartmentManagerItemModel
import com.task.cdse.model.EmployeeDepartmentItemModel
import com.task.cdse.model.EmployeeItemModel
import com.task.cdse.model.SalariesItemModel
import com.task.cdse.model.TitlesItemModel
import retrofit2.http.GET

interface ApiInterfaceService {
    @GET("c877182acc76d9c1b58c542181d4f2ff/raw/b19ad9784f752475f91078ac694f09dcfc731616/department_manager.json")
    suspend fun getDepartmentManager(): List<DepartmentManagerItemModel>

    @GET("c877182acc76d9c1b58c542181d4f2ff/raw/b19ad9784f752475f91078ac694f09dcfc731616/departments.json")
    suspend fun getDepartment():List<DepartmentItemModel>

    @GET("c877182acc76d9c1b58c542181d4f2ff/raw/b19ad9784f752475f91078ac694f09dcfc731616/titles.json")
    suspend fun getTitles(): List<TitlesItemModel>

    @GET("c877182acc76d9c1b58c542181d4f2ff/raw/b19ad9784f752475f91078ac694f09dcfc731616/salaries.json")
    suspend fun getSalaries(): List<SalariesItemModel>

    @GET("c877182acc76d9c1b58c542181d4f2ff/raw/b19ad9784f752475f91078ac694f09dcfc731616/employees.json")
    suspend fun getEmployees(): List<EmployeeItemModel>

    @GET("c877182acc76d9c1b58c542181d4f2ff/raw/b19ad9784f752475f91078ac694f09dcfc731616/emp_departments.json")
    suspend fun getEmployeeDepartment(): List<EmployeeDepartmentItemModel>
}