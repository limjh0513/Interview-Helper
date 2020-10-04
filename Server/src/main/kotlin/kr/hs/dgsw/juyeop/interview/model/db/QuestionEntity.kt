package kr.hs.dgsw.juyeop.interview.model.db

import javax.persistence.*

@Entity
@Table(name = "question")
class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Int? = null

    var category: Int? = null
    var question: String? = null
}