package kr.hs.dgsw.juyeop.interview.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.juyeop.domain.entity.User
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.databinding.ItemRankBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.adapter.RankItemViewModel

class RankItemAdapter : RecyclerView.Adapter<RankItemAdapter.ViewHolder>() {

    lateinit var userItemList: List<User>

    fun setList(userItemList: List<User>) {
        this.userItemList = userItemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_rank, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            bind(userItemList[position], position+1)
        }
    }

    override fun getItemCount(): Int {
        return userItemList.size
    }

    class ViewHolder(val binding: ItemRankBinding): RecyclerView.ViewHolder(binding.root) {
        val viewModel = RankItemViewModel()

        fun bind(user: User, position: Int) {
            viewModel.bind(user, position)
            binding.viewModel = viewModel
        }
    }
}