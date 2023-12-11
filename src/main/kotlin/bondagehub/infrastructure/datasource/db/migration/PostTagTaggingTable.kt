package bondagehub.infrastructure.datasource.db.migration

import org.jetbrains.exposed.sql.Table

object PostTagTaggingTable : Table("post_tag_tagging") {

    val id = long("id").autoIncrement()
    val postId = reference("post_id", PostsTable.id)
    val postTagId = reference("post_tag_id", PostTagsTable.id)

    override val primaryKey = PrimaryKey(id)
}
