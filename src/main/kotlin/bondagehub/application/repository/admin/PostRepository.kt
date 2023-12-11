package bondagehub.application.repository.admin

import bondagehub.domain.model.post.*

/**
 * 投稿を操作するためのリポジトリを表現する。
 */
interface PostRepository {

    fun findAll(limit: Int, offset: Int): List<Post>
}
