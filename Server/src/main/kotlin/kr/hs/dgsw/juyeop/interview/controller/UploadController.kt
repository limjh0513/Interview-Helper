package kr.hs.dgsw.juyeop.interview.controller

import kr.hs.dgsw.juyeop.interview.model.response.JsonResponse
import kr.hs.dgsw.juyeop.interview.repository.UploadRepository
import org.apache.commons.io.FilenameUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.util.*

@RestController
class UploadController(val uploadRepository: UploadRepository) {

    @PostMapping
    @Throws(IOException::class)
    @RequestMapping("/upload/video")
    fun uploadVideo(@RequestPart("video") video: MultipartFile): HashMap<String, Any> {
        val originalVideoName = video.originalFilename
        val originalVideoExt = FilenameUtils.getExtension(originalVideoName)

        val freshVideoName = String.format("%s_%s.%s", Date().time, UUID.randomUUID(), originalVideoExt)

        val rootDestination = File("")
        val videoDestination = File(String.format("%s/public/video/%s", rootDestination.absolutePath, freshVideoName))

        video.transferTo(videoDestination)
        return JsonResponse().returnJsonResponse("200", "비디오 업로드를 정상적으로 수행하였습니다.", freshVideoName)
    }

    @PostMapping
    @Throws(IOException::class)
    @RequestMapping("/upload/audio")
    fun uploadAudio(@RequestPart("audio") audio: MultipartFile): HashMap<String, Any> {
        val originalAudioName = audio.originalFilename
        val originalAudioExt = FilenameUtils.getExtension(originalAudioName)

        val freshAudioName = String.format("%s_%s.%s", Date().time, UUID.randomUUID(), originalAudioExt)

        val rootDestination = File("")
        val audioDestination = File(String.format("%s/public/audio/%s", rootDestination.absolutePath, freshAudioName))

        audio.transferTo(audioDestination)
        return JsonResponse().returnJsonResponse("200", "오디오 업로드를 정상적으로 수행하였습니다.", freshAudioName)
    }
}