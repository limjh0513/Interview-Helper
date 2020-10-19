package kr.hs.dgsw.juyeop.interview.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.juyeop.domain.entity.Question
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.databinding.ItemQuestionBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.adapter.QuestionItemViewModel

class QuestionItemAdapter : RecyclerView.Adapter<QuestionItemAdapter.ViewHolder>() {

    lateinit var questionItemList: List<Question>

    fun setList(questionItemList: List<Question>) {
        this.questionItemList = questionItemList
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

    inner class ViewHolder(val binding: ItemQuestionBinding): RecyclerView.ViewHolder(binding.root) {
        val viewModel = QuestionItemViewModel()

        fun bind(question: Question) {
            viewModel.bind(question)
            binding.viewModel = viewModel
            binding.lockImageView.setColorFilter(binding.root.resources.getColor(viewModel.getColorResource(question.category)), android.graphics.PorterDuff.Mode.MULTIPLY)
            binding.answerTextView.setTextColor(binding.root.resources.getColor(viewModel.getColorResource(question.category)))
        }
    }
}