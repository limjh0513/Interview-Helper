package kr.hs.dgsw.juyeop.domain.usecase

import io.reactivex.Completable
import kr.hs.dgsw.juyeop.domain.base.ParamUseCase
import kr.hs.dgsw.juyeop.domain.repository.AuthRepository
import kr.hs.dgsw.juyeop.domain.request.LoginRequest
import javax.inject.Inject

class PostLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
): ParamUseCase<PostLoginUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return authRepository.postLogin(params.loginRequest)
    }

    data class Params(
        val loginRequest: LoginRequest
    )
}