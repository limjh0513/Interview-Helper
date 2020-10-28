package kr.hs.dgsw.juyeop.interview.viewmodel.fragment

import android.Manifest
import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import androidx.lifecycle.MutableLiveData
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.juyeop.domain.entity.Question
import kr.hs.dgsw.juyeop.domain.request.solution.PostSolutionReqeust
import kr.hs.dgsw.juyeop.domain.usecase.solution.PostSolutionUseCase
import kr.hs.dgsw.juyeop.domain.usecase.upload.UploadAudioUseCase
import kr.hs.dgsw.juyeop.domain.usecase.upload.UploadVideoUseCase
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseViewModel
import kr.hs.dgsw.juyeop.interview.widget.SingleLiveEvent
import kr.hs.dgsw.juyeop.interview.widget.extension.atFormat
import kr.hs.dgsw.juyeop.interview.widget.manager.SharedPreferencesManager
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class QuestionReplyViewModel(
    private val context: Context,
    private val uploadAudioUseCase: UploadAudioUseCase,
    private val uploadVideoUseCase: UploadVideoUseCase,
    private val postSolutionUseCase: PostSolutionUseCase
) : BaseViewModel() {

    lateinit var question: Question
    lateinit var audioFile: File
    lateinit var videoFile: File

    val categoryName = MutableLiveData<String>()
    val questionName = MutableLiveData<String>()

    val audioLayout = MutableLiveData(false)
    val audioName = MutableLiveData<String>()
    val audioTime = MutableLiveData<String>()

    val videoLayout = MutableLiveData(false)
    val videoName = MutableLiveData<String>()
    val videoTime = MutableLiveData<String>()

    val solutionText = MutableLiveData<String>()
    val solutionAudio = MutableLiveData<String>()
    val solutionVideo = MutableLiveData<String>()

    val onBackEvent = SingleLiveEvent<Unit>()
    val onEmptyEvent = SingleLiveEvent<Unit>()
    val onCompleteEvent = SingleLiveEvent<Unit>()
    val onAudioEvent = SingleLiveEvent<Unit>()
    val onVideoEvent = SingleLiveEvent<Unit>()
    val onAudioDeleteEvent = SingleLiveEvent<Unit>()

    fun setQuestionData(question: Question) {
        this.question = question

        categoryName.value = getCategoryName()
        questionName.value = question.question
    }
    fun setAudioData(mediaFileName: String) {
        audioFile = File(mediaFileName)
        if (audioFile.exists()) {
            val mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(mediaFileName)
            mediaPlayer.prepare()

            audioLayout.value = true
            audioName.value = audioFile.name
            audioTime.value = SimpleDateFormat("mm:ss", Locale.getDefault()).format(mediaPlayer.duration)
        } else {
            audioLayout.value = false
        }
    }
    fun setVideoData(mediaFileName: String) {
        videoFile = File(mediaFileName)
        if (videoFile.exists()) {
            val mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(mediaFileName)
            mediaPlayer.prepare()

            videoLayout.value = true
            videoName.value = videoFile.name
            videoTime.value = SimpleDateFormat("mm:ss", Locale.getDefault()).format(mediaPlayer.duration)
        } else {
            videoLayout.value = false
        }
    }

    fun saveEvent() {
        saveAudioEvent()
    }
    fun saveAudioEvent() {
        if (this::audioFile.isInitialized) {
            addDisposable(uploadAudioUseCase.buildUseCaseObservable(UploadAudioUseCase.Params(audioFile)),
                object : DisposableSingleObserver<String>() {
                    override fun onSuccess(t: String) {
                        solutionAudio.value = t
                        saveVideoEvent()
                    }
                    override fun onError(e: Throwable) {
                        onErrorEvent.value = e
                    }
                })
        } else {
            saveVideoEvent()
        }
    }
    fun saveVideoEvent() {
        if (this::videoFile.isInitialized) {
            addDisposable(uploadVideoUseCase.buildUseCaseObservable(UploadVideoUseCase.Params(videoFile)),
                object : DisposableSingleObserver<String>() {
                    override fun onSuccess(t: String) {
                        solutionVideo.value = t
                        saveSolutionEvent()
                    }
                    override fun onError(e: Throwable) {
                        onErrorEvent.value = e
                    }
                })
        } else {
            saveSolutionEvent()
        }
    }
    fun saveSolutionEvent() {
        if (!solutionText.value.isNullOrEmpty()) {
            val userId = SharedPreferencesManager.getUserId(context).toString()
            val postSolutionReqeust = PostSolutionReqeust(null, userId, question.idx, solutionText.value, solutionAudio.value, solutionVideo.value, Date().atFormat())

            addDisposable(postSolutionUseCase.buildUseCaseObservable(PostSolutionUseCase.Params(postSolutionReqeust)),
                object : DisposableCompletableObserver() {
                    override fun onComplete() {
                        onCompleteEvent.call()
                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        } else {
            onEmptyEvent.call()
        }
    }

    fun permissionSetting() {
        val permissionListener = object : PermissionListener {
            override fun onPermissionGranted() {}
            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {}
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            TedPermission.with(context)
                .setPermissionListener(permissionListener)
                .setRationaleMessage("오디오 녹음을 위해 권한이 필요합니다.")
                .setDeniedMessage("[설정] > [권한]에서 권한을 허용할 수 있습니다.")
                .setPermissions(Manifest.permission.RECORD_AUDIO)
                .check()
            TedPermission.with(context)
                .setPermissionListener(permissionListener)
                .setRationaleMessage("비디오 촬영을 위해 권한이 필요합니다.")
                .setDeniedMessage("[설정] > [권한]에서 권한을 허용할 수 있습니다.")
                .setPermissions(Manifest.permission.CAMERA)
                .check()
            TedPermission.with(context)
                .setPermissionListener(permissionListener)
                .setRationaleMessage("저장공간 접근을 위해 권한이 필요합니다.")
                .setDeniedMessage("[설정] > [권한]에서 권한을 허용할 수 있습니다.")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check()
        }
    }
    fun getCategoryName(): String {
        var categoryResource = R.string.tab_question_first
        when(question.category) {
            1 -> categoryResource = R.string.tab_question_first
            2 -> categoryResource = R.string.tab_question_second
            3 -> categoryResource = R.string.tab_question_third
            4 -> categoryResource = R.string.tab_question_fourth
            5 -> categoryResource = R.string.tab_question_fifth
            6 -> categoryResource = R.string.tab_question_sixth
            7 -> categoryResource = R.string.tab_question_seventh
        }
        return context.resources.getString(categoryResource)
    }
    fun getColorResource(): Int {
        var colorResource = R.color.colorCategory1
        when(question.category) {
            1 -> colorResource = R.color.colorCategory1
            2 -> colorResource = R.color.colorCategory2
            3 -> colorResource = R.color.colorCategory3
            4 -> colorResource = R.color.colorCategory4
            5 -> colorResource = R.color.colorCategory5
            6 -> colorResource = R.color.colorCategory6
            7 -> colorResource = R.color.colorCategory7
        }
        return colorResource
    }

    fun backEvent() {
        onBackEvent.call()
    }
    fun audioEvent() {
        onAudioEvent.call()
    }
    fun videoEvent() {
        onVideoEvent.call()
    }
    fun audioDeleteEvent() {
        audioFile.delete()
        audioLayout.value = false
    }
    fun videoDeleteEvent() {
        videoFile.delete()
        videoLayout.value = false
    }
}