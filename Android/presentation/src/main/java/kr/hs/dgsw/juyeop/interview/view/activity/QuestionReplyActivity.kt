package kr.hs.dgsw.juyeop.interview.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_question_reply.*
import kr.hs.dgsw.juyeop.domain.entity.Question
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.view.BaseActivity
import kr.hs.dgsw.juyeop.interview.databinding.ActivityQuestionReplyBinding
import kr.hs.dgsw.juyeop.interview.view.dialog.AudioRecordDialog
import kr.hs.dgsw.juyeop.interview.view.dialog.VideoTakeDialog
import kr.hs.dgsw.juyeop.interview.viewmodel.activity.QuestionReplyViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.activity.QuestionReplyViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import kr.hs.dgsw.juyeop.interview.widget.extension.shortSnackbar
import javax.inject.Inject

class QuestionReplyActivity : BaseActivity<ActivityQuestionReplyBinding, QuestionReplyViewModel>() {

    @Inject
    lateinit var viewModelFactory: QuestionReplyViewModelFactory

    override val viewModel: QuestionReplyViewModel
        get() = getViewModel(viewModelFactory)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setQuestionData(intent.getSerializableExtra("question") as Question)
        viewModel.permissionSetting()

        backgroundLayout.setBackgroundResource(viewModel.getColorResource())
    }

    override fun observerViewModel() {
        with(viewModel) {
            onBackEvent.observe(this@QuestionReplyActivity, Observer {
                onBackPressed()
            })
            onEmptyEvent.observe(this@QuestionReplyActivity, Observer {
                shortSnackbar(layout, resources.getString(R.string.empty_solution))
            })
            onCompleteEvent.observe(this@QuestionReplyActivity, Observer {
                onBackPressed()
            })
            onAudioEvent.observe(this@QuestionReplyActivity, Observer {
                val audioRecordDialog = AudioRecordDialog()
                audioRecordDialog.show(supportFragmentManager)
                audioRecordDialog.onDismissEvent.observe(this@QuestionReplyActivity, Observer {
                    setAudioData(it)
                })
            })
            onVideoEvent.observe(this@QuestionReplyActivity, Observer {
                val videoTakeDialog = VideoTakeDialog()
                videoTakeDialog.show(supportFragmentManager)
                videoTakeDialog.onDismissEvent.observe(this@QuestionReplyActivity, Observer {
                    setVideoData(it)
                })
            })
        }
    }
}