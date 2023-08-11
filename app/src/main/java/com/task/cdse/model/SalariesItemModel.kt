package com.task.cdse.model

import com.google.gson.annotations.SerializedName
class SalariesItemModel(
    @SerializedName("emp_no") var empNo: String?,
    @SerializedName("salary") var salary: String?,
    @SerializedName("from_date") var fromDate: String?,
    @SerializedName("to_date") var toDate: String?
)