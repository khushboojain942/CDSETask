package com.task.cdse.model

import com.google.gson.annotations.SerializedName

class DepartmentItemModel(
    @SerializedName("dept_name") var deptName: String?,
    @SerializedName("dept_no") var deptNo: String?
)