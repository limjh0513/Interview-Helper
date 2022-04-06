package kr.hs.dgsw.juyeop.interview.viewmodel.activity

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.juyeop.data.util.Constants
import kr.hs.dgsw.juyeop.domain.entity.Question
import kr.hs.dgsw.juyeop.domain.entity.Solution
import kr.hs.dgsw.juyeop.domain.entity.User
import kr.hs.dgsw.juyeop.domain.usecase.solution.DeleteSolutionUseCase
import kr.hs.dgsw.juyeop.domain.usecase.user.GetUserUseCase
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseViewModel
import kr.hs.dgsw.juyeop.interview.widget.SingleLiveEvent
import kr.hs.dgsw.juyeop.interview.widget.manager.SharedPreferencesManager
import java.text.SimpleDateFormat
import java.util.*

class MyQuestionReplyViewModel(
    private val context: Context,
    private val deleteSolutionUseCase: DeleteSolutionUseCase,
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel() {

    lateinit var question: Question
    lateinit var solution: Solution

    var audioMediaPlayer: MediaPlayer ?= null
    var videoMediaPlayer: MediaPlayer ?= null

    var audioPlayerKind = 0

    val categoryName = MutableLiveData<String>()
    val questionName = MutableLiveData<String>()
    val solutionText = MutableLiveData<String>()

    val audioLayout = MutableLiveData(false)
    val audioName = MutableLiveData<String>()
    val audioTime = MutableLiveData<String>()

    val videoLayout = MutableLiveData(false)
    val videoName = MutableLiveData<String>()
    val videoTime = MutableLiveData<String>()

    // 어디서 많이 본 플로우인 것 같은데...ㅎㅎ
    // SignleLiveEvent로 Event 처리는 지양하고 함수로 처리해주는게 훨 나은거 같아요
    val onBackEvent = SingleLiveEvent<Unit>()
    val onAudioStartEvent = SingleLiveEvent<Unit>()
    val onAudioCompleteEvent = SingleLiveEvent<Unit>()
    val onVideoPlayEvent = SingleLiveEvent<Unit>()

    // 이것도 더 좋은 방법이 있을 것 같은데 아이디어가 안 떠오르네요
    fun setData(question: Question, solution: Solution) {
        this.question = question
        this.solution = solution

        categoryName.value = getCategoryName()
        questionName.value = question.question
        solutionText.value = solution.solution_text

        if (!solution.solution_audio.isNullOrEmpty()) {
            val audioPath = "${Constants.DEFAULT_HOST}audio/${solution.solution_audio}"
            audioMediaPlayer = MediaPlayer().apply {
                setDataSource(audioPath)
                prepare()
            }
            audioLayout.value = true
            audioName.value = "${solution.solution_audio!!.substring(0, 13)}.${solution.solution_audio!!.substring(solution.solution_audio!!.length-3)}"
            audioTime.value = SimpleDateFormat("mm:ss", Locale.getDefault()).format(audioMediaPlayer!!.duration)
        }
        if (!solution.solution_video.isNullOrEmpty()) {
            val videoPath = "${Constants.DEFAULT_HOST}video/${solution.solution_video}"
            videoMediaPlayer = MediaPlayer().apply {
                setDataSource(videoPath)
                prepare()
            }
            videoLayout.value = true
            videoName.value = "${solution.solution_video!!.substring(0, 13)}.${solution.solution_video!!.substring(solution.solution_video!!.length-3)}"
            videoTime.value = SimpleDateFormat("mm:ss", Locale.getDefault()).format(videoMediaPlayer!!.duration)
        }
    }

    fun audioPlayEvent() {
        if (audioPlayerKind == 0) {
            audioMediaPlayer!!.start()
            onAudioStartEvent.call()
            audioPlayerKind = 1
        } else {
            audioMediaPlayer!!.stop()
            audioMediaPlayer!!.prepare()
            onAudioCompleteEvent.call()
            audioPlayerKind = 0
        }
            audioMediaPlayer!!.setOnCompletionListener {
            onAudioCompleteEvent.call()
            audioPlayerKind = 0
        }
    }

    fun deleteEvent() {
        isLoading.value = true
        addDisposable(deleteSolutionUseCase.buildUseCaseObservable(DeleteSolutionUseCase.Params(solution.idx)), object : DisposableCompletableObserver() {
            override fun onComplete() {
                setUsersolution()
            }
            override fun onError(e: Throwable) {
                isLoading.value = false
                onErrorEvent.value = e
            }
        })
    }
    fun setUsersolution() {
        val userId = SharedPreferencesManager.getUserId(context)
        addDisposable(getUserUseCase.buildUseCaseObservable(GetUserUseCase.Params(userId!!)),
            object : DisposableSingleObserver<User>() {
                override fun onSuccess(user: User) {
                    SharedPreferencesManager.setUserSolution(context, user.solution)
                    onBackEvent.call()

                    isLoading.value = false
                }
                override fun onError(e: Throwable) {
                    isLoading.value = false
                    onErrorEvent.value = e
                }
            })
    }

    fun videoPlayEvent() {
        onVideoPlayEvent.call()
    }
    fun backEvent() {
        onBackEvent.call()
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
}