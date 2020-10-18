package kr.hs.dgsw.juyeop.interview.view.fragment

import kr.hs.dgsw.juyeop.interview.base.view.BaseFragment
import kr.hs.dgsw.juyeop.interview.databinding.FragmentQuestionBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.fragment.QuestionViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.fragment.QuestionViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import javax.inject.Inject

class QuestionFragment : BaseFragment<FragmentQuestionBinding, QuestionViewModel>() {

    @Inject
    lateinit var viewModelFactory: QuestionViewModelFactory

    override val viewModel: QuestionViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {}
}