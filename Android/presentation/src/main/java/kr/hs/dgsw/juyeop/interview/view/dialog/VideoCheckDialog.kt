package kr.hs.dgsw.juyeop.interview.view.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.dialog_video_check.*
import kotlinx.android.synthetic.main.dialog_video_take.*
import kotlinx.android.synthetic.main.dialog_video_take.container
import kr.hs.dgsw.juyeop.interview.base.view.BaseDialog
import kr.hs.dgsw.juyeop.interview.databinding.DialogVideoCheckBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.dialog.VideoCheckViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.dialog.VideoCheckViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import javax.inject.Inject

class VideoCheckDialog : BaseDialog<DialogVideoCheckBinding, VideoCheckViewModel>(), SurfaceHolder.Callback {

    @Inject
    lateinit var viewModelFactory: VideoCheckViewModelFactory

    override val viewModel: VideoCheckViewModel
        get() = getViewModel(viewModelFactory)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.surfaceHolder = container.holder
        viewModel.surfaceHolder.addCallback(this)
        viewModel.videoPath = requireArguments().getString("videoPath")!!
    }

    override fun observerViewModel() {
        with(viewModel) {
            onCancelEvent.observe(this@VideoCheckDialog, Observer {
                dismiss()
            })
        }
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        viewModel.videoMediaPlayerSetting()
    }
    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {}
    override fun surfaceDestroyed(p0: SurfaceHolder) {}
}