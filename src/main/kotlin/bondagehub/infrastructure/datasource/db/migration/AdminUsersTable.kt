package bondagehub.infrastructure.datasource.db.migration

import bondagehub.domain.model.adminuser.*
import bondagehub.infrastructure.datasource.db.*

object AdminUsersTable : ExposedTable<AdminUser>("admin-users") {

    val id = AdminUsersTable.long("id").autoIncrement()
    val name = AdminUsersTable.varchar("name", length = 50)
    val email = AdminUsersTable.varchar("email", length = 100).uniqueIndex()
    val pass = AdminUsersTable.varchar("pass", length = 64)
    val createdAt = AdminUsersTable.instant("created_at")
    val updatedAt = AdminUsersTable.instant("updated_at")

    override val primaryKey = PrimaryKey(id)
}
