package kr.hs.dgsw.juyeop.interview.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.juyeop.domain.entity.Advice
import kr.hs.dgsw.juyeop.domain.entity.User
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.databinding.ItemAdviceBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.adapter.AdviceItemViewModel

class AdviceItemAdapter : RecyclerView.Adapter<AdviceItemAdapter.ViewHolder>() {

    lateinit var adviceItemList: List<Advice>
    lateinit var userItemList: List<User>

    fun setData(adviceItemList: List<Advice>, userItemList: List<User>) {
        this.adviceItemList = adviceItemList
        this.userItemList = userItemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdviceItemAdapter.ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_advice, parent, false))
    }

    override fun onBindViewHolder(holder: AdviceItemAdapter.ViewHolder, position: Int) {
        with(holder) {
            bind(adviceItemList[position], userItemList)
        }
    }

    override fun getItemCount(): Int {
        return adviceItemList.size
    }

    class ViewHolder(val binding: ItemAdviceBinding): RecyclerView.ViewHolder(binding.root) {
        val viewModel = AdviceItemViewModel()

        fun bind(advice: Advice, userItemList: List<User>) {
            //viewModel에서 MutableLiveData를 만들어서 viewModel에 advice 전달 후 xml에 적용하는거 보다
            //이 함수에서 처리하고 variable에 advice 하나 만들어서 @{advice.name} 이런식으로 하는게 더 나아 보이네요
            viewModel.bind(advice, userItemList)
            binding.viewModel = viewModel
        }
    }
}