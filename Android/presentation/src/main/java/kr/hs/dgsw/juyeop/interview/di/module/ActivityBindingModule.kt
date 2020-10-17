package kr.hs.dgsw.juyeop.interview.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.hs.dgsw.juyeop.interview.di.scope.PerActivity
import kr.hs.dgsw.juyeop.interview.view.MainActivity
import kr.hs.dgsw.juyeop.interview.view.SignInActivity
import kr.hs.dgsw.juyeop.interview.view.SplashActivity

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingSplashActivity(): SplashActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingSignInActivity(): SignInActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingMainActivity(): MainActivity
}