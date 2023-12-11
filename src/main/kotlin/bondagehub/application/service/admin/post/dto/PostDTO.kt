package bondagehub.application.service.admin.post.dto

import bondagehub.domain.model.post.*

/**
 * 管理者([Post])のDTO。
 */
data class PostDTO(
    val id: String,
    val typeLabel: String,
    val typeName: String,
    val memberId: String,
    val statusLabel: String,
    val statusName: String,
    val createdAt: Long,
    val updatedAt: Long,
)
