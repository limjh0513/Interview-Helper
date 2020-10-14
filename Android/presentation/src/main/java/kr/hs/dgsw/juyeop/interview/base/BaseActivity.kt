package kr.hs.dgsw.juyeop.interview.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kr.hs.dgsw.juyeop.interview.BR
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.di.component.DaggerAppComponent
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*

abstract class BaseActivity<VB: ViewDataBinding, VM: BaseViewModel>: DaggerAppCompatActivity() {

    private lateinit var mBinding: VB
    private lateinit var mViewModel: VM

    protected abstract val viewModel: VM
    protected abstract fun observerViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = 0

        performDataBinding()
        observerViewModel()
    }

    private fun performDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, layoutRes())
        mViewModel = if (::mViewModel.isInitialized) mViewModel else viewModel
        mBinding.setVariable(BR.viewModel, mViewModel)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mBinding.isInitialized) mBinding.unbind()
    }

    private fun layoutRes(): Int {
        val split = ((Objects.requireNonNull<Type>(javaClass.genericSuperclass) as ParameterizedType).actualTypeArguments[0] as Class<*>)
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