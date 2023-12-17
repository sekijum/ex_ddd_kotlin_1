package bondagehub.application.service.admin.member.dto

import bondagehub.domain.model.member.*
import org.springframework.stereotype.Component

@Component
class MemberPresenter {

     fun toDTO(member: Member): MemberDTO {
        return MemberDTO(
            member.id.id(),
            member.name.value(),
            member.pass.format(),
            member.email?.value(),
            member.status.name,
            member.status.label,
            member.emailVerifiedAt?.toEpochMilli(),
            member.deletedAt?.toEpochMilli(),
            member.createdAt.toEpochMilli(),
            member.updatedAt.toEpochMilli()
        )
    }


    fun toDTO(members: List<Member>, count: Int): Pair<List<MemberDTO>, Int> {
        return Pair(
            members.map { toDTO(it) },
            count
        )
    }

}
