package bondagehub.common.database.seeder


import bondagehub.common.database.table.*
import org.jetbrains.exposed.sql.batchInsert
import bondagehub.domain.model.member.*
import bondagehub.domain.model.member.detail.Gender
import bondagehub.domain.model.member.detail.MemberDetail
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.jetbrains.exposed.sql.insert
import java.time.Instant

class MemberSeeder {

	companion object {
		fun run() {
			val member01Id = MemberId.generate()
			Member.create(
				member01Id,
				Name.valueOf("会員01"),
				Email.valueOf("member01@example.com"),
				Pass.valueOf("pass1234", member01Id),
				MemberDetail.create(
					memberId = member01Id,
				)
			).let {member ->
				MembersTable.insert {
					it[MembersTable.id] = member.id.id()
					it[MembersTable.name] = member.name.value()
					it[MembersTable.email] = member.email?.value()
					it[MembersTable.pass] = member.pass.value()
					it[MembersTable.status] = member.status.status
					it[MembersTable.updatedAt] = member.updatedAt
					it[MembersTable.createdAt] = member.createdAt
				}

				MemberDetailsTable.insert {
					member.detail?.let {detail ->
						it[MemberDetailsTable.memberId] = member.id.id()
						it[MemberDetailsTable.updatedAt] = detail.updatedAt
						it[MemberDetailsTable.createdAt] = detail.createdAt
					}
				}
			}
		}
	}
}
