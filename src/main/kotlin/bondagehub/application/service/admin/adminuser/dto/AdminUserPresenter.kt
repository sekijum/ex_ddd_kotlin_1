package bondagehub.application.service.admin.adminuser.dto

import bondagehub.domain.model.admin_user.*
import bondagehub.application.service.*
import org.springframework.stereotype.Component

@Component
class AdminUserPresenter {

     fun toDTO(adminUser: AdminUser): AdminUserDTO {
        return AdminUserDTO(
            adminUser.id,
            adminUser.name.value(),
            adminUser.email.value(),
            adminUser.pass.format(),
            adminUser.createdAt.toEpochMilli(),
            adminUser.updatedAt.toEpochMilli()
        )
    }

    fun toDTO(adminUsers: List<AdminUser>, count: Int, limit: Int, offset: Int): PaginationDTO<AdminUserDTO> {
        return PaginationDTO(
            count,
            limit,
            offset,
            adminUsers.map { toDTO(it) }
        )
    }
}
