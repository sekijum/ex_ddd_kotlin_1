package bondagehub.common.database.table

import bondagehub.common.database.*
import bondagehub.domain.model.post_tag.*

object PostTagsTable : ExposedTable<PostTag>("post_tags") {

    val id = PostTagsTable.long("id").autoIncrement()
    val name = PostTagsTable.varchar("type", length = 64)
    val description = PostTagsTable.varchar("description", length = 64)
    val slug = PostTagsTable.varchar("slug", length = 64)
    val createdAt = PostTagsTable.instant("created_at")
    val updatedAt = PostTagsTable.instant("updated_at")

    override val primaryKey = PrimaryKey(id)
}
