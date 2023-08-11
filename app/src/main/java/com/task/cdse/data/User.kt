package com.task.cdse.data

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp
import java.io.Serializable
import java.util.Date

class User : Serializable {
    @JvmField
     var uid: String? = null

    @JvmField
     var name: String? = null
    @JvmField
    var photoUrl: String? = null

    @JvmField
    @get:Exclude
    var email: String? = null

    @ServerTimestamp
    var createdAt: Date? = null

    @Exclude
    var isNew = false

    constructor()
    constructor(uid: String?, name: String?, email: String?, photoUrl: String?) {
        this.uid = uid
        this.name = name
        this.email = email
        this.photoUrl = photoUrl
    }
}