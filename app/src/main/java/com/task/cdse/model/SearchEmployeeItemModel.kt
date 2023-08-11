package com.task.cdse.model

import com.google.gson.annotations.SerializedName

class SearchEmployeeItemModel(
    @SerializedName("emp_no") var empNo: String = "",
    @SerializedName("first_name") var firstName: String = "",
    @SerializedName("last_name") var lastName: String = "",
    @SerializedName("birth_date") var birthDate: String = "",
    @SerializedName("gender") var gender: String = "",
    @SerializedName("hire_date") var hireDate: String = "",
    @SerializedName("salary") var salary: String = "",
    @SerializedName("title") var title: String = ""
)