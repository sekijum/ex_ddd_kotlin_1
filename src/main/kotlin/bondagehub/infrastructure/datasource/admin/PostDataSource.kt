package bondagehub.infrastructure.datasource.admin

import bondagehub.application.repository.admin.PostRepository
import bondagehub.domain.model.member.MemberId
import bondagehub.domain.model.post.*
import bondagehub.infrastructure.datasource.db.migration.AdminUsersTable
import bondagehub.infrastructure.datasource.db.migration.PostsTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class PostDataSource: PostRepository {

    override fun findAll(limit: Int, offset: Int): List<Post> =
        PostsTable.selectAll()
            .orderBy(PostsTable.createdAt)
            .limit(limit, offset = offset.toLong() * limit.toLong())
            .map { it.rowToModel() }

    private fun ResultRow.rowToModel(): Post =
        Post(
            PostId.valueOf(this[PostsTable.id]),
            PostType(this[PostsTable.type]),
            MemberId.valueOf(this[PostsTable.memberId]),
            PostStatus(this[PostsTable.status]),
            this[AdminUsersTable.createdAt],
            this[AdminUsersTable.updatedAt]
        )
}