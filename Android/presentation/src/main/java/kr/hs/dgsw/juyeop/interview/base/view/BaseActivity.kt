package kr.hs.dgsw.juyeop.interview.base.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity
import kr.hs.dgsw.juyeop.interview.BR
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*

abstract class BaseActivity<VB: ViewDataBinding, VM: BaseViewModel>: DaggerAppCompatActivity() {

    /*
     mBinding이 private여서 UI 코드 부분(image 및 text 변경, 색깔 등)이 전체적으로 viewModel로 몰린 것 같은데
     나중에 시간이 되신다면 설명 한번 부탁드립니다 ㅎㅎ
     */

    private lateinit var mBinding: VB
    private lateinit var mViewModel: VM

    protected abstract val viewModel: VM
    protected abstract fun observerViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStatusMode()
        performDataBinding()
        observerViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mBinding.isInitialized) mBinding.unbind()
    }

    private fun setStatusMode() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    private fun performDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, layoutRes())
        mViewModel = if (::mViewModel.isInitialized) mViewModel else viewModel
        mBinding.setVariable(BR.viewModel, mViewModel)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
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