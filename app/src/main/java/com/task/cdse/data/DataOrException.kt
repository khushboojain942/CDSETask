package com.task.cdse.data

class DataOrException<T, E : Exception?> {
    var data: T? = null
    var exception: E? = null
}