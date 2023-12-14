package bondagehub.application.service.admin.postcategory.dto

data class PostCategoryDTO(
    val id: Long,
    val name: String,
    val description: String?,
    val slug: String,
    val createdAt: Long,
    val updatedAt: Long,
)
