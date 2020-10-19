package kr.hs.dgsw.juyeop.interview.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_question_reply.*
import kr.hs.dgsw.juyeop.domain.entity.Question
import kr.hs.dgsw.juyeop.interview.base.view.BaseActivity
import kr.hs.dgsw.juyeop.interview.databinding.ActivityQuestionReplyBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.fragment.QuestionReplyViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.activity.QuestionReplyViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import javax.inject.Inject

class QuestionReplyActivity : BaseActivity<ActivityQuestionReplyBinding, QuestionReplyViewModel>() {

    @Inject
    lateinit var viewModelFactory: QuestionReplyViewModelFactory

    override val viewModel: QuestionReplyViewModel
        get() = getViewModel(viewModelFactory)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setQuestionData(intent.getSerializableExtra("question") as Question)
        backgroundLayout.setBackgroundResource(viewModel.getColorResource())
    }

    override fun observerViewModel() {
        with(viewModel) {
            onBackEvent.observe(this@QuestionReplyActivity, Observer {
                onBackPressed()
            })
            onCompleteEvent.observe(this@QuestionReplyActivity, Observer {
                onBackPressed()
            })
        }
    }
}