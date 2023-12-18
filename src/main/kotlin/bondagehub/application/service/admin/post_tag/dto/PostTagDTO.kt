package bondagehub.application.service.admin.post_tag.dto

data class PostTagDTO(
    val id: Long,
    val name: String,
    val description: String?,
    val slug: String,
    val createdAt: Long,
    val updatedAt: Long,
)
