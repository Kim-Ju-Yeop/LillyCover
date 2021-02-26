package com.lillycover.hair.viewmodel.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.widget.ImageView
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.lillycover.hair.base.viewmodel.BaseViewModel
import com.lillycover.hair.widget.SingleLiveEvent
import dagger.hilt.android.qualifiers.ActivityContext
import org.videolan.libvlc.IVLCVout
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.MediaPlayer

class CamcorderViewModel @ViewModelInject constructor(
    @ActivityContext private val context: Context
) : BaseViewModel() {

    lateinit var libVLC: LibVLC
    lateinit var mediaPlayer: MediaPlayer
    lateinit var iVLCVout: IVLCVout

    var imageViewIdx = 0
    val imageViewList = ArrayList<ImageView>()
    val isTake = MutableLiveData<Boolean>()

    val onLostEvent = SingleLiveEvent<Unit>()
    val onCreateMediaPlayerEvent = SingleLiveEvent<Unit>()
    val onTakeEvent = SingleLiveEvent<Unit>()
    val onRetakeEvent = SingleLiveEvent<Unit>()
    val onCheckEvent = SingleLiveEvent<Unit>()
    val onNextEvent = SingleLiveEvent<Unit>()

    init {
        observerNetwork()
    }

    fun createMediaPlayer() {
        if (::libVLC.isInitialized)
            releaseMediaPlayer()

        val optionList = ArrayList<String>()
        optionList.add("--aout=opensles")
        optionList.add("--audio-time-stretch")
        optionList.add("-vvv")

        libVLC = LibVLC(context, optionList)
        mediaPlayer = MediaPlayer(libVLC)
        iVLCVout = mediaPlayer.vlcVout

        onCreateMediaPlayerEvent.call()
    }
    fun releaseMediaPlayer() {
        libVLC.release()
        Thread { mediaPlayer.stop() }.start()
        iVLCVout = mediaPlayer.vlcVout
        iVLCVout.detachViews()
    }

    fun takeEvent() {
        isTake.value = true
        onTakeEvent.call()
    }
    fun retakeEvent() {
        isTake.value = false
        onRetakeEvent.call()
    }
    fun checkEvent() {
        if (imageViewIdx != imageViewList.size-1) isTake.value = false
        onCheckEvent.call()
    }

    private fun observerNetwork() {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()

        networkRequest.addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        connectivityManager.registerNetworkCallback(networkRequest.build(), object: ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network) {
                if (imageViewIdx != imageViewList.size-1) onLostEvent.postCall()
                else onNextEvent.postCall()
            }
        })
    }
}