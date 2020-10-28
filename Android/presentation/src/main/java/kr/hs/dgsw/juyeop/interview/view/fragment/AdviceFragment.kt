package kr.hs.dgsw.juyeop.interview.view.fragment

import kr.hs.dgsw.juyeop.interview.base.view.BaseFragment
import kr.hs.dgsw.juyeop.interview.databinding.FragmentAdviceBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.fragment.AdviceViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.fragment.AdviceViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import javax.inject.Inject

class AdviceFragment : BaseFragment<FragmentAdviceBinding, AdviceViewModel>() {

    @Inject
    lateinit var viewModelFactory: AdviceViewModelFactory

    override val viewModel: AdviceViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {}
}