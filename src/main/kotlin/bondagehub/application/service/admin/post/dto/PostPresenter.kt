package bondagehub.application.service.admin.post.dto

import bondagehub.domain.model.post.*
import org.springframework.stereotype.Component

@Component
class PostPresenter {

     fun toDTO(post: Post): PostDTO {
        return PostDTO(
            post.id.id(),
            post.memberId.id(),
            post.type.label,
            post.type.name,
            post.status.label,
            post.status.name,
            post.createdAt.toEpochMilli(),
            post.updatedAt.toEpochMilli()
        )
    }

    fun toDTO(posts: List<Post>, count: Int): Pair<List<PostDTO>, Int> {
        return Pair(
            posts.map { toDTO(it) },
            count
        )
    }
}
