package kr.hs.dgsw.juyeop.interview.model.db

import javax.persistence.*

@Entity
@Table(name = "solution")
class SolutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Int? = null

    var questionIdx: Int? = null
    var userId: String? = null
    var solutionText: String? = null
    var solutionAudio: String? = null
    var solutionVideo: String? = null
    var solutionAt: String? = null
}