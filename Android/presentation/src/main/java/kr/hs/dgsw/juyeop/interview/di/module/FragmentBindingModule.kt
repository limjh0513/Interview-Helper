package kr.hs.dgsw.juyeop.interview.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.hs.dgsw.juyeop.interview.di.scope.PerFragment
import kr.hs.dgsw.juyeop.interview.view.dialog.AudioRecordDialog
import kr.hs.dgsw.juyeop.interview.view.fragment.HomeFragment
import kr.hs.dgsw.juyeop.interview.view.fragment.MyInfoFragment
import kr.hs.dgsw.juyeop.interview.view.fragment.QuestionFragment

@Module
abstract class FragmentBindingModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingHomeFragment(): HomeFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingQuestionFragment(): QuestionFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingMyInfoFragment(): MyInfoFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingAudioRecordDialog(): AudioRecordDialog
}