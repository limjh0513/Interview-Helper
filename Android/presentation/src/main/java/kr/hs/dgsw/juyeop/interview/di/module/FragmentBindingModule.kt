package kr.hs.dgsw.juyeop.interview.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.hs.dgsw.juyeop.interview.di.scope.PerFragment
import kr.hs.dgsw.juyeop.interview.view.dialog.AudioRecordDialog
import kr.hs.dgsw.juyeop.interview.view.dialog.VideoCheckDialog
import kr.hs.dgsw.juyeop.interview.view.dialog.VideoTakeDialog
import kr.hs.dgsw.juyeop.interview.view.fragment.*

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

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingVideoTakeDialog(): VideoTakeDialog

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingRankFragment(): RankFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingAdviceFragment(): AdviceFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingRecruitFragment(): RecruitFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingRecommandFragment(): RecommandFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingVideoCheckDialog(): VideoCheckDialog
}