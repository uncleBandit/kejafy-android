package com.pitahmzalendo.kejafy.core.media

import java.io.File

interface MediaStorage {
    suspend fun saveImage(file: File): String?
}
