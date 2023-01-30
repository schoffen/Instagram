package com.example.instagram.common.model

import android.net.Uri

data class Post(
    val uuid: String,
    val uri: Uri,
    val caption: String,
    val timestamp: Long
)