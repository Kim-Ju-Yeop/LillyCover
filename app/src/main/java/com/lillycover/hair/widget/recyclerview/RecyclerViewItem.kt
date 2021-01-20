package com.lillycover.hair.widget.recyclerview

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.lillycover.hair.BR

data class RecyclerViewItem(val viewModel: Any, val navigator: Any?, @LayoutRes val layoutId: Int) {

    fun bind(binding: ViewDataBinding) {
        binding.setVariable(BR.viewModel, viewModel)
//        if (navigator != null) binding.setVariable(BR.navigator, navigator)
    }
}