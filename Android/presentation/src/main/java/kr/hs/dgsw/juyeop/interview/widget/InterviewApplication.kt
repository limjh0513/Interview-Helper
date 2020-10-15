package kr.hs.dgsw.juyeop.interview.widget

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kr.hs.dgsw.juyeop.interview.di.injector.ApplicationInjector
import javax.inject.Inject

class InterviewApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        ApplicationInjector().init(this)
    }
}