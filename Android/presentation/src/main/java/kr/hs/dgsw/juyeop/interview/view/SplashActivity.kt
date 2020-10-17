package kr.hs.dgsw.juyeop.interview.view

import android.os.Handler
import androidx.lifecycle.Observer
import kr.hs.dgsw.juyeop.interview.base.BaseActivity
import kr.hs.dgsw.juyeop.interview.databinding.ActivitySplashBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.SplashViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.SplashViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import kr.hs.dgsw.juyeop.interview.widget.extension.startActivityWithFinish
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    @Inject
    lateinit var viewModelFactory: SplashViewModelFactory

    override val viewModel: SplashViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        val handler = Handler()
        var runnable: Runnable

        with(viewModel) {
            onNullEvent.observe(this@SplashActivity, Observer {
                runnable = Runnable { startActivityWithFinish(applicationContext, SignInActivity::class.java) }
                handler.postDelayed(runnable, 2000)
            })
            onNotNullEvent.observe(this@SplashActivity, Observer {
                runnable = Runnable { startActivityWithFinish(applicationContext, MainActivity::class.java) }
                handler.postDelayed(runnable, 2000)
            })
        }
    }
}