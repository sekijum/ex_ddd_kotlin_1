package bondagehub.common.database.table

import org.jetbrains.exposed.sql.Table
import bondagehub.common.database.*

object PostCategorizationTable : Table("post_categorization") {

    val postId = reference("post_id", PostsTable.id)
    val postCategoryId = reference("post_category_id", PostCategoriesTable.id)

    override val primaryKey = PrimaryKey(postId, postCategoryId)
}
