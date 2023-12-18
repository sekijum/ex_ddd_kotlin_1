package bondagehub.application.service.admin.admin_user.dto

import bondagehub.domain.model.admin_user.*

/**
 * 管理者([AdminUser])のDTO。
 */
data class AdminUserDTO(
    val id: Long,
    val name: String,
    val email: String,
    val pass: String,
    val createdAt: Long,
    val updatedAt: Long,
)
