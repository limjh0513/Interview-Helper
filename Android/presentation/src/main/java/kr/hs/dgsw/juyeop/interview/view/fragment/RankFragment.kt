package kr.hs.dgsw.juyeop.interview.view.fragment

import kr.hs.dgsw.juyeop.interview.base.view.BaseFragment
import kr.hs.dgsw.juyeop.interview.databinding.FragmentRankBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.fragment.RankViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.fragment.RankViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import javax.inject.Inject

class RankFragment : BaseFragment<FragmentRankBinding, RankViewModel>() {

    @Inject
    lateinit var viewModelFactory: RankViewModelFactory

    override val viewModel: RankViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {}
}