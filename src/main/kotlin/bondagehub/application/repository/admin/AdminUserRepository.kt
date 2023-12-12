package bondagehub.application.repository.admin

import bondagehub.domain.model.adminuser.*

/**
 * 管理者を操作するためのリポジトリを表現する。
 */
interface AdminUserRepository {

    fun findById(id: Long, lock: Boolean = false): AdminUser
    fun findAll(limit: Int, offset: Int): List<AdminUser>
    fun count(): Int
    fun create(adminUser: AdminUser)
    fun update(adminUser: AdminUser)
    fun delete(adminUser: AdminUser)
}
