package kr.hs.dgsw.juyeop.interview.repository

import kr.hs.dgsw.juyeop.interview.model.db.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, String>