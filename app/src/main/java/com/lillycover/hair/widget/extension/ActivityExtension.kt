package com.lillycover.hair.widget.extension

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.startActivityWithFinish(activity: Class<*>) {
    val intent = Intent(this, activity).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    startActivity(intent)
}