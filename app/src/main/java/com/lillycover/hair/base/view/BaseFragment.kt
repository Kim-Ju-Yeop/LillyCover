package com.lillycover.hair.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.lillycover.hair.BR
import com.lillycover.hair.R
import com.lillycover.hair.base.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType
import java.util.*

abstract class BaseFragment<VB: ViewDataBinding, VM: BaseViewModel> : Fragment() {

    private lateinit var mBinding: VB
    protected abstract val mViewModel: VM

    protected abstract fun observerViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        performDataBinding(inflater, container)
        observerViewModel()

        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }

    private fun performDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        mBinding.setVariable(BR.viewModel, mViewModel)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }

    private fun getLayoutRes(): Int {
        val split = ((Objects.requireNonNull(javaClass.genericSuperclass) as ParameterizedType).actualTypeArguments[0] as Class<*>)
            .simpleName.replace("Binding$".toRegex(), "")
            .split("(?<=.)(?=\\p{Upper})".toRegex())
            .dropLastWhile { it.isEmpty() }.toTypedArray()

        val name = StringBuilder()
        for (i in split.indices) {
            name.append(split[i].toLowerCase(Locale.ROOT))
            if (i != split.size - 1) name.append("_")
        }
        return R.layout::class.java.getField(name.toString()).getInt(R.layout::class.java)
    }
}