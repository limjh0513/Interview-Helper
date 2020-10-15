package kr.hs.dgsw.juyeop.domain.request.solution

class PostSolutionReqeust (
    val idx: Int?,
    val user_id: String,
    val question_idx: Int,
    val solution_text: String?,
    val solution_audio: String?,
    val solution_video: String?,
    val solution_at: String
)