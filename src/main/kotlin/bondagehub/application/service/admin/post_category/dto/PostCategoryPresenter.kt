package bondagehub.application.service.admin.post_category.dto

import bondagehub.domain.model.post_category.*
import org.springframework.stereotype.Component

@Component
class PostCategoryPresenter {

     fun toDTO(postCategory: PostCategory): PostCategoryDTO {
        return PostCategoryDTO(
            postCategory.id,
            postCategory.name,
            postCategory.description,
            postCategory.slug,
            postCategory.createdAt.toEpochMilli(),
            postCategory.updatedAt.toEpochMilli()
        )
    }

    fun toDTO(postCategories: List<PostCategory>, count: Int): Pair<List<PostCategoryDTO>, Int> {
        return Pair(
                postCategories.map { toDTO(it) },
                count
        )
    }
}
