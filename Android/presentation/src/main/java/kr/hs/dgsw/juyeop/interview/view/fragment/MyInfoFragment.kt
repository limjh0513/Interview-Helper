package kr.hs.dgsw.juyeop.interview.view.fragment

import androidx.lifecycle.Observer
import kr.hs.dgsw.juyeop.interview.base.view.BaseFragment
import kr.hs.dgsw.juyeop.interview.databinding.FragmentMyInfoBinding
import kr.hs.dgsw.juyeop.interview.view.activity.MyQuestionActivity
import kr.hs.dgsw.juyeop.interview.view.activity.SignInActivity
import kr.hs.dgsw.juyeop.interview.viewmodel.fragment.MyInfoViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.fragment.MyInfoViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import kr.hs.dgsw.juyeop.interview.widget.extension.startActivity
import kr.hs.dgsw.juyeop.interview.widget.extension.startActivityWithFinish
import kr.hs.dgsw.juyeop.interview.widget.manager.SharedPreferencesManager
import javax.inject.Inject

class MyInfoFragment : BaseFragment<FragmentMyInfoBinding, MyInfoViewModel>() {

    @Inject
    lateinit var viewModelFactory: MyInfoViewModelFactory

    override val viewModel: MyInfoViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(viewModel) {
            onLogoutEvent.observe(this@MyInfoFragment, Observer {
                SharedPreferencesManager.setUserId(requireContext(), null)
                SharedPreferencesManager.setUserSolution(requireContext(), 0)
                startActivityWithFinish(requireContext(), SignInActivity::class.java)
            })
            onMySolutionEvent.observe(this@MyInfoFragment, Observer {
                startActivity(requireContext(), MyQuestionActivity::class.java)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllUser()
    }
}