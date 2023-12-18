package bondagehub.common.database.table

import bondagehub.domain.model.post.*
import bondagehub.common.database.*

object PostsTable : ExposedTable<Post>("posts") {

    val id = PostsTable.varchar("id", length = 64)
    val type = PostsTable.integer("type").uniqueIndex()
    val memberId = PostsTable.reference("member_id", MembersTable.id)
    val status = PostsTable.integer("status").uniqueIndex()
    val createdAt = PostsTable.instant("created_at")
    val updatedAt = PostsTable.instant("updated_at")

    override val primaryKey = PrimaryKey(id)
}
