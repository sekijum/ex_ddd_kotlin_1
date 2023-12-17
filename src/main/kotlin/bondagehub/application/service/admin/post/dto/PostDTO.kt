package bondagehub.application.service.admin.post.dto

data class PostDTO(
    val id: String,
    val memberId: String,
    val typeLabel: String,
    val typeName: String,
    val statusLabel: String,
    val statusName: String,
    val createdAt: Long,
    val updatedAt: Long,
)
