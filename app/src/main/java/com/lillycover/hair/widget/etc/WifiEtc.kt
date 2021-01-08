package com.lillycover.hair.widget.etc

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.SupplicantState
import android.net.wifi.WifiManager
import androidx.core.app.ActivityCompat

fun isLillyCoverSSID(context: Context, activity: Activity): Boolean {
    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
    }
    else {
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo = wifiManager.connectionInfo

        if (wifiInfo.supplicantState == SupplicantState.COMPLETED) {
            val SSID = wifiInfo.ssid
            return SSID.contains("LILLYCOVER")
        }
    }
    return false
}