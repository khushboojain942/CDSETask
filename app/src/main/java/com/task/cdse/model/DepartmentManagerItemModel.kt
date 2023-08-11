package com.task.cdse.model

import com.google.gson.annotations.SerializedName

class DepartmentManagerItemModel(
    @SerializedName("dept_no") var deptNo: String?,
    @SerializedName("emp_no") var empNo: String?,
    @SerializedName("from_date") var fromDate: String?,
    @SerializedName("to_date") var toDate: String?
)