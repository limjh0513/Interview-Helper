package kr.hs.dgsw.juyeop.domain.usecase.user

import io.reactivex.Single
import kr.hs.dgsw.juyeop.domain.base.ParamUseCase
import kr.hs.dgsw.juyeop.domain.entity.User
import kr.hs.dgsw.juyeop.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
): ParamUseCase<GetUserUseCase.Params, Single<User>>() {

    override fun buildUseCaseObservable(params: Params): Single<User> {
        return userRepository.getUser(params.userId)
    }

    data class Params(
        val userId: String
    )
}