package bondagehub.application.repository.admin

import bondagehub.domain.model.posttag.*

/**
 * 会員タグを操作するためのリポジトリを表現する。
 */
interface PostTagRepository {

    fun findAll(limit: Int, offset: Int): List<PostTag>
    fun count(): Int
}
