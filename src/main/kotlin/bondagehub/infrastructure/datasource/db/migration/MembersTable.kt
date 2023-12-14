package bondagehub.infrastructure.datasource.db.migration

import bondagehub.domain.model.member.*
import bondagehub.infrastructure.datasource.db.*

object MembersTable : ExposedTable<Member>("posts") {

    val id = MembersTable.varchar("id", length = 64)
    val name = MembersTable.varchar("name", length = 64)
    val email = MembersTable.varchar("email", length = 100).nullable().uniqueIndex()
    val pass = MembersTable.varchar("pass", length = 64)
    val status = MembersTable.integer("status")
    val emailVerifiedAt= MembersTable.instant("email_verified_at").nullable()
    val deletedAt= MembersTable.instant("deleted_at").nullable()
    val createdAt = MembersTable.instant("created_at")
    val updatedAt = MembersTable.instant("updated_at")

    override val primaryKey = PrimaryKey(id)
}
