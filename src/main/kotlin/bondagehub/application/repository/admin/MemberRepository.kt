package bondagehub.application.repository.admin

import bondagehub.domain.model.member.*

/**
 * 会員ユーザーを操作するためのリポジトリを表現する。
 */
interface MemberRepository {

    fun findAll(limit: Int, offset: Int): List<Member>
    fun count(): Int
}
