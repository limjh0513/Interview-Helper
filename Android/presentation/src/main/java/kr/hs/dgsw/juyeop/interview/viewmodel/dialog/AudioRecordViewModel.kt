package kr.hs.dgsw.juyeop.interview.viewmodel.dialog

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.provider.MediaStore
import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseViewModel
import kr.hs.dgsw.juyeop.interview.widget.SingleLiveEvent
import kr.hs.dgsw.juyeop.interview.widget.manager.SharedPreferencesManager
import java.io.File

/**
 * 녹음이 끝난 후 QuestionReplyActivity -> mediaFileName 값을 전달해야한다.
 */

class AudioRecordViewModel(
    private val context: Context
) : BaseViewModel() {

    var recordKind = 0
    var mediaFileName = ""

    val mediaRecorder = MediaRecorder()
    val mediaPlayer = MediaPlayer()

    val timerCount = MutableLiveData("0")

    val onRecordEvent = SingleLiveEvent<Unit>()
    val onCancelEvent = SingleLiveEvent<Unit>()

    init {
        fileSetting()
        mediaRecorderSetting()
    }

    fun fileSetting() {
        val directory = File(Environment.getExternalStorageDirectory().absolutePath + File.separator + "record")
        if (!directory.exists()) directory.mkdirs()

        mediaFileName = directory.path + File.separator + System.currentTimeMillis() + ".mp3"
    }
    fun mediaRecorderSetting() {
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
        mediaRecorder.setOutputFile(mediaFileName)
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
        values.put(MediaStore.Audio.Media.ALBUM, "Audio Album")
        values.put(MediaStore.Audio.Media.ARTIST, SharedPreferencesManager.getUserId(context))
        values.put(MediaStore.Audio.Media.DISPLAY_NAME, "Recorded Audio")
        values.put(MediaStore.Audio.Media.IS_RINGTONE, 1)
        values.put(MediaStore.Audio.Media.IS_MUSIC, 1)
        values.put(MediaStore.Audio.Media.DATE_ADDED, System.currentTimeMillis())
        values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/mp4")
        values.put(MediaStore.Audio.Media.DATA, mediaFileName)

        context.contentResolver.insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values)
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