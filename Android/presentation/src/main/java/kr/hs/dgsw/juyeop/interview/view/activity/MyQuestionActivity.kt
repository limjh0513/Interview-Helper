package kr.hs.dgsw.juyeop.interview.view.activity

import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.robinhood.ticker.TickerUtils
import kotlinx.android.synthetic.main.fragment_question.*
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.view.BaseActivity
import kr.hs.dgsw.juyeop.interview.databinding.ActivityMyQuestionBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.activity.MyQuestionViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.activity.MyQuestionViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import kr.hs.dgsw.juyeop.interview.widget.extension.startActivity
import kr.hs.dgsw.juyeop.interview.widget.extension.startActivityWithTwoValue
import javax.inject.Inject

class MyQuestionActivity : BaseActivity<ActivityMyQuestionBinding, MyQuestionViewModel>() {

    private var colorResource = R.color.colorCategory1
    private val NUMBER_LIST = TickerUtils.provideNumberList()

    @Inject
    lateinit var viewModelFactory: MyQuestionViewModelFactory

    override val viewModel: MyQuestionViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(viewModel) {
            category.observe(this@MyQuestionActivity, Observer {
                getAllSolution()
                backgroundCardView.setCardBackgroundColor(resources.getColor(colorResource))
            })
            onCombinEvent.observe(this@MyQuestionActivity, Observer {
                questionCountTextView.setCharacterLists(NUMBER_LIST)

                val questionCount = "${questionItemList.size}개"
                questionCountTextView.text = questionCount
            })
            questionItemAdapter.onReplyEvent.observe(this@MyQuestionActivity, Observer {
                val questionData = it
                val solutionData = allSolutionList.filter { solution -> solution.question_idx == it.idx }[0]
                startActivityWithTwoValue(applicationContext, "question", "solution", MyQuestionReplyActivity::class.java, questionData, solutionData)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        tabSelectedEvent()
        viewModel.getAllSolution()
    }

    fun tabSelectedEvent() {
        var questionInfo = "이전에 이미 답변한\n${tabs.getTabAt(0)!!.text} 관련 면접 질문 수"
        questionInfoTextView.text = questionInfo

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                questionInfo = "이전에 이미 답변한\n${tab!!.text} 관련 면접 질문 수"
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