package kr.hs.dgsw.juyeop.interview.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.juyeop.data.repository.*
import kr.hs.dgsw.juyeop.domain.repository.*
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository {
        return authRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideQuestionRepository(questionRepositoryImpl: QuestionRepositoryImpl): QuestionRepository {
        return questionRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideAdviceRepository(adviceRepositoryImpl: AdviceRepositoryImpl): AdviceRepository {
        return adviceRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideSolutionRepository(solutionRepositoryImpl: SolutionRepositoryImpl): SolutionRepository {
        return solutionRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideUploadRepository(uploadRepositoryImpl: UploadRepositoryImpl): UploadRepository {
        return uploadRepositoryImpl
    }
}