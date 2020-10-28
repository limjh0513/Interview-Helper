package kr.hs.dgsw.juyeop.interview.view.fragment

import kr.hs.dgsw.juyeop.interview.base.view.BaseFragment
import kr.hs.dgsw.juyeop.interview.databinding.FragmentRecruitBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.fragment.RecruitViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.fragment.RecruitViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import javax.inject.Inject

class RecruitFragment : BaseFragment<FragmentRecruitBinding, RecruitViewModel>() {

    @Inject
    lateinit var viewModelFactory: RecruitViewModelFactory

    override val viewModel: RecruitViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {}
}