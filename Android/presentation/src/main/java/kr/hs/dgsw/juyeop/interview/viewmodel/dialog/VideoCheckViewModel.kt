package kr.hs.dgsw.juyeop.interview.viewmodel.dialog

import android.media.MediaPlayer
import android.view.SurfaceHolder
import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseViewModel
import kr.hs.dgsw.juyeop.interview.widget.SingleLiveEvent

class VideoCheckViewModel : BaseViewModel() {

    lateinit var videoPath: String
    lateinit var surfaceHolder: SurfaceHolder
    lateinit var videoMediaPlayer: MediaPlayer

    val onCancelEvent = SingleLiveEvent<Unit>()

    fun videoMediaPlayerSetting() {
        videoMediaPlayer = MediaPlayer().apply {
            setDataSource(videoPath)
            setDisplay(surfaceHolder)
            prepare()
            start()
        }
        videoMediaPlayer.setOnCompletionListener {
            onCancelEvent.call()
        }
    }

    fun cancelEvent() {
        onCancelEvent.call()
    }
}