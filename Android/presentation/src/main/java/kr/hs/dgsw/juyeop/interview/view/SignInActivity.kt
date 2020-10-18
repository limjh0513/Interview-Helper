package kr.hs.dgsw.juyeop.interview.view

import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.layout
import kotlinx.android.synthetic.main.activity_sign_up.*
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.BaseActivity
import kr.hs.dgsw.juyeop.interview.databinding.ActivitySignInBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.SignInViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.SignInViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import kr.hs.dgsw.juyeop.interview.widget.extension.shortSnackbar
import kr.hs.dgsw.juyeop.interview.widget.extension.startActivity
import kr.hs.dgsw.juyeop.interview.widget.extension.startActivityWithFinish
import javax.inject.Inject

class SignInActivity : BaseActivity<ActivitySignInBinding, SignInViewModel>() {

    @Inject
    lateinit var viewModelFactory: SignInViewModelFactory

    override val viewModel: SignInViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(viewModel) {
            onEmptyEvent.observe(this@SignInActivity, Observer {
                shortSnackbar(layout, resources.getString(R.string.empty_data))
            })
            onSuccessEvent.observe(this@SignInActivity, Observer {
                startActivityWithFinish(applicationContext, MainActivity::class.java)
            })
            onErrorEvent.observe(this@SignInActivity, Observer {
                shortSnackbar(layout, it.message.toString())
            })
            onSignUpEvent.observe(this@SignInActivity, Observer {
                startActivity(applicationContext, SignUpActivity::class.java)
            })
        }
    }
}