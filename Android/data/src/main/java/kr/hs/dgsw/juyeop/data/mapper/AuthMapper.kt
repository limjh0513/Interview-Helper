package kr.hs.dgsw.juyeop.data.mapper

import kr.hs.dgsw.juyeop.data.entity.AuthData
import kr.hs.dgsw.juyeop.domain.entity.Auth

fun AuthData.toEntity(): Auth {
    return Auth(
        id = id,
        pw = pw,
        name = name,
        solution = solution
    )
}