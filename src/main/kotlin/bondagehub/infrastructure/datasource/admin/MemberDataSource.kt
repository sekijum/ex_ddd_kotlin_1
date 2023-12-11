package bondagehub.infrastructure.datasource.admin

import bondagehub.application.repository.admin.MemberRepository
import bondagehub.domain.model.member.*
import bondagehub.domain.model.post.PostId
import bondagehub.domain.model.post.category.PostCategory
import bondagehub.infrastructure.datasource.db.migration.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll

class MemberDataSource: MemberRepository {

    override fun findAll(limit: Int, offset: Int): List<Member> =
        MembersTable.selectAll()
            .orderBy(MembersTable.createdAt)
            .limit(limit, offset = offset.toLong() * limit.toLong())
            .map { it.rowToModel() }

    private fun ResultRow.rowToModel(): Member =
        Member(
            MemberId.valueOf(this[MembersTable.id]),
            Name.valueOf(this[MembersTable.name]),
            Pass.from(this[MembersTable.pass]),
            MemberStatus(this[MembersTable.status]),
            this[MembersTable.email]?.let { Email.from(it) },
            this[MembersTable.emailVerifiedAt],
            this[MembersTable.deletedAt],
            this[MembersTable.createdAt],
            this[MembersTable.updatedAt]
        )
}