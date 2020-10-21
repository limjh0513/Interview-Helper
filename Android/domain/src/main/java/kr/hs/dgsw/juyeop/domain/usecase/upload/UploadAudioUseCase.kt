package kr.hs.dgsw.juyeop.domain.usecase.upload

import io.reactivex.Single
import kr.hs.dgsw.juyeop.domain.base.ParamUseCase
import kr.hs.dgsw.juyeop.domain.repository.UploadRepository
import java.io.File
import javax.inject.Inject

class UploadAudioUseCase @Inject constructor(
    private val uploadRepository: UploadRepository
): ParamUseCase<UploadAudioUseCase.Params, Single<String>>() {

    override fun buildUseCaseObservable(params: Params): Single<String> {
        return uploadRepository.uploadAudio(params.audio)
    }

    data class Params(
        val audio: File
    )
}