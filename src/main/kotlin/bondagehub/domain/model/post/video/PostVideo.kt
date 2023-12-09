package bondagehub.domain.model.post.video

import bondagehub.domain.model.post.*
import bondagehub.domain.model.member.*
import java.time.Instant
import bondagehub.domain.model.attachment.*

/**
 * 投稿動画を表現する。
 */
class PostVideo(
    val id: Long, // 主キー
    val postId: PostId, // 投稿ID
    val title: String, // 投稿のタイトル
    val description: String? = null, // 投稿の説明
    val thumbnailId: Long? = null, // サムネイルID
    val videoId: Long, // 動画ID
    val createdAt: Instant, // 投稿日時
    val updatedAt: Instant, // 更新日時

    val thumbnail: Attachment? = null,
    val video: Attachment? = null,
    val sampleImageIds: List<Long>? = ArrayList<Long>(),
    val sampleImages: List<Attachment>? = ArrayList<Attachment>(),
    val post: Post? = null,
) {

    companion object {

        fun create(
            postId: PostId,
            title: String,
            description: String? = null,
            thumbnailId: Long? = null,
            videoId: Long,
            sampleImageIds: List<Long>? = null
        ): PostVideo = with(Instant.now()) {
            PostVideo(
                id = 0,
                postId,
                title,
                description,
                thumbnailId,
                videoId,
                createdAt = this,
                updatedAt = this,
                sampleImageIds = sampleImageIds
            )
        }
    }
}