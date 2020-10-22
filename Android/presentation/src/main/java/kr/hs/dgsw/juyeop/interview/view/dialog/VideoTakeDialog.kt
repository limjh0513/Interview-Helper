package kr.hs.dgsw.juyeop.interview.view.dialog

import android.hardware.Camera
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.dialog_audio_record.*
import kotlinx.android.synthetic.main.dialog_video_take.*
import kotlinx.android.synthetic.main.dialog_video_take.cancelImageView
import kotlinx.android.synthetic.main.dialog_video_take.recordImageView
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.view.BaseDialog
import kr.hs.dgsw.juyeop.interview.databinding.DialogVideoTakeBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.dialog.VideoTakeViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.dialog.VideoTakeViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import javax.inject.Inject

/**
 * 1. 카메라 회전 문제
 * 2. 동영상 화질 문제
 */

class VideoTakeDialog : BaseDialog<DialogVideoTakeBinding, VideoTakeViewModel>() {

    val onDismissEvent = MutableLiveData<String>()

    @Inject
    lateinit var viewModelFactory: VideoTakeViewModelFactory

    override val viewModel: VideoTakeViewModel
        get() = getViewModel(viewModelFactory)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.camera = Camera.open(1)
        viewModel.camera.setDisplayOrientation(90)

        viewModel.surfaceHolder = container.holder
        viewModel.surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
    }

    override fun observerViewModel() {
        with(viewModel) {
            onRecordEvent.observe(this@VideoTakeDialog, Observer {
                recordImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_stop))
                cancelImageView.visibility = View.GONE
            })
            onCancelEvent.observe(this@VideoTakeDialog, Observer {
                dismiss()
            })
        }
    }

    override fun dismiss() {
        super.dismiss()
        onDismissEvent.value = viewModel.mediaFileName
    }
}