package kr.hs.dgsw.juyeop.interview.view.activity

import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_sign_up.*
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.view.BaseActivity
import kr.hs.dgsw.juyeop.interview.databinding.ActivitySignUpBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.activity.SignUpViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.activity.SignUpViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import kr.hs.dgsw.juyeop.interview.widget.extension.shortSnackbar
import kr.hs.dgsw.juyeop.interview.widget.extension.startActivityWithFinish
import javax.inject.Inject

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {

    @Inject
    lateinit var viewModelFactory: SignUpViewModelFactory

    override val viewModel: SignUpViewModel
        get() = getViewModel(viewModelFactory)

    /*
        아실 것 같긴한데, 안드로이드 화면에서 키보드 올라왔을 때 화면 가리는게 불편해서 찾아보니까 해결법이 있긴 하더라고요
        이미 아셨던거면 말고 ㅎㅎ
        https://inma06.tistory.com/68
     */

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