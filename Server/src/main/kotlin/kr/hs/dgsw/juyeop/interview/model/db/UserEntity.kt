package kr.hs.dgsw.juyeop.interview.model.db

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
class UserEntity {

    @Id
    var id: String? = null

    var pw: String? = null
    var name: String? = null
    var solution: Int? = null
}