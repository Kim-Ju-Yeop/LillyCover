package com.lillycover.hair.widget.util

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData

object DiagnoseUtil {
    val hairBitmapList = ArrayList<Bitmap>()
    val faceBitmap = MutableLiveData<Bitmap>()
    var surveySolutionArray = arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
}