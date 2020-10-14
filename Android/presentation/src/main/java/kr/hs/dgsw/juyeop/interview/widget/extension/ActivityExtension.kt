package kr.hs.dgsw.juyeop.interview.widget.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T: ViewModel> AppCompatActivity.getViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory)[T::class.java]
}

inline fun <reified T: ViewModel> AppCompatActivity.getViewModel(): T {
    return ViewModelProvider(this)[T::class.java]
}