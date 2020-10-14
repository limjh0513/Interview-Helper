package kr.hs.dgsw.juyeop.interview.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Singleton
    @Provides
    fun bindContext(application: Application): Context = application

    @Singleton
    @Provides
    fun bindCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Singleton
    @Provides
    fun bindAny(): Any = Any()
}