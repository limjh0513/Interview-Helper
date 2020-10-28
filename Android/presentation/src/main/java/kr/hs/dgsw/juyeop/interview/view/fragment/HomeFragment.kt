package kr.hs.dgsw.juyeop.interview.view.fragment

import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.view.BaseFragment
import kr.hs.dgsw.juyeop.interview.databinding.FragmentHomeBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.fragment.HomeViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.fragment.HomeViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    val rankFragment = RankFragment()
    val adviceFragment = AdviceFragment()
    val recruitFragment = RecruitFragment()
    val recommandFragment = RecommandFragment()

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    override val viewModel: HomeViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {}

    override fun onResume() {
        super.onResume()

        val fragmentTranscation = activity?.supportFragmentManager?.beginTransaction()
        fragmentTranscation?.replace(R.id.frameLayout, rankFragment)?.commitAllowingStateLoss()
        tabSelectedEvent()
    }

    fun tabSelectedEvent() {
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val fragmentTranscation = activity?.supportFragmentManager?.beginTransaction()
                when (tab?.position) {
                    0 -> fragmentTranscation?.replace(R.id.frameLayout, rankFragment)?.commitAllowingStateLoss()
                    1 -> fragmentTranscation?.replace(R.id.frameLayout, adviceFragment)?.commitAllowingStateLoss()
                    2 -> fragmentTranscation?.replace(R.id.frameLayout, recruitFragment)?.commitAllowingStateLoss()
                    3 -> fragmentTranscation?.replace(R.id.frameLayout, recommandFragment)?.commitAllowingStateLoss()
                }
            }
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabReselected(p0: TabLayout.Tab?) {}
        })
    }
}