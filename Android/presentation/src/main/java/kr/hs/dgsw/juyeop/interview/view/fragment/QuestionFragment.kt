package kr.hs.dgsw.juyeop.interview.view.fragment

import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.robinhood.ticker.TickerUtils
import kotlinx.android.synthetic.main.fragment_question.*
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.view.BaseFragment
import kr.hs.dgsw.juyeop.interview.databinding.FragmentQuestionBinding
import kr.hs.dgsw.juyeop.interview.view.activity.QuestionReplyActivity
import kr.hs.dgsw.juyeop.interview.viewmodel.fragment.QuestionViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.fragment.QuestionViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import kr.hs.dgsw.juyeop.interview.widget.extension.startActivityWithValue
import javax.inject.Inject

class QuestionFragment : BaseFragment<FragmentQuestionBinding, QuestionViewModel>() {

    private var colorResource = R.color.colorCategory1
    private val NUMBER_LIST = TickerUtils.provideNumberList()

    @Inject
    lateinit var viewModelFactory: QuestionViewModelFactory

    override val viewModel: QuestionViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(viewModel) {
            category.observe(this@QuestionFragment, Observer {
                getAllSolution()
                backgroundCardView.setCardBackgroundColor(resources.getColor(colorResource))
            })
            onCombinEvent.observe(this@QuestionFragment, Observer {
                questionCountTextView.setCharacterLists(NUMBER_LIST)

                val questionCount = "${questionItemList.size}개"
                questionCountTextView.text = questionCount
            })
            questionItemAdapter.onReplyEvent.observe(this@QuestionFragment, Observer {
                startActivityWithValue(requireContext(), "question", QuestionReplyActivity::class.java, it)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        tabSelectedEvent()
        viewModel.getAllSolution()
    }

    fun tabSelectedEvent() {
        var questionInfo = "아직 답변하지 않은\n${tabs.getTabAt(0)!!.text} 관련 면접 질문 수"
        questionInfoTextView.text = questionInfo

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                questionInfo = "아직 답변하지 않은\n${tab!!.text} 관련 면접 질문 수"
                questionInfoTextView.text = questionInfo

                with(viewModel) {
                    colorResource = getColorResource(tab.position+1)
                    when (tab.position) {
                        0 -> category.value = 1
                        1 -> category.value = 2
                        2 -> category.value = 3
                        3 -> category.value = 4
                        4 -> category.value = 5
                        5 -> category.value = 6
                        6 -> category.value = 7
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}