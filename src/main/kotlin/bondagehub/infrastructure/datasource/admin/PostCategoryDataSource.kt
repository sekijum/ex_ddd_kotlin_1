package bondagehub.infrastructure.datasource.admin

import bondagehub.application.repository.admin.PostCategoryRepository
import bondagehub.domain.model.postcategory.*
import bondagehub.infrastructure.datasource.db.migration.AdminUsersTable
import bondagehub.infrastructure.datasource.db.migration.PostCategoriesTable
import bondagehub.infrastructure.datasource.db.migration.PostTagsTable
import bondagehub.infrastructure.datasource.db.migration.PostsTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class PostCategoryDataSource: PostCategoryRepository {

    override fun findPageByQuery(pageable: Pageable): List<PostCategory> =
        PostCategoriesTable.selectAll()
            .orderBy(PostCategoriesTable.createdAt)
            .limit(pageable.pageSize, offset = pageable.offset.toLong())
            .map { it.rowToModel() }

    override fun count(): Int =
        PostCategoriesTable.selectAll().count().toInt()

    private fun ResultRow.rowToModel(): PostCategory =
        PostCategory(
            this[PostCategoriesTable.id],
            this[PostCategoriesTable.name],
            this[PostCategoriesTable.description],
            this[PostCategoriesTable.slug],
            this[PostCategoriesTable.createdAt],
            this[PostCategoriesTable.updatedAt]
        )
}