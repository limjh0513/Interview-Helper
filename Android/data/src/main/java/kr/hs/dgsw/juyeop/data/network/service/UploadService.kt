package kr.hs.dgsw.juyeop.data.network.service

import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.util.Response
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadService {

    @Multipart
    @POST("upload/audio")
    fun uploadAudio(@Part audio: MultipartBody): Single<retrofit2.Response<Response<String>>>

    @Multipart
    @POST("upload/video")
    fun uploadVideo(@Part video: MultipartBody): Single<retrofit2.Response<Response<String>>>
}