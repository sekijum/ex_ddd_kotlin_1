package bondagehub.infrastructure.datasource.db.migration

import bondagehub.domain.model.admin_user.*
import bondagehub.infrastructure.datasource.db.*
import org.apache.juli.logging.Log
import org.jetbrains.exposed.sql.*
import java.time.Instant

object AdminUsersTable : ExposedTable<AdminUser>("admin-users") {

    val id: Column<Long> = AdminUsersTable.long("id")
    val name: Column<String> = AdminUsersTable.varchar("name", length = 100)
    val email: Column<String> = AdminUsersTable.varchar("email", length = 100)
    val pass: Column<String> = AdminUsersTable.varchar("pass", length = 64)
    val createdAt: Column<Instant> = AdminUsersTable.instant("created_at")
    val updatedAt: Column<Instant> = AdminUsersTable.instant("updated_at")

    override val primaryKey = PrimaryKey(id)
}
