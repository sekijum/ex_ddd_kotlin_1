package bondagehub.application.service.admin.member.dto

data class MemberDTO(
    val id: String,
    val name: String,
    val pass: String,
    val statusName: String,
    val statusLabel: String,
    val email: String?,
    val emailVerifiedAt: Long?,
    val deletedAt: Long?,
    val createdAt: Long,
    val updatedAt: Long,
)
