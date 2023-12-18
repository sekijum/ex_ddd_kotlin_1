package bondagehub.infrastructure.datasource.admin

import bondagehub.application.repository.admin.PostRepository
import bondagehub.domain.model.member.MemberId
import bondagehub.domain.model.post.*
import bondagehub.common.database.table.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class PostDataSource: PostRepository {

    override fun findPageByQuery(pageable: Pageable): List<Post> =
        PostsTable.selectAll()
            .orderBy(PostsTable.createdAt)
            .limit(pageable.pageSize, offset = pageable.offset.toLong())
            .map { it.rowToModel() }

    override fun count(): Int =
        PostsTable.selectAll().count().toInt()

    private fun ResultRow.rowToModel(): Post =
        Post(
            PostId.valueOf(this[PostsTable.id]),
            PostType(this[PostsTable.type]),
            MemberId.valueOf(this[PostsTable.memberId]),
            PostStatus(this[PostsTable.status]),
            this[PostsTable.createdAt],
            this[PostsTable.updatedAt]
        )
}