package kr.hs.dgsw.juyeop.interview.model.db

import javax.persistence.*

@Entity
@Table(name = "user")
class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: String? = null

    var pw: String? = null
    var name: String? = null
    var solution: Int? = null
}