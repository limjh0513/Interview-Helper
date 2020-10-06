package kr.hs.dgsw.juyeop.interview.repository

import kr.hs.dgsw.juyeop.interview.model.db.AdviceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AdviceRepository : JpaRepository<AdviceEntity, Any>