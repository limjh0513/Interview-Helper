package kr.hs.dgsw.juyeop.interview.repository

import kr.hs.dgsw.juyeop.interview.model.db.SolutionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SolutionRepository : JpaRepository<SolutionEntity, Any>