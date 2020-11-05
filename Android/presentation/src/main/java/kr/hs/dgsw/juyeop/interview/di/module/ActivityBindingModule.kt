package kr.hs.dgsw.juyeop.interview.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.hs.dgsw.juyeop.interview.di.scope.PerActivity
import kr.hs.dgsw.juyeop.interview.view.activity.*

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
    abstract fun bindingQuestionReplyActivity(): QuestionReplyActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingMyQuestionActivity(): MyQuestionActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingMyQuestionReplyActivity(): MyQuestionReplyActivity
}