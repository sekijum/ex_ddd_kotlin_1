package bondagehub.application.service.admin.post.dto

import bondagehub.domain.model.post.*
import bondagehub.application.service.*
import org.springframework.stereotype.Component

@Component
class PostPresenter {

     fun toDTO(post: Post): PostDTO {
        return PostDTO(
            post.id.id(),
            post.type.label,
            post.type.name,
            post.memberId.id(),
            post.status.label,
            post.status.name,
            post.createdAt.toEpochMilli(),
            post.updatedAt.toEpochMilli()
        )
    }

    fun toDTO(posts: List<Post>, count: Int, limit: Int, offset: Int): PaginationDTO<PostDTO> {
        return PaginationDTO(
            count,
            limit,
            offset,
            posts.map { toDTO(it) }
        )
    }
}
