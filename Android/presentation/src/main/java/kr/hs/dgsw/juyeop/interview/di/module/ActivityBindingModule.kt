package kr.hs.dgsw.juyeop.interview.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.hs.dgsw.juyeop.interview.di.scope.PerActivity
import kr.hs.dgsw.juyeop.interview.view.*

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
    abstract fun bindingSignUpActivity(): SignUpActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingSignUpCompleteActivity(): SignUpCompleteActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingMainActivity(): MainActivity
}