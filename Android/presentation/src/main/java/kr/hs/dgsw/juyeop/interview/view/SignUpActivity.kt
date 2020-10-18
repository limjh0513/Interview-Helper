package kr.hs.dgsw.juyeop.interview.view

import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_sign_up.*
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.BaseActivity
import kr.hs.dgsw.juyeop.interview.databinding.ActivitySignUpBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.SignUpViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.SignUpViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import kr.hs.dgsw.juyeop.interview.widget.extension.shortSnackbar
import kr.hs.dgsw.juyeop.interview.widget.extension.startActivityWithFinish
import javax.inject.Inject

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {

    @Inject
    lateinit var viewModelFactory: SignUpViewModelFactory

    override val viewModel: SignUpViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(viewModel) {
            onEmptyEvent.observe(this@SignUpActivity, Observer {
                shortSnackbar(layout, resources.getString(R.string.empty_data))
            })
            onCompleteEvent.observe(this@SignUpActivity, Observer {
                startActivityWithFinish(applicationContext, SignUpCompleteActivity::class.java)
            })
            onErrorEvent.observe(this@SignUpActivity, Observer {
                shortSnackbar(layout, it.message.toString())
            })
        }
    }
}