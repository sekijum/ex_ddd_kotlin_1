package bondagehub.presentation.controller.admin.post.resource

import bondagehub.application.service.admin.post.dto.PostDTO
import io.swagger.annotations.ApiModelProperty

data class PostResponse(
    @ApiModelProperty(
        value = "投稿のID", example = "AC_c5fb2cec-a77c-4886-b997-ffc2ef060e78", required = true, position = 1
    )
    val id: String,
    @ApiModelProperty(
        value = "会員ID", example = "c5fb2cec-a77c-4886-b997-ffc2ef060e78", required = true, position = 7
    )
    val memberId: String,
    @ApiModelProperty(
        value = "投稿種別のラベル", example = "アルバム", required = true, position = 2
    )
    val typeLabel: String,
    @ApiModelProperty(
        value = "投稿種別のキー", example = "Album", required = true, position = 4
    )
    val typeName: String,
    @ApiModelProperty(
        value = "ステータス種別", example = "公開済み", required = true, position = 5
    )
    val statusLabel: String,
    @ApiModelProperty(
            value = "ステータスのキー", example = "Publish", required = true, position = 6
    )
    val statusName: String,
    @ApiModelProperty(
        value = "管理者の作成日時", example = "1576120910973", required = true, position = 8
    )
    val createdAt: Long,
    @ApiModelProperty(
        value = "管理者の削除日時", example = "1576120910973", required = false, position = 9
    )
    val updatedAt: Long
) {

    companion object {

        fun from(dto: PostDTO): PostResponse =
            PostResponse(
                dto.id,
                dto.memberId,
                dto.typeLabel,
                dto.typeName,
                dto.statusLabel,
                dto.statusName,
                dto.createdAt,
                dto.updatedAt
            )
    }
}
