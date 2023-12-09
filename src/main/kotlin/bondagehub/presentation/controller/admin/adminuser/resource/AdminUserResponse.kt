package bondagehub.presentation.controller.admin.adminuser.resource

import bondagehub.application.service.admin.adminuser.dto.*
import io.swagger.annotations.ApiModelProperty

/**
 * 管理者のレスポンス情報。
 */
data class AdminUserResponse(
    @ApiModelProperty(
        value = "管理者のID", example = "AC_c5fb2cec-a77c-4886-b997-ffc2ef060e78", required = true, position = 1
    )
    val id: Long,
    @ApiModelProperty(
        value = "管理者の氏名または会社名", example = "あいうえお", required = true, position = 2
    )
    val name: String,
    @ApiModelProperty(
        value = "管理者のメールアドレス", example = "example@example.com", required = true, position = 4
    )
    val email: String,
    @ApiModelProperty(
        value = "管理者のパスワード", required = true, position = 5
    )
    val pass: String,
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

        fun from(dto: AdminUserDTO): AdminUserResponse =
            AdminUserResponse(
                dto.id,
                dto.name,
                dto.email,
                dto.pass,
                dto.createdAt,
                dto.updatedAt
            )
    }
}
