package bondagehub.infrastructure.datasource.db.migration

import org.jetbrains.exposed.sql.Table

object PostCategoryCategorizationTable : Table("post_category_categorization") {

    val id = long("id").autoIncrement()
    val postId = reference("post_id", PostsTable.id)
    val postCategoryId = reference("post_category_id", PostCategoriesTable.id)

    override val primaryKey = PrimaryKey(id)
}
