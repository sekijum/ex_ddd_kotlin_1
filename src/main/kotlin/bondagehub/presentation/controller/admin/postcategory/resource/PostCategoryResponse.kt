package bondagehub.presentation.controller.admin.postcategory.resource

import bondagehub.application.service.admin.postcategory.dto.PostCategoryDTO
import io.swagger.annotations.ApiModelProperty

data class PostCategoryResponse(
        @ApiModelProperty(
                value = "投稿タグのID", example = "1", required = true, position = 1
        )
        val id: Long,
        @ApiModelProperty(
                value = "タグ名", example = "ほげ", required = true, position = 2
        )
        val name: String,
        @ApiModelProperty(
                value = "タグ詳細", example = "ほげ", required = false, position = 4
        )
        val description: String?,
        @ApiModelProperty(
                value = "投稿タグスラッグ", example = "hoge", required = true, position = 5
        )
        val memberId: String?,
        @ApiModelProperty(
                value = "管理者の作成日時", example = "1576120910973", required = true, position = 6
        )
        val createdAt: Long,
        @ApiModelProperty(
                value = "管理者の削除日時", example = "1576120910973", required = false, position = 7
        )
        val updatedAt: Long
) {

    companion object {

        fun from(dto: PostCategoryDTO): PostCategoryResponse =
                PostCategoryResponse(
                    dto.id,
                    dto.name,
                    dto.description,
                    dto.slug,
                    dto.createdAt,
                    dto.updatedAt
                )
    }
}
