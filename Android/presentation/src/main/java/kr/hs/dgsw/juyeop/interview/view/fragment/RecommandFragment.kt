package kr.hs.dgsw.juyeop.interview.view.fragment

import kr.hs.dgsw.juyeop.interview.base.view.BaseFragment
import kr.hs.dgsw.juyeop.interview.databinding.FragmentRecommandBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.fragment.RecommandViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.fragment.RecommandViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import javax.inject.Inject

class RecommandFragment : BaseFragment<FragmentRecommandBinding, RecommandViewModel>() {

    @Inject
    lateinit var viewModelFactory: RecommandViewModelFactory

    override val viewModel: RecommandViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {}
}