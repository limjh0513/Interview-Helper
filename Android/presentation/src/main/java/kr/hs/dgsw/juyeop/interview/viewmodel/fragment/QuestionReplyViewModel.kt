package kr.hs.dgsw.juyeop.interview.viewmodel.fragment

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.lifecycle.MutableLiveData
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import io.reactivex.observers.DisposableCompletableObserver
import kr.hs.dgsw.juyeop.domain.entity.Question
import kr.hs.dgsw.juyeop.domain.request.solution.PostSolutionReqeust
import kr.hs.dgsw.juyeop.domain.usecase.solution.PostSolutionUseCase
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.viewmodel.BaseViewModel
import kr.hs.dgsw.juyeop.interview.widget.SingleLiveEvent
import kr.hs.dgsw.juyeop.interview.widget.extension.atFormat
import kr.hs.dgsw.juyeop.interview.widget.manager.SharedPreferencesManager
import java.util.*

class QuestionReplyViewModel(
    private val context: Context,
    private val postSolutionUseCase: PostSolutionUseCase
) : BaseViewModel() {

    lateinit var question: Question

    val categoryName = MutableLiveData<String>()
    val questionName = MutableLiveData<String>()

    val solutionText = MutableLiveData<String>()
    val solutionAduio = MutableLiveData<String>()
    val solutionVideo = MutableLiveData<String>()

    val onBackEvent = SingleLiveEvent<Unit>()
    val onCompleteEvent = SingleLiveEvent<Unit>()
    val onAudioEvent = SingleLiveEvent<Unit>()
    val onVideoEvent = SingleLiveEvent<Unit>()

    fun setQuestionData(question: Question) {
        this.question = question

        categoryName.value = getCategoryName()
        questionName.value = question.question
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

    fun saveEvent() {
        val userId = SharedPreferencesManager.getUserId(context).toString()
        val postSolutionReqeust = PostSolutionReqeust(null, userId, question.idx, solutionText.value, solutionAduio.value, solutionVideo.value, Date().atFormat())

        addDisposable(postSolutionUseCase.buildUseCaseObservable(PostSolutionUseCase.Params(postSolutionReqeust)),
            object : DisposableCompletableObserver() {
                override fun onComplete() {
                    onCompleteEvent.call()
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })
    }

    fun getCategoryName(): String {
        var categoryResource = R.string.tab_first
        when(question.category) {
            1 -> categoryResource = R.string.tab_first
            2 -> categoryResource = R.string.tab_second
            3 -> categoryResource = R.string.tab_third
            4 -> categoryResource = R.string.tab_fourth
            5 -> categoryResource = R.string.tab_fifth
            6 -> categoryResource = R.string.tab_sixth
            7 -> categoryResource = R.string.tab_seventh
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
}