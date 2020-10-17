package kr.hs.dgsw.juyeop.interview.view

import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_sign_in.*
import kr.hs.dgsw.juyeop.interview.base.BaseActivity
import kr.hs.dgsw.juyeop.interview.databinding.ActivitySignInBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.SignInViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.SignInViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import kr.hs.dgsw.juyeop.interview.widget.extension.startActivityWithFinish
import javax.inject.Inject

class SignInActivity : BaseActivity<ActivitySignInBinding, SignInViewModel>() {

    @Inject
    lateinit var viewModelFactory: SignInViewModelFactory

    override val viewModel: SignInViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(viewModel) {
            onSuccessEvent.observe(this@SignInActivity, Observer {
                startActivityWithFinish(applicationContext, MainActivity::class.java)
            })
            onErrorEvent.observe(this@SignInActivity, Observer {
                Snackbar.make(layout, it.message.toString(), Snackbar.LENGTH_LONG).show()
            })
        }
    }
}