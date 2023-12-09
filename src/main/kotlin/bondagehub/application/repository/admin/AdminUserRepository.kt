package bondagehub.application.repository.admin

import bondagehub.domain.model.admin_user.*

/**
 * 管理者を操作するためのリポジトリを表現する。
 */
interface AdminUserRepository {

    fun findById(id: Long, lock: Boolean = false): AdminUser

    fun findAll(limit: Int, offset: Int): List<AdminUser>

    fun count(): Int

    fun add(adminUser: AdminUser)

    fun set(adminUser: AdminUser)

    fun remove(adminUser: AdminUser)
}
