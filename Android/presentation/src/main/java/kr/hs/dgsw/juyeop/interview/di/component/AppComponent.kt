package kr.hs.dgsw.juyeop.interview.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import kr.hs.dgsw.juyeop.interview.widget.InterviewApplication
import kr.hs.dgsw.juyeop.interview.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBindingModule::class,
        AppModule::class,
        FragmentBindingModule::class,
        NetworkModule::class,
        RemoteModule::class,
        RepositoryModule::class,
        ServiceBindingModule::class,
        AndroidInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<InterviewApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}