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
        tabSelectedEvent()
    }

    fun tabSelectedEvent() {
        // fragment 이동하려고 fragment 만들고 transcation 하고 있는데, navigation component 사용해서 더 효율적으로 할 수 있을 것 같아요

        var fragmentTranscation = activity?.supportFragmentManager?.beginTransaction()
        fragmentTranscation?.replace(R.id.frameLayout, rankFragment)?.commitAllowingStateLoss()
        tabsTextView.text = resources.getString(viewModel.getTitleResource(tabs.selectedTabPosition))

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                fragmentTranscation = activity?.supportFragmentManager?.beginTransaction()
                tabsTextView.text = resources.getString(viewModel.getTitleResource(tab!!.position))

                when (tab.position) {
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