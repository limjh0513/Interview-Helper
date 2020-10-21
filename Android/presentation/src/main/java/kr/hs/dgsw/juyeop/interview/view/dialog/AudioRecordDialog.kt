package kr.hs.dgsw.juyeop.interview.view.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.dialog_audio_record.*
import kr.hs.dgsw.juyeop.interview.R
import kr.hs.dgsw.juyeop.interview.base.view.BaseDialog
import kr.hs.dgsw.juyeop.interview.databinding.DialogAudioRecordBinding
import kr.hs.dgsw.juyeop.interview.viewmodel.dialog.AudioRecordViewModel
import kr.hs.dgsw.juyeop.interview.viewmodelfactory.dialog.AudioRecordViewModelFactory
import kr.hs.dgsw.juyeop.interview.widget.extension.getViewModel
import javax.inject.Inject

class AudioRecordDialog : BaseDialog<DialogAudioRecordBinding, AudioRecordViewModel>() {

    val onRecordStopEvent = MutableLiveData<String>()

    @Inject
    lateinit var viewModelFactory: AudioRecordViewModelFactory

    override val viewModel: AudioRecordViewModel
        get() = getViewModel(viewModelFactory)

    override fun observerViewModel() {
        with(viewModel) {
            onRecordEvent.observe(this@AudioRecordDialog, Observer {
                recordImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_stop))
            })
            onCancelEvent.observe(this@AudioRecordDialog, Observer {
                dismiss()
            })
        }
    }

    override fun dismiss() {
        super.dismiss()
        onRecordStopEvent.value = viewModel.mediaFileName
    }
}