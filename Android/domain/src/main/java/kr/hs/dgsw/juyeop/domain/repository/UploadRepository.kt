package kr.hs.dgsw.juyeop.domain.repository

import io.reactivex.Single
import java.io.File

interface UploadRepository {

    fun uploadAudio(audio: File): Single<String>

    fun uploadVideo(video: File): Single<String>
}