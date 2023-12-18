package bondagehub.common.database.table

import bondagehub.domain.model.post_category.*
import bondagehub.common.database.*

object PostCategoriesTable : ExposedTable<PostCategory>("post_categories") {

    val id = PostCategoriesTable.long("id").autoIncrement()
    val name = PostCategoriesTable.varchar("type", length = 64)
    val description = PostCategoriesTable.varchar("description", length = 64)
    val slug = PostCategoriesTable.varchar("slug", length = 64)
    val createdAt = PostCategoriesTable.instant("created_at")
    val updatedAt = PostCategoriesTable.instant("updated_at")

    override val primaryKey = PrimaryKey(id)
}
