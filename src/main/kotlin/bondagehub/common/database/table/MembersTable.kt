package bondagehub.common.database.table

import bondagehub.domain.model.member.*
import bondagehub.common.database.*

object MembersTable : ExposedTable<Member>("members") {

    val id = MembersTable.varchar("id", length = 64)
    val name = MembersTable.varchar("name", length = 64)
    val email = MembersTable.varchar("email", length = 100).nullable().uniqueIndex()
    val pass = MembersTable.varchar("pass", length = 64)
    val status = MembersTable.integer("status").uniqueIndex()
    val emailVerifiedAt= MembersTable.instant("email_verified_at").nullable()
    val deletedAt= MembersTable.instant("deleted_at").nullable()
    val createdAt = MembersTable.instant("created_at")
    val updatedAt = MembersTable.instant("updated_at")

    override val primaryKey = PrimaryKey(id)
}
