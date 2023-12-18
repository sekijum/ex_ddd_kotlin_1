package bondagehub.common.database.table

import bondagehub.domain.model.member.detail.*
import org.jetbrains.exposed.sql.jodatime.date
import bondagehub.common.database.*

object MemberDetailsTable : ExposedTable<MemberDetail>("member_details") {

    val id = MemberDetailsTable.integer("id").autoIncrement()
    val memberId = MemberDetailsTable.reference("member_id", MembersTable.id)
    val birthday = MemberDetailsTable.date("birthday").nullable()
    val gender = MemberDetailsTable.integer("gender").nullable()
    val iconId = MemberDetailsTable.reference("icon_id", AttachmentsTable.id).nullable()
    val displayName= MemberDetailsTable.varchar("display_name", length = 64).nullable()
    val createdAt = MemberDetailsTable.instant("created_at")
    val updatedAt = MemberDetailsTable.instant("updated_at")

    override val primaryKey = PrimaryKey(id)
}
