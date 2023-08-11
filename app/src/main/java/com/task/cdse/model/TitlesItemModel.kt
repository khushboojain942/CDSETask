package com.task.cdse.model

import com.google.gson.annotations.SerializedName

class TitlesItemModel(
    @SerializedName("emp_no") var empNo: String="",
    @SerializedName("title") var title: String="",
    @SerializedName("from_date") var fromDate: String="",
    @SerializedName("to_date") var toDate: String=""
)