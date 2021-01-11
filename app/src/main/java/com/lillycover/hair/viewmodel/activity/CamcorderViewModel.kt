package com.lillycover.hair.viewmodel.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.lillycover.hair.base.viewmodel.BaseViewModel
import com.lillycover.hair.widget.SingleLiveEvent
import dagger.hilt.android.qualifiers.ActivityContext

class CamcorderViewModel @ViewModelInject constructor(
    @ActivityContext private val context: Context
) : BaseViewModel() {

    val onLostEvent = MutableLiveData<Unit>()

    init {
        observerNetwork()
    }

    private fun observerNetwork() {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()

        networkRequest.addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        connectivityManager.registerNetworkCallback(networkRequest.build(), object: ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network) {
                onLostEvent.postValue(Unit)
            }
        })
    }
}