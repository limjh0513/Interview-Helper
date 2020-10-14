package kr.hs.dgsw.juyeop.interview.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import kr.hs.dgsw.juyeop.interview.InterviewApplication
import kr.hs.dgsw.juyeop.interview.di.module.ActivityBindingModule
import kr.hs.dgsw.juyeop.interview.di.module.AppModule
import kr.hs.dgsw.juyeop.interview.di.module.FragmentBindingModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBindingModule::class,
        AppModule::class,
        FragmentBindingModule::class
    ]
)

interface AppComponent : AndroidInjector<InterviewApplication>{

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}