package bondagehub.domain.model.post_tag

import java.time.Instant

class PostTag(
    val id: Long, // 投稿の主キー
    val name: String, // タグの名前
    val description: String?, // タグの説明
    val slug: String, // URLスラッグ
    val createdAt: Instant, // 投稿日時
    val updatedAt: Instant, // 更新日時
) {

    companion object {

        fun create(
            name: String,
            description: String?,
            slug: String,
        ): PostTag = with(Instant.now()) {
            PostTag(
                id = 0,
                name = name,
                description = description,
                slug = slug,
                createdAt = this,
                updatedAt = this
            )
        }
    }
}