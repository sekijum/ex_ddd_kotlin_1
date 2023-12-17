package bondagehub.application.repository.admin

import bondagehub.domain.model.post.*
import org.springframework.data.domain.Pageable

interface PostRepository {

    fun findPageByQuery(pageable: Pageable): List<Post>
    fun count(): Int
}
