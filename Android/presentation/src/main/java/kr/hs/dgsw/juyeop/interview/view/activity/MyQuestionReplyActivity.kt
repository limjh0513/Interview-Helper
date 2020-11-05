package kr.hs.dgsw.juyeop.interview.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_my_question_reply.*
import kotlinx.android.synthetic.main.activity_question_reply.backgroundLayout
import kr.hs.dgsw.juyeop.data.util.Constants
import kr.hs.dgsw.juyeop.domain.entity.Question
import kr.hs.dgsw.juyeop.domain.entity.Solution
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.view.BaseActivity
import kr.hs.dgsw.juyeop.interview.databinding.ActivityMyQuestionReplyBinding
import kr.hs.dgsw.juyeop.interview.view.dialog.VideoCheckDialog
import kr.hs.dgsw.juyeop.interview.viewmodel.activity.MyQuestionReplyViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.activity.MyQuestionReplyViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import javax.inject.Inject

class MyQuestionReplyActivity : BaseActivity<ActivityMyQuestionReplyBinding, MyQuestionReplyViewModel>() {

    @Inject
    lateinit var viewModelFactory: MyQuestionReplyViewModelFactory

    override val viewModel: MyQuestionReplyViewModel
        get() = getViewModel(viewModelFactory)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val questionData = intent.getSerializableExtra("question") as Question
        val solutionData = intent.getSerializableExtra("solution") as Solution

        viewModel.setData(questionData, solutionData)
        backgroundLayout.setBackgroundResource(viewModel.getColorResource())
    }

    override fun observerViewModel() {
        with(viewModel) {
            onBackEvent.observe(this@MyQuestionReplyActivity, Observer {
                onBackPressed()
            })
            onAudioStartEvent.observe(this@MyQuestionReplyActivity, Observer {
                audioImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_stop))
            })
            onAudioCompleteEvent.observe(this@MyQuestionReplyActivity, Observer {
                audioImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_play))
            })
            onVideoPlayEvent.observe(this@MyQuestionReplyActivity, Observer {
                val bundle = Bundle()
                bundle.putString("videoPath", "${Constants.DEFAULT_HOST}video/${solution.solution_video}")

                val videoCheckDialog = VideoCheckDialog()
                videoCheckDialog.arguments = bundle
                videoCheckDialog.show(supportFragmentManager)
            })
        }
    }

    override fun onBackPressed() {
        if (viewModel.audioMediaPlayer != null) viewModel.audioMediaPlayer!!.stop()
        if (viewModel.videoMediaPlayer != null) viewModel.videoMediaPlayer!!.stop()

        super.onBackPressed()
    }
}