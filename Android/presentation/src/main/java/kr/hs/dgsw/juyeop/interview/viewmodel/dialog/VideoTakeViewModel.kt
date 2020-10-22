package kr.hs.dgsw.juyeop.interview.viewmodel.dialog

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.hardware.Camera
import android.media.CamcorderProfile
import android.media.MediaRecorder
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.provider.MediaStore
import android.view.SurfaceHolder
import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseViewModel
import kr.hs.dgsw.juyeop.interview.widget.SingleLiveEvent
import kr.hs.dgsw.juyeop.interview.widget.manager.SharedPreferencesManager
import java.io.File

class VideoTakeViewModel(
    private val context: Context
) : BaseViewModel() {

    lateinit var surfaceHolder: SurfaceHolder
    lateinit var mediaRecorder: MediaRecorder
    lateinit var camera: Camera

    var recordKind = 0
    var mediaFileName = ""

    val timerCount = MutableLiveData("0")

    val onRecordEvent = SingleLiveEvent<Unit>()
    val onCancelEvent = SingleLiveEvent<Unit>()

    init {
        fileSetting()
    }

    fun fileSetting() {
        val directory = File(Environment.getExternalStorageDirectory().absolutePath + File.separator + "video")
        if (!directory.exists()) directory.mkdirs()

        mediaFileName = directory.path + File.separator + System.currentTimeMillis() + ".mp4"
    }
    fun mediaRecorderSetting() {
        camera.unlock()
        mediaRecorder.setCamera(camera)
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA)
        mediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P))
        mediaRecorder.setOrientationHint(270)
        mediaRecorder.setOutputFile(mediaFileName)
        mediaRecorder.setPreviewDisplay(surfaceHolder.surface)
    }

    fun recordEvent() {
        if (recordKind == 0) {
            startRecord()
            onRecordEvent.call()
        }
        else stopRecord()
    }
    fun cancelEvent() {
        onCancelEvent.call()
    }

    fun startRecord() {
        mediaRecorder = MediaRecorder()
        mediaRecorderSetting()

        mediaRecorder.prepare()
        mediaRecorder.start()

        recordKind = 1
        startTimer()
    }
    fun stopRecord() {
        mediaRecorder.stop()
        mediaRecorder.release()

        val values = ContentValues(10)
        values.put(MediaStore.MediaColumns.TITLE, "Record")
        values.put(MediaStore.Audio.Media.ALBUM, "Video Album")
        values.put(MediaStore.Audio.Media.ARTIST, SharedPreferencesManager.getUserId(context))
        values.put(MediaStore.Audio.Media.DISPLAY_NAME, "Recorded Video")
        values.put(MediaStore.Audio.Media.DATE_ADDED, System.currentTimeMillis())
        values.put(MediaStore.Audio.Media.MIME_TYPE, "video/mp4")
        values.put(MediaStore.Audio.Media.DATA, mediaFileName)

        context.contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values)
        onCancelEvent.call()
    }
    fun startTimer() {
        var counter = 0
        val handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                timerCount.value = counter.toString()
                counter++

                sendEmptyMessageDelayed(0, 1000)
            }
        }
        handler.sendEmptyMessage(0)
    }
}