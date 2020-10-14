package kr.hs.dgsw.juyeop.interview.view

import kr.hs.dgsw.juyeop.interview.base.BaseActivity
import kr.hs.dgsw.juyeop.interview.databinding.ActivityMainBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.MainViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.MainViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override val viewModel: MainViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {}
}