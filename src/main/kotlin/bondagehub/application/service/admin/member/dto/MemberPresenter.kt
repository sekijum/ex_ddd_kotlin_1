package bondagehub.application.service.admin.member.dto

import bondagehub.domain.model.member.*
import bondagehub.application.service.*
import org.springframework.stereotype.Component

@Component
class MemberPresenter {

     fun toDTO(member: Member): MemberDTO {
        return MemberDTO(
            member.id.id(),
            member.name.value(),
            member.pass.format(),
            member.status.name,
            member.status.label,
            member.email?.value(),
            member.emailVerifiedAt?.toEpochMilli(),
            member.deletedAt?.toEpochMilli(),
            member.createdAt.toEpochMilli(),
            member.updatedAt.toEpochMilli()
        )
    }

    fun toDTO(members: List<Member>, count: Int, limit: Int, offset: Int): PaginationDTO<MemberDTO> {
        return PaginationDTO(
            count,
            limit,
            offset,
            members.map { toDTO(it) }
        )
    }
}
