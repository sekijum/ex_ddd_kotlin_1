package bondagehub.infrastructure.datasource.admin

import bondagehub.application.repository.admin.PostCategoryRepository
import bondagehub.domain.model.postcategory.*
import bondagehub.infrastructure.datasource.db.migration.AdminUsersTable
import bondagehub.infrastructure.datasource.db.migration.PostCategoriesTable
import bondagehub.infrastructure.datasource.db.migration.PostTagsTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class PostCategoryDataSource: PostCategoryRepository {

    override fun findAll(limit: Int, offset: Int): List<PostCategory> =
        PostCategoriesTable.selectAll()
            .orderBy(PostCategoriesTable.createdAt)
            .limit(limit, offset = offset.toLong() * limit.toLong())
            .map { it.rowToModel() }

    private fun ResultRow.rowToModel(): PostCategory =
        PostCategory(
            this[PostTagsTable.id],
            this[PostTagsTable.name],
            this[PostTagsTable.description],
            this[PostTagsTable.slug],
            this[AdminUsersTable.createdAt],
            this[AdminUsersTable.updatedAt]
        )
}