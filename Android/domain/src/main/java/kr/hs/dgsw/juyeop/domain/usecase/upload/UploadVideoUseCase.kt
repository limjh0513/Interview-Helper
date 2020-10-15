package kr.hs.dgsw.juyeop.domain.usecase.upload

import io.reactivex.Single
import kr.hs.dgsw.juyeop.domain.base.ParamUseCase
import kr.hs.dgsw.juyeop.domain.repository.UploadRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class UploadVideoUseCase @Inject constructor(
    private val uploadRepository: UploadRepository
): ParamUseCase<UploadVideoUseCase.Params, Single<String>>() {

    override fun buildUseCaseObservable(params: Params): Single<String> {
        return uploadRepository.uploadVideo(params.video)
    }

    data class Params(
        val video: MultipartBody
    )
}