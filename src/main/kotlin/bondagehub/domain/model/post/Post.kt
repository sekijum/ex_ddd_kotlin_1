package bondagehub.domain.model.post

import bondagehub.domain.model.Entity
import bondagehub.domain.model.member.*
import bondagehub.domain.model.post.video.*
import bondagehub.domain.model.post.album.*
import bondagehub.domain.model.post.category.*
import bondagehub.domain.model.post.tag.*
import bondagehub.domain.model.post.image.PostImage
import java.time.Instant

/**
 * 投稿そのものを表現する。
 */
class Post(
    val id: PostId, // 主キー
    val type: PostType, // 投稿のタイプ
    val memberId: MemberId, // 会員ID
    val status: PostStatus, // 投稿ステータス
    val createdAt: Instant, // 投稿日時
    val updatedAt: Instant, // 更新日時

    val categories: List<PostCategory>? = null,
    val categoryIds: List<Long>? = null,
    val tags: List<PostTag>? = null,
    val tagIds: List<Long>? = null,
    val member: Member? = null,
    val album: PostAlbum? = null,
    val video: PostVideo? = null,
    val image: PostImage? = null,
): Entity<Post> {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Post
        return sameIdentityAs(other)
    }

    override fun hashCode(): Int = MemberId.hashCode()

    override fun sameIdentityAs(other: Post): Boolean = id == other.id

    companion object {

        fun create(
            id: PostId,
            type: PostType,
            memberId: MemberId,
            status: PostStatus,
            album: PostAlbum? = null,
            video: PostVideo? = null,
            image: PostImage? = null,
            categoryIds: List<Long>? = null,
            tagIds: List<Long>? = null,
        ): Post = with(Instant.now()) {
            Post(
                id,
                type,
                memberId,
                status,
                createdAt = this,
                updatedAt = this,
                album = album,
                video = video,
                image = image,
                categoryIds = categoryIds,
                tagIds = tagIds,
            )
        }
    }
}
