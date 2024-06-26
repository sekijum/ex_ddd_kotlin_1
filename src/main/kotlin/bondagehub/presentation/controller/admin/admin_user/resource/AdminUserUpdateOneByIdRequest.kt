package bondagehub.presentation.controller.admin.admin_user.resource

import io.swagger.annotations.ApiModelProperty

/**
 * 管理者更新時のリクエスト情報。
 */
data class AdminUserUpdateOneByIdRequest(
    @ApiModelProperty(
        value = "管理者の氏名または会社名", example = "あいうえお", required = false, position = 1
    )
    val name: String?,
    @ApiModelProperty(
        value = "管理者のメールアドレス", example = "example@example.com", required = false, position = 3
    )
    val email: String?,
)
