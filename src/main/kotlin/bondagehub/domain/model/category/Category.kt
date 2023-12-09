package bondagehub.domain.model.category

import java.time.Instant

/**
 * カテゴリーを表現する。
 */
class Category(
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
        ): Category = with(Instant.now()) {
            Category(
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