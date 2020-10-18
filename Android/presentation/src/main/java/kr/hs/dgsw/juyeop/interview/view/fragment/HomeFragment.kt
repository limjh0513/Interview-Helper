package kr.hs.dgsw.juyeop.interview.view.fragment

import kr.hs.dgsw.juyeop.interview.base.view.BaseFragment
import kr.hs.dgsw.juyeop.interview.databinding.FragmentHomeBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.fragment.HomeViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.fragment.HomeViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    override val viewModel: HomeViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {}
}