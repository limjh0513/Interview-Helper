package kr.hs.dgsw.juyeop.interview.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.juyeop.data.network.remote.*
import kr.hs.dgsw.juyeop.data.network.service.*
import kr.hs.dgsw.juyeop.domain.repository.SolutionRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideAuthRemote(retrofit: Retrofit): AuthRemote {
        return AuthRemote(retrofit.create(AuthService::class.java))
    }

    @Singleton
    @Provides
    fun provideQuestionRemote(retrofit: Retrofit): QuestionRemote {
        return QuestionRemote(retrofit.create(QuestionService::class.java))
    }

    @Singleton
    @Provides
    fun provideAdviceRemote(retrofit: Retrofit): AdviceRemote {
        return AdviceRemote(retrofit.create(AdviceService::class.java))
    }

    @Singleton
    @Provides
    fun provideSolutionRemote(retrofit: Retrofit): SolutionRemote {
        return SolutionRemote(retrofit.create(SolutionService::class.java))
    }

    @Singleton
    @Provides
    fun provideUploadRemote(retrofit: Retrofit): UploadRemote {
        return UploadRemote(retrofit.create(UploadService::class.java))
    }
}