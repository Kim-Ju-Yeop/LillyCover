package com.lillycover.hair.widget.extension

import android.content.Intent
import android.provider.Settings
import androidx.fragment.app.Fragment

fun Fragment.startWifiSetting() {
    val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
    startActivity(intent)
}

fun Fragment.startActivity(activity: Class<*>) {
    val intent = Intent(context, activity)
    startActivity(intent)
}