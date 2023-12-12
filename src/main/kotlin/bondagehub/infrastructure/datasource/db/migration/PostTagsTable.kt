package bondagehub.infrastructure.datasource.db.migration

import bondagehub.infrastructure.datasource.db.*
import bondagehub.domain.model.posttag.*

object PostTagsTable : ExposedTable<PostTag>("post_tags") {

    val id = PostTagsTable.long("id").autoIncrement()
    val name = PostTagsTable.varchar("type", length = 64)
    val description = PostTagsTable.varchar("description", length = 64)
    val slug = PostTagsTable.varchar("slug", length = 64)
    val createdAt = PostTagsTable.instant("created_at")
    val updatedAt = PostTagsTable.instant("updated_at")

    override val primaryKey = PrimaryKey(id)
}
