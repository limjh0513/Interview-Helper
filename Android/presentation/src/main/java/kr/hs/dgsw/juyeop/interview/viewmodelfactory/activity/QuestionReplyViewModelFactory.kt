package kr.hs.dgsw.juyeop.interview.viewmodelfactory.activity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.hs.dgsw.juyeop.domain.usecase.solution.PostSolutionUseCase
import kr.hs.dgsw.juyeop.domain.usecase.upload.UploadAudioUseCase
import kr.hs.dgsw.juyeop.domain.usecase.upload.UploadVideoUseCase
import kr.hs.dgsw.juyeop.domain.usecase.user.GetUserUseCase
import javax.inject.Inject

class QuestionReplyViewModelFactory @Inject constructor(
    private val context: Context,
    private val uploadAudioUseCase: UploadAudioUseCase,
    private val uploadVideoUseCase: UploadVideoUseCase,
    private val postSolutionUseCase: PostSolutionUseCase,
    private val getUserUseCase: GetUserUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            Context::class.java,
            UploadAudioUseCase::class.java,
            UploadVideoUseCase::class.java,
            PostSolutionUseCase::class.java,
            GetUserUseCase::class.java
        ).newInstance(context, uploadAudioUseCase, uploadVideoUseCase, postSolutionUseCase, getUserUseCase)
    }
}