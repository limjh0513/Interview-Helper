package kr.hs.dgsw.juyeop.interview.di.module

import dagger.Module
import dagger.Provides
import kr.hs.dgsw.juyeop.data.repository.AdviceRepositoryImpl
import kr.hs.dgsw.juyeop.data.repository.AuthRepositoryImpl
import kr.hs.dgsw.juyeop.data.repository.QuestionRepositoryImpl
import kr.hs.dgsw.juyeop.domain.repository.AdviceRepository
import kr.hs.dgsw.juyeop.domain.repository.AuthRepository
import kr.hs.dgsw.juyeop.domain.repository.QuestionRepository
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
}