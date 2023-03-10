package com.example.instagram.common.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Post(
    val uuid: String,
    val uri: Uri,
    val caption: String,
    val timestamp: Long,
    val publisher: UserAuth
)
