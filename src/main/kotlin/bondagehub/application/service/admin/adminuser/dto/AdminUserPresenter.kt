package bondagehub.application.service.admin.adminuser.dto

import bondagehub.domain.model.adminuser.*
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

    fun toDTO(adminUsers: List<AdminUser>, count: Int): Pair<List<AdminUserDTO>, Int> {
        return Pair(
            adminUsers.map { toDTO(it) },
            count
        )
    }

}
