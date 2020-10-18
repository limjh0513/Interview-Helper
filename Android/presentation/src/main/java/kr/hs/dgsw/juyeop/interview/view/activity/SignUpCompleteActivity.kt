package kr.hs.dgsw.juyeop.interview.view.activity

import androidx.lifecycle.Observer
import kr.hs.dgsw.juyeop.interview.base.view.BaseActivity
import kr.hs.dgsw.juyeop.interview.databinding.ActivitySignUpCompleteBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.activity.SignUpCompleteViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.activity.SignUpCompleteViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import kr.hs.dgsw.juyeop.interview.widget.extension.startActivityWithFinish
import javax.inject.Inject

class SignUpCompleteActivity : BaseActivity<ActivitySignUpCompleteBinding, SignUpCompleteViewModel>() {

    @Inject
    lateinit var viewModelFactory: SignUpCompleteViewModelFactory

    override val viewModel: SignUpCompleteViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(viewModel) {
            onCancelEvent.observe(this@SignUpCompleteActivity, Observer {
                startActivityWithFinish(applicationContext, SignInActivity::class.java)
            })
        }
    }
}