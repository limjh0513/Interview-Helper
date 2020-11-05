package kr.hs.dgsw.juyeop.interview.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.juyeop.domain.entity.Question
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.databinding.ItemQuestionBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.adapter.QuestionItemViewModel
import kr.hs.dgsw.juyeop.interview.widget.SingleLiveEvent
import kr.hs.dgsw.juyeop.interview.widget.navigator.QuestionItemNavigator

class QuestionItemAdapter : RecyclerView.Adapter<QuestionItemAdapter.ViewHolder>(), QuestionItemNavigator {

    lateinit var questionItemList: List<Question>

    var questionState = false
    val onReplyEvent = SingleLiveEvent<Question>()

    fun setList(questionItemList: List<Question>, questionState: Boolean) {
        this.questionItemList = questionItemList
        this.questionState = questionState
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_question, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            bind(questionItemList[position])
        }
    }

    override fun getItemCount(): Int {
        return questionItemList.size
    }

    override fun replyEvent(question: Question) {
        onReplyEvent.value = question
    }

    inner class ViewHolder(val binding: ItemQuestionBinding): RecyclerView.ViewHolder(binding.root) {
        val viewModel = QuestionItemViewModel()

        fun bind(question: Question) {
            viewModel.bind(question)
            viewModel.setNavigator(this@QuestionItemAdapter)

            binding.viewModel = viewModel
            binding.lockImageView.setColorFilter(binding.root.resources.getColor(viewModel.getColorResource(question.category)), android.graphics.PorterDuff.Mode.MULTIPLY)
            binding.answerTextView.setTextColor(binding.root.resources.getColor(viewModel.getColorResource(question.category)))

            if (questionState) {
                binding.lockImageView.setImageResource(R.drawable.ic_lock_open)
                binding.answerTextView.text = binding.root.resources.getString(R.string.text_answer_check)
            }
        }
    }
}