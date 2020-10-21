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

    lateinit var file: MultipartBody.Part

    override fun uploadAudio(audio: File): Single<String> {
        setAudioToMultipartBody(audio)
        return uploadDataSource.uploadAudio(file)
    }
    override fun uploadVideo(video: File): Single<String> {
        setVideoToMultipartBody(video)
        return uploadDataSource.uploadVideo(file)
    }

    private fun setAudioToMultipartBody(audio: File) {
        val uploadName = "DA_IMG_${Random().nextInt(Int.MAX_VALUE)}"

        val extension = getExtension(audio)
        val mediaType = getMediaType(extension)
        val audioBody = RequestBody.create(mediaType.toMediaTypeOrNull(), audio)

        file = MultipartBody.Part.createFormData("audio", "$uploadName.$extension", audioBody)
    }
    private fun setVideoToMultipartBody(video: File) {
        val uploadName = "DA_IMG_${Random().nextInt(Int.MAX_VALUE)}"

        val extension = getExtension(video)
        val mediaType = getMediaType(extension)
        val audioBody = RequestBody.create(mediaType.toMediaTypeOrNull(), video)

        file = MultipartBody.Part.createFormData("video", "$uploadName.$extension", audioBody)
    }

    private fun getMediaType(extension: String): String {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension).toString()
    }
    private fun getExtension(file: File): String {
        val filenameArray: Array<String> = file.name.split(".").toTypedArray()
        return filenameArray[filenameArray.size - 1]
    }
}