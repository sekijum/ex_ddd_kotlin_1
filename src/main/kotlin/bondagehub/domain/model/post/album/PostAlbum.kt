package bondagehub.domain.model.post.album

import bondagehub.domain.model.attachment.*
import bondagehub.domain.model.member.*
import bondagehub.domain.model.post.Post
import bondagehub.domain.model.post.PostId
import java.time.Instant

/**
 * 投稿アルバムを表現する。
 */
class PostAlbum(
    val id: Long, // 主キー
    val postId: PostId, // 投稿ID
    val title: String, // 投稿のタイトル
    val description: String? = null, // 投稿の説明
    val thumbnailId: Long,
    val createdAt: Instant, // 投稿日時
    val updatedAt: Instant, // 更新日時

    val post: Post? = null,
    val thumbnail: Attachment? = null,
    val imageIds: List<Long>? = ArrayList<Long>(),
    val images: List<Attachment>? = ArrayList<Attachment>(),
) {

    companion object {

        fun create(
            postId: PostId,
            title: String,
            description: String? = null,
            thumbnailId: Long,
        ): PostAlbum = with(Instant.now()) {
            PostAlbum(
                id = 0,
                postId = postId,
                title = title,
                description = description,
                thumbnailId = thumbnailId,
                createdAt = this,
                updatedAt = this
            )
        }
    }
}