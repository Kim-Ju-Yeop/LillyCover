package com.lillycover.hair.viewmodel.activity

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.lillycover.hair.R
import com.lillycover.hair.base.viewmodel.BaseViewModel
import com.lillycover.hair.widget.SingleLiveEvent
import dagger.hilt.android.qualifiers.ActivityContext

class CameraViewModel @ViewModelInject constructor(
    @ActivityContext private val context: Context
) : BaseViewModel() {

    val isTake = MutableLiveData<Boolean>()
    val buttonText = MutableLiveData<String>()

    val onTakeEvent = SingleLiveEvent<Unit>()
    val onCheckEvent = SingleLiveEvent<Unit>()

    init {
        buttonText.value = context.resources.getString(R.string.text_take)
    }

    fun takeEvent() {
        isTake.value = true
        buttonText.value = context.resources.getString(R.string.text_retake)
        onTakeEvent.call()
    }
    fun checkEvent() {
        onCheckEvent.call()
    }
}