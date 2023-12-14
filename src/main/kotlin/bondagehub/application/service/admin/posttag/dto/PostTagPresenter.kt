package bondagehub.application.service.admin.posttag.dto

import bondagehub.domain.model.posttag.*
import bondagehub.application.service.*
import org.springframework.stereotype.Component

@Component
class PostTagPresenter {

     fun toDTO(postTag: PostTag): PostTagDTO {
        return PostTagDTO(
            postTag.id,
            postTag.name,
            postTag.description,
            postTag.slug,
            postTag.createdAt.toEpochMilli(),
            postTag.updatedAt.toEpochMilli()
        )
    }

    fun toDTO(postTags: List<PostTag>, count: Int, limit: Int, offset: Int): PaginationDTO<PostTagDTO> {
        return PaginationDTO(
            count,
            limit,
            offset,
            postTags.map { toDTO(it) }
        )
    }
}
