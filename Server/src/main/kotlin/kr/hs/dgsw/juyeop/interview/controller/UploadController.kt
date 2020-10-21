package kr.hs.dgsw.juyeop.interview.controller

import kr.hs.dgsw.juyeop.interview.exception.BadRequestException
import kr.hs.dgsw.juyeop.interview.model.response.JsonResponse
import org.apache.commons.io.FilenameUtils
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartResolver
import org.springframework.web.multipart.commons.CommonsMultipartResolver
import java.io.File
import java.io.IOException
import java.util.*
import javax.servlet.MultipartConfigElement

@RestController
class UploadController {

    @Bean
    fun multipartConfigElement(): MultipartConfigElement? {
        return MultipartConfigElement("")
    }
    @Bean
    fun multipartResolver(): MultipartResolver? {
        val multipartResolver = CommonsMultipartResolver()
        multipartResolver.setMaxUploadSize(1000000)
        return multipartResolver
    }

    @PostMapping
    @Throws(IOException::class)
    @RequestMapping("/upload/audio")
    fun uploadAudio(@RequestPart("audio") audio: MultipartFile): HashMap<String, Any> {
        if (checkFileEmpty(audio)) {
            val originalAudioName = audio.originalFilename
            val originalAudioExt = FilenameUtils.getExtension(originalAudioName)

            val freshAudioName = String.format("%s_%s.%s", Date().time, UUID.randomUUID(), originalAudioExt)

            val rootDestination = File("")
            val audioDestination = File(String.format("%s/public/audio/%s", rootDestination.absolutePath, freshAudioName))

            audio.transferTo(audioDestination)
            return JsonResponse().returnJsonResponse("200", "오디오 업로드를 정상적으로 수행하였습니다.", freshAudioName)
        } else throw BadRequestException("검증 오류가 발생하였습니다.")
    }

    @PostMapping
    @Throws(IOException::class)
    @RequestMapping("/upload/video")
    fun uploadVideo(@RequestPart("video") video: MultipartFile): HashMap<String, Any> {
        if (checkFileEmpty(video)) {
            val originalVideoName = video.originalFilename
            val originalVideoExt = FilenameUtils.getExtension(originalVideoName)

            val freshVideoName = String.format("%s_%s.%s", Date().time, UUID.randomUUID(), originalVideoExt)

            val rootDestination = File("")
            val videoDestination = File(String.format("%s/public/video/%s", rootDestination.absolutePath, freshVideoName))

            video.transferTo(videoDestination)
            return JsonResponse().returnJsonResponse("200", "비디오 업로드를 정상적으로 수행하였습니다.", freshVideoName)
        } else throw BadRequestException("검증 오류가 발생하였습니다.")
    }

    fun checkFileEmpty(file: MultipartFile): Boolean {
        return !file.isEmpty
    }

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handelr(error: BadRequestException): HashMap<String, Any> {
        return JsonResponse().returnJsonResponse("400", error.message.toString(), Unit)
    }
}