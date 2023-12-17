package bondagehub.infrastructure.datasource.admin

import bondagehub.application.repository.admin.PostTagRepository
import bondagehub.domain.model.posttag.*
import bondagehub.infrastructure.datasource.db.migration.AdminUsersTable
import bondagehub.infrastructure.datasource.db.migration.PostTagsTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class PostTagDataSource: PostTagRepository {

    override fun findPageByQuery(pageable: Pageable): List<PostTag> =
        PostTagsTable.selectAll()
            .orderBy(PostTagsTable.createdAt)
            .limit(pageable.pageSize, offset = pageable.offset.toLong())
            .map { it.rowToModel() }

    override fun count(): Int =
        PostTagsTable.selectAll().count().toInt()

    private fun ResultRow.rowToModel(): PostTag =
        PostTag(
            this[PostTagsTable.id],
            this[PostTagsTable.name],
            this[PostTagsTable.description],
            this[PostTagsTable.slug],
            this[PostTagsTable.createdAt],
            this[PostTagsTable.updatedAt]
        )
}