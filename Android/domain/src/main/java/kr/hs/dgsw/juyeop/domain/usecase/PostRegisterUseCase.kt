package kr.hs.dgsw.juyeop.domain.usecase

import io.reactivex.Completable
import kr.hs.dgsw.juyeop.domain.base.ParamUseCase
import kr.hs.dgsw.juyeop.domain.repository.AuthRepository
import kr.hs.dgsw.juyeop.domain.request.RegisterRequest
import javax.inject.Inject

class PostRegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
): ParamUseCase<PostRegisterUseCase.Params, Completable>() {

    override fun buildUseCaseObservable(params: Params): Completable {
        return authRepository.postRegister(params.registerRequest)
    }

    data class Params(
        val registerRequest: RegisterRequest
    )
}