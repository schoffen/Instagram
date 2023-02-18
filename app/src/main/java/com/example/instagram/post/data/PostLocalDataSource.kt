package com.example.instagram.post.data

import android.content.ContentUris
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import com.example.instagram.common.model.PhotoPair

class PostLocalDataSource(private val context: Context) : PostDataSource {
    override suspend fun fetchPictures(): List<PhotoPair> {
        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.HEIGHT
        )

        val photos = mutableListOf<PhotoPair>()

        context.contentResolver.query(
            collection,
            projection,
            null,
            null,
            "${MediaStore.Images.Media._ID} DESC"
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

            while(cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)

                photos.add(PhotoPair(id, uri))

                if (photos.size == 99)
                    break
            }
        }

        return photos
    }
}