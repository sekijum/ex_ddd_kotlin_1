package bondagehub.application.repository.admin

import bondagehub.domain.model.postcategory.*
import org.springframework.data.domain.Pageable

interface PostCategoryRepository {

    fun findPageByQuery(pageable: Pageable): List<PostCategory>
    fun count(): Int
}
