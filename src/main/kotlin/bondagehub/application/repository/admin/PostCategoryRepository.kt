package bondagehub.application.repository.admin

import bondagehub.domain.model.postcategory.*

/**
 * 投稿カテゴリーを操作するためのリポジトリを表現する。
 */
interface PostCategoryRepository {

    fun findAll(limit: Int, offset: Int): List<PostCategory>
    fun count(): Int
}
