package kr.hs.dgsw.juyeop.data.mapper

import kr.hs.dgsw.juyeop.data.entity.AdviceData
import kr.hs.dgsw.juyeop.domain.entity.Advice

fun AdviceData.toEntity(): Advice {
    return Advice(
        idx = idx,
        user_id = user_id,
        title = title,
        content = content,
        advice_at = advice_at
    )
}