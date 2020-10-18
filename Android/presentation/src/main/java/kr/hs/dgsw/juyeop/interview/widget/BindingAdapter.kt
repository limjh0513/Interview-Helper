package kr.hs.dgsw.juyeop.interview.widget

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("booleanToVisibility")
fun booleanToVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}