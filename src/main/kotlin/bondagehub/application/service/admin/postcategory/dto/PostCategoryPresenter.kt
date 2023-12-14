package bondagehub.application.service.admin.postcategory.dto

import bondagehub.domain.model.postcategory.*
import bondagehub.application.service.*
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

    fun toDTO(postCategories: List<PostCategory>, count: Int, limit: Int, offset: Int): PaginationDTO<PostCategoryDTO> {
        return PaginationDTO(
            count,
            limit,
            offset,
            postCategories.map { toDTO(it) }
        )
    }
}
