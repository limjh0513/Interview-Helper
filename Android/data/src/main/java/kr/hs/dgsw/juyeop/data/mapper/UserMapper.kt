package kr.hs.dgsw.juyeop.data.mapper

import kr.hs.dgsw.juyeop.data.entity.UserData
import kr.hs.dgsw.juyeop.domain.entity.User

fun UserData.toEntity(): User {
    return User(
        id = id,
        pw = pw,
        name = name,
        solution = solution
    )
}