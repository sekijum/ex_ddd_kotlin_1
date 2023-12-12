package bondagehub.infrastructure.datasource.admin

import bondagehub.application.repository.admin.PostTagRepository
import bondagehub.domain.model.posttag.*
import bondagehub.infrastructure.datasource.db.migration.AdminUsersTable
import bondagehub.infrastructure.datasource.db.migration.PostTagsTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class PostTagDataSource: PostTagRepository {

    override fun findAll(limit: Int, offset: Int): List<PostTag> =
        PostTagsTable.selectAll()
            .orderBy(PostTagsTable.createdAt)
            .limit(limit, offset = offset.toLong() * limit.toLong())
            .map { it.rowToModel() }

    private fun ResultRow.rowToModel(): PostTag =
        PostTag(
            this[PostTagsTable.id],
            this[PostTagsTable.name],
            this[PostTagsTable.description],
            this[PostTagsTable.slug],
            this[AdminUsersTable.createdAt],
            this[AdminUsersTable.updatedAt]
        )
}