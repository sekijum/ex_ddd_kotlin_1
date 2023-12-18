package bondagehub.common.database.table

import org.jetbrains.exposed.sql.Table

object PostTaggingTable : Table("post_tagging") {

    val postId = reference("post_id", PostsTable.id)
    val postTagId = reference("post_tag_id", PostTagsTable.id)

    override val primaryKey = PrimaryKey(postId, postTagId)
}
