package kr.hs.dgsw.juyeop.interview.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.hs.dgsw.juyeop.interview.di.scope.PerActivity
import kr.hs.dgsw.juyeop.interview.view.MainActivity

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingMainActivity(): MainActivity
}