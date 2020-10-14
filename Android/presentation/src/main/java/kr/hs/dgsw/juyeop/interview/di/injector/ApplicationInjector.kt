package kr.hs.dgsw.juyeop.interview.di.injector

import kr.hs.dgsw.juyeop.interview.InterviewApplication
import kr.hs.dgsw.juyeop.interview.di.component.DaggerAppComponent

class ApplicationInjector {

    fun init(interviewApplication: InterviewApplication) {
        DaggerAppComponent
            .builder()
            .application(interviewApplication)
            .build()
            .inject(interviewApplication)
    }
}