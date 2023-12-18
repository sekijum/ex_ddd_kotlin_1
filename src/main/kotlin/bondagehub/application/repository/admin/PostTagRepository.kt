package bondagehub.application.repository.admin

import bondagehub.domain.model.post_tag.*
import org.springframework.data.domain.Pageable

interface PostTagRepository {

    fun findPageByQuery(pageable: Pageable): List<PostTag>
    fun count(): Int
}
