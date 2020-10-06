package kr.hs.dgsw.juyeop.interview.model.db

import javax.persistence.*

@Entity
@Table(name = "advice")
class AdviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Int? = null

    var user_id: String? = null
    var title: String? = null
    var content: String? = null
    var advice_at: String? = null
}