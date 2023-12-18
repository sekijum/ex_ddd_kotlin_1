package bondagehub.infrastructure.datasource.admin

import bondagehub.domain.model.admin_user.*
import bondagehub.application.repository.admin.*
import bondagehub.common.database.table.*
import bondagehub.domain.exception.*
import org.jetbrains.exposed.sql.*
import org.springframework.stereotype.Repository
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.springframework.data.domain.Pageable

@Repository
class AdminUserDataSource: AdminUserRepository {

    override fun findOneById(id: Long, lock: Boolean): AdminUser =
        AdminUsersTable.select { AdminUsersTable.id eq id }
            .run { if (lock) this.forUpdate() else this }
            .firstOrNull()
            ?.rowToModel()
            ?: throw NotFoundException("AdminUser(id=$id)")

    override fun findAllByQuery(): List<AdminUser> =
        AdminUsersTable.selectAll()
            .orderBy(AdminUsersTable.createdAt)
            .map { it.rowToModel() }

    override fun findPageByQuery(pageable: Pageable): List<AdminUser> =
        AdminUsersTable.selectAll()
            .orderBy(AdminUsersTable.createdAt)
            .limit(pageable.pageSize, offset = pageable.offset.toLong())
            .map { it.rowToModel() }

    override fun existsById(id: Long, lock: Boolean): Boolean =
        AdminUsersTable.exists()

    override fun count(): Int =
        AdminUsersTable.selectAll().count().toInt()

    override fun createOne(adminUser: AdminUser) {
        AdminUsersTable.insert {
            it[id] = adminUser.id
            it[name] = adminUser.name.value()
            it[email] = adminUser.email.value()
            it[pass] = adminUser.pass.value()
            it[createdAt] = adminUser.createdAt
            it[updatedAt] = adminUser.updatedAt
        }
    }

    override fun updateOneById(adminUser: AdminUser) {
        AdminUsersTable.update({ AdminUsersTable.id eq adminUser.id }) {
            it[name] = adminUser.name.value()
            it[email] = adminUser.email.value()
            it[updatedAt] = adminUser.updatedAt
        }
            .takeIf { it > 0 }
            ?: throw UpdateFailedException("AdminUser($adminUser.id)")
    }

    override fun deleteOneById(adminUser: AdminUser) {
        AdminUsersTable.deleteWhere { AdminUsersTable.id eq adminUser.id }
            .takeIf { it > 0 }
            ?: throw DeleteFailedException("AdminUser($adminUser.id)")
    }

    private fun ResultRow.rowToModel(): AdminUser =
        AdminUser(
            this[AdminUsersTable.id],
            Name.valueOf(this[AdminUsersTable.name]),
            Email.valueOf(this[AdminUsersTable.email]),
            Pass.from(this[AdminUsersTable.pass]),
            this[AdminUsersTable.createdAt],
            this[AdminUsersTable.updatedAt]
        )
}
