package kr.hs.dgsw.juyeop.interview.repository

import kr.hs.dgsw.juyeop.interview.model.db.AuthEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AuthRepository : JpaRepository<AuthEntity, Any>