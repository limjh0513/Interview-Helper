package kr.hs.dgsw.juyeop.data.repository

import android.webkit.MimeTypeMap
import io.reactivex.Single
import kr.hs.dgsw.juyeop.data.datasource.UploadDataSource
import kr.hs.dgsw.juyeop.domain.repository.UploadRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*
import javax.inject.Inject

class UploadRepositoryImpl @Inject constructor(
    private val uploadDataSource: UploadDataSource
): UploadRepository {

    lateinit var file: MultipartBody

    override fun uploadAudio(audio: File): Single<String> {
        setAudioToMultipartBody(audio)
        return uploadDataSource.uploadAudio(file)
    }

    override fun uploadVideo(video: File): Single<String> {
        setVideoToMultipartBody(video)
        return uploadDataSource.uploadVideo(file)
    }

    private fun setAudioToMultipartBody(audio: File) {
        val audioBuilder = MultipartBody.Builder().setType(MultipartBody.FORM)

        val uploadName = "DA_FILE_${Random().nextInt(Int.MAX_VALUE)}"
        val extension = audio.extension
        val mediaType = getMediaType(extension)

        val audioBody = RequestBody.create(mediaType.toMediaTypeOrNull(), audio)

        audioBuilder.addFormDataPart("audio", "$uploadName.$extension", audioBody)
        this.file = audioBuilder.build()
    }

    private fun setVideoToMultipartBody(video: File) {
        val videoBuilder = MultipartBody.Builder().setType(MultipartBody.FORM)

        val uploadName = "DA_FILE_${Random().nextInt(Int.MAX_VALUE)}"
        val extension = video.extension
        val mediaType = getMediaType(extension)

        val videoBody = RequestBody.create(mediaType.toMediaTypeOrNull(), video)

        videoBuilder.addFormDataPart("video", "$uploadName.$extension", videoBody)
        this.file = videoBuilder.build()
    }

    private fun getMediaType(extension: String): String {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension).toString()
    }
}