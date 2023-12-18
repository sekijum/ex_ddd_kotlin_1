package bondagehub.infrastructure.datasource.admin

import bondagehub.application.repository.admin.MemberRepository
import bondagehub.domain.model.member.*
import bondagehub.common.database.table.*
import bondagehub.domain.model.member.detail.MemberDetail
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.slf4j.LoggerFactory

@Repository
class MemberDataSource: MemberRepository {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun findPageByQuery(pageable: Pageable): List<Member> {
        log.info("hogehgoe")
        return MembersTable.join(
            otherTable = MemberDetailsTable,
            joinType = JoinType.INNER,
            onColumn = MembersTable.id,
            otherColumn = MemberDetailsTable.memberId
        )
            .selectAll()
            .orderBy(MembersTable.createdAt)
            .limit(pageable.pageSize, offset = pageable.offset.toLong())
            .map { it.rowToModel() }
//         MembersTable.leftJoin(PostsTable)
//             .slice(MembersTable.columns)
//             .selectAll()
//             .orderBy(MembersTable.createdAt)
//             .limit(pageable.pageSize, offset = pageable.offset.toLong())
//             .groupBy(PostsTable.id)
//             .map { it.rowToModel() }
    }

    override fun count(): Int =
        MembersTable.selectAll().count().toInt()

    private fun ResultRow.rowToModel(): Member =
        Member(
            MemberId.valueOf(this[MembersTable.id]),
            Name.valueOf(this[MembersTable.name]),
            Pass.from(this[MembersTable.pass]),
            MemberStatus(this[MembersTable.status]),
            this[MembersTable.email]?.let { Email.valueOf(it) },
            this[MembersTable.emailVerifiedAt],
            this[MembersTable.deletedAt],
            this[MembersTable.createdAt],
            this[MembersTable.updatedAt],
        )
}