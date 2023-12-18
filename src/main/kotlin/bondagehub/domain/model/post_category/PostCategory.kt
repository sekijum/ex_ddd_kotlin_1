package bondagehub.domain.model.post_category

import java.time.Instant

/**
 * 投稿カテゴリーを表現する。
 */
class PostCategory(
    val id: Long, // 投稿の主キー
    val name: String, // タグの名前
    val description: String?, // タグの説明
    val slug: String, // URLスラッグ
    val createdAt: Instant, // 作成日時
    val updatedAt: Instant, // 更新日時
) {

    companion object {

        fun create(
            name: String,
            description: String?,
            slug: String,
        ): PostCategory = with(Instant.now()) {
            PostCategory(
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