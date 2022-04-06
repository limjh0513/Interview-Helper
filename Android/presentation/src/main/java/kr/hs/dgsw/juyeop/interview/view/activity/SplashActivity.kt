package kr.hs.dgsw.juyeop.interview.view.activity

import android.os.Handler
import androidx.lifecycle.Observer
import kr.hs.dgsw.juyeop.interview.base.view.BaseActivity
import kr.hs.dgsw.juyeop.interview.databinding.ActivitySplashBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.activity.SplashViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.activity.SplashViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import kr.hs.dgsw.juyeop.interview.widget.extension.startActivityWithFinish
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    @Inject
    lateinit var viewModelFactory: SplashViewModelFactory

    override val viewModel: SplashViewModel
        get() = getViewModel(viewModelFactory)

    /*
     인터뷰헬퍼 splash 로직이랑은 관련 없긴 한데, 토이 프로젝트하면서 splash 필요할 때에 괜찮은 방법인 것 같아서 올려놔요 ㅎㅎ
     https://blog.kmshack.kr/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%98%AC%EB%B0%94%EB%A5%B8-%EC%8A%A4%ED%94%8C%EB%9E%98%EC%8B%9C-%ED%99%94%EB%A9%B4%EC%9D%84-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0-%EC%9C%84%ED%95%9C-%EB%B0%A9%EB%B2%95/
     */

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