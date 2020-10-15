package kr.hs.dgsw.juyeop.data.mapper

import kr.hs.dgsw.juyeop.data.entity.SolutionData
import kr.hs.dgsw.juyeop.domain.entity.Solution

fun SolutionData.toEntity(): Solution {
    return Solution(
        idx = idx,
        user_id = user_id,
        question_idx = question_idx,
        solution_text = solution_text,
        solution_audio = solution_audio,
        solution_video = solution_video,
        solution_at = solution_at
    )
}