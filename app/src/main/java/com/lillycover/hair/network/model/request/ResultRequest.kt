package com.lillycover.hair.network.model.request

import android.graphics.Bitmap

data class ResultRequest(
    val name: String,
    val file0: Bitmap,
    val file1: Bitmap,
    val file2: Bitmap,
    val file3: Bitmap,
    val file4: Bitmap,
    val file5: Bitmap
)