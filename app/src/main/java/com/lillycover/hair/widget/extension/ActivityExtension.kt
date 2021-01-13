package com.lillycover.hair.widget.extension

import android.content.Intent
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.startWifiSetting() {
    val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
    startActivity(intent)
}

fun AppCompatActivity.startActivityWithFinish(activity: Class<*>) {
    val intent = Intent(this, activity).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    startActivity(intent)
}