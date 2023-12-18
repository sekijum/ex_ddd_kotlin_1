package bondagehub.application.service.admin.post_tag.dto

import bondagehub.domain.model.post_tag.*
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

    fun toDTO(postTags: List<PostTag>, count: Int): Pair<List<PostTagDTO>, Int> {
        return Pair(
                postTags.map { toDTO(it) },
                count
        )
    }
}
