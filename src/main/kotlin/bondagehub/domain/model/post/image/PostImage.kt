package bondagehub.domain.model.post.image

import bondagehub.domain.model.post.*
import java.time.Instant
import bondagehub.domain.model.attachment.*

/**
 * 投稿単体画像を表現する。
 */
class PostImage(
    val id: Long, // 主キー
    val postId: PostId, // 投稿ID
    val title: String, // 投稿のタイトル
    val imageId: Long, // 画像ID
    val createdAt: Instant, // 投稿日時
    val updatedAt: Instant, // 更新日時

    val image: Attachment? = null,
    val post: Post? = null,
) {

    companion object {

        fun create(
            postId: PostId,
            title: String,
            imageId: Long,
        ): PostImage = with(Instant.now()) {
            PostImage(
                id = 0,
                postId = postId,
                title = title,
                imageId = imageId,
                createdAt = this,
                updatedAt = this,
            )
        }
    }
}