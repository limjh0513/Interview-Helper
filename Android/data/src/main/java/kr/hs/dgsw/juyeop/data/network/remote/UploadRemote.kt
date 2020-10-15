package kr.hs.dgsw.juyeop.data.network.remote

import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.base.BaseRemote
import kr.hs.dgsw.juyeop.data.network.service.UploadService
import okhttp3.MultipartBody

class UploadRemote(override val service: UploadService) : BaseRemote<UploadService>() {

    fun uploadAudio(audio: MultipartBody): Single<String> {
        return service.uploadAudio(audio).map(getResponse())
    }

    fun uploadVideo(video: MultipartBody): Single<String> {
        return service.uploadVideo(video).map(getResponse())
    }
}