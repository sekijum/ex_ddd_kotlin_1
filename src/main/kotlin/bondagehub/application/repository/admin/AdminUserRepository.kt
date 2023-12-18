package bondagehub.application.repository.admin

import bondagehub.domain.model.admin_user.*
import org.springframework.data.domain.Pageable

/**
 * 管理者を操作するためのリポジトリを表現する。
 */
interface AdminUserRepository {

    fun findOneById(id: Long, lock: Boolean = false): AdminUser
    fun findAllByQuery(): List<AdminUser>
    fun findPageByQuery(pageable: Pageable): List<AdminUser>
    fun count(): Int
    fun existsById(id: Long, lock: Boolean = false): Boolean
    fun createOne(adminUser: AdminUser)
    fun updateOneById(adminUser: AdminUser)
    fun deleteOneById(adminUser: AdminUser)
}
