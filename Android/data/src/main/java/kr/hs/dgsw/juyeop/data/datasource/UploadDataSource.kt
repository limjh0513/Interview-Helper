package kr.hs.dgsw.juyeop.data.datasource

import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.base.BaseDataSource
import kr.hs.dgsw.juyeop.data.network.remote.UploadRemote
import okhttp3.MultipartBody
import javax.inject.Inject

class UploadDataSource @Inject constructor(
    override val remote: UploadRemote,
    override val cache: Any
): BaseDataSource<UploadRemote, Any>() {

    fun uploadAudio(audio: MultipartBody.Part): Single<String> {
        return remote.uploadAudio(audio)
    }

    fun uploadVideo(video: MultipartBody.Part): Single<String> {
        return remote.uploadVideo(video)
    }
}