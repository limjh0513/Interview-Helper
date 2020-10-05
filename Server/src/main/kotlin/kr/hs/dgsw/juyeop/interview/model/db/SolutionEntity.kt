package kr.hs.dgsw.juyeop.interview.model.db

import javax.persistence.*

@Entity
@Table(name = "solution")
class SolutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Int? = null

    var user_id: String? = null
    var question_idx: Int? = null
    var solution_text: String? = null
    var solution_audio: String? = null
    var solution_video: String? = null
    var solution_at: String? = null
}