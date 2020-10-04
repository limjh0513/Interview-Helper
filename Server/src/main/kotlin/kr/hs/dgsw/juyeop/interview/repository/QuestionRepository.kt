package kr.hs.dgsw.juyeop.interview.repository

import kr.hs.dgsw.juyeop.interview.model.db.QuestionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository : JpaRepository<QuestionEntity, Any>