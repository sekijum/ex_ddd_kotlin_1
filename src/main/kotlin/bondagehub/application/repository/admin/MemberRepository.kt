package bondagehub.application.repository.admin

import bondagehub.domain.model.member.*
import org.springframework.data.domain.Pageable

interface MemberRepository {

    fun findPageByQuery(pageable: Pageable): List<Member>
    fun count(): Int
}
